package com.depromeet.threedays.front.domain.usecase.habit;

import com.depromeet.threedays.data.entity.habit.HabitAchievementEntity;
import com.depromeet.threedays.data.entity.habit.HabitEntity;
import com.depromeet.threedays.data.enums.LevelUpSection;
import com.depromeet.threedays.data.enums.MemberStatus;
import com.depromeet.threedays.front.config.security.AuditorHolder;
import com.depromeet.threedays.front.domain.converter.RewardHistoryConverter;
import com.depromeet.threedays.front.domain.converter.habit.HabitAchievementConverter;
import com.depromeet.threedays.front.domain.converter.habit.HabitConverter;
import com.depromeet.threedays.front.domain.converter.mate.MateConverter;
import com.depromeet.threedays.front.domain.model.habit.Habit;
import com.depromeet.threedays.front.domain.model.habit.HabitAchievement;
import com.depromeet.threedays.front.domain.model.mate.Mate;
import com.depromeet.threedays.front.domain.validation.HabitAchievementValidator;
import com.depromeet.threedays.front.exception.MemberNotFoundException;
import com.depromeet.threedays.front.exception.ResourceNotFoundException;
import com.depromeet.threedays.front.persistence.repository.RewardHistoryRepository;
import com.depromeet.threedays.front.persistence.repository.habit.HabitAchievementRepository;
import com.depromeet.threedays.front.persistence.repository.habit.HabitRepository;
import com.depromeet.threedays.front.persistence.repository.mate.MateRepository;
import com.depromeet.threedays.front.persistence.repository.member.MemberRepository;
import com.depromeet.threedays.front.support.DateCalculator;
import com.depromeet.threedays.front.web.request.habit.SaveHabitAchievementRequest;
import java.time.LocalDate;
import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional
@RequiredArgsConstructor
public class SaveHabitAchievementUseCase {

	private static final int PROVIDE_REWARD_COUNT = 3;

	private final MemberRepository memberRepository;
	private final HabitAchievementRepository repository;
	private final HabitRepository habitRepository;

	private final MateRepository mateRepository;
	private final RewardHistoryRepository rewardHistoryRepository;
	private final HabitAchievementValidator validator;

	public Habit execute(Long habitId, final SaveHabitAchievementRequest request) {
		Long memberId = AuditorHolder.get();
		memberRepository
				.findByIdAndStatus(memberId, MemberStatus.REGULAR)
				.orElseThrow(() -> new MemberNotFoundException(memberId));

		validator.validateCreateConstraints(HabitAchievementConverter.from(request));

		final HabitEntity source =
				habitRepository.findById(habitId).orElseThrow(ResourceNotFoundException::new);

		final HabitAchievement lastHabitAchievement =
				HabitAchievementConverter.to(
						repository.findFirstByHabitIdOrderByAchievementDateDesc(habitId).orElse(null));

		final Mate mate =
				mateRepository
						.findByHabitIdAndDeletedFalse(source.getId())
						.map(MateConverter::from)
						.orElse(null);

		// TODO : /api/v2 에서 totalAchievementCount는 삭제 되어야함
		final Habit habit =
				HabitConverter.from(source).toBuilder()
						.totalAchievementCount(repository.countByHabitId(habitId))
						.mate(mate)
						.build();

		if (lastHabitAchievement == null) {
			return HabitConverter.from(
					habit,
					this.save(
							habit,
							request,
							DateCalculator.findNextDate(habit.getDayOfWeeks(), request.getAchievementDate()),
							1),
					0L);
		}

		if (lastHabitAchievement.getAchievementDate().isEqual(request.getAchievementDate())) {
			return HabitConverter.from(
					habit,
					lastHabitAchievement,
					getTotalRewardCount(habit, lastHabitAchievement.getSequence()));
		}

		if (request.getAchievementDate().isAfter(lastHabitAchievement.getNextAchievementDate())) {
			return HabitConverter.from(
					habit,
					this.save(
							habit,
							request,
							DateCalculator.findNextDate(habit.getDayOfWeeks(), request.getAchievementDate()),
							1),
					getTotalRewardCount(habit, 1));
		}

		Integer sequence = lastHabitAchievement.getSequence() + 1;

		if (request.getAchievementDate().isEqual(lastHabitAchievement.getNextAchievementDate())) {
			return HabitConverter.from(
					habit,
					this.save(
							habit,
							request,
							DateCalculator.findNextDate(habit.getDayOfWeeks(), request.getAchievementDate()),
							sequence),
					getTotalRewardCount(habit, sequence),
					updateMateLevel(habit, sequence));
		}

		return HabitConverter.from(
				habit,
				this.save(habit, request, lastHabitAchievement.getNextAchievementDate(), sequence),
				getTotalRewardCount(habit, sequence),
				updateMateLevel(habit, sequence));
	}

	private HabitAchievement save(
			final Habit habit,
			final SaveHabitAchievementRequest request,
			final LocalDate nextAchievementDate,
			int sequence) {
		HabitAchievementEntity entity =
				repository.save(
						HabitAchievementConverter.to(habit, request, nextAchievementDate, sequence));
		return HabitAchievementConverter.from(entity);
	}

	private Long getTotalRewardCount(final Habit habit, final Integer sequence) {
		if (sequence % PROVIDE_REWARD_COUNT == 0) {
			rewardHistoryRepository.save(RewardHistoryConverter.to(habit));
		}
		return rewardHistoryRepository.countByHabitId(habit.getId());
	}

	private Mate updateMateLevel(final Habit habit, final Integer sequence) {
		if (habit == null || habit.getMate() == null) {
			return null;
		}

		Mate mate =
				mateRepository
						.findById(habit.getMate().getId())
						.map(MateConverter::from)
						.orElseThrow(ResourceNotFoundException::new);

		if (sequence % PROVIDE_REWARD_COUNT != 0) {
			return mate;
		}

		Long rewardCount =
				rewardHistoryRepository.countByHabitIdAndCreateAtIsAfter(
								habit.getMemberId(), mate.getCreateAt())
						+ 1;

		if (this.isLevelUp(rewardCount)) {
			return MateConverter.from(
					mateRepository.save(
							MateConverter.to(
									mate.toBuilder()
											.level(LevelUpSection.MATE.getSectionMap().get(rewardCount))
											.levelUpAt(LocalDateTime.now())
											.build())));
		}

		return mate;
	}

	private boolean isLevelUp(final Long rewardCount) {
		return LevelUpSection.MATE.getSectionMap().containsKey(rewardCount);
	}
}
