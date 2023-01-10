package com.depromeet.threedays.front.domain.usecase.habit;

import com.depromeet.threedays.data.entity.habit.HabitAchievementEntity;
import com.depromeet.threedays.data.entity.habit.HabitEntity;
import com.depromeet.threedays.data.enums.HabitStatus;
import com.depromeet.threedays.data.enums.MateStatus;
import com.depromeet.threedays.data.enums.MemberStatus;
import com.depromeet.threedays.front.config.security.AuditorHolder;
import com.depromeet.threedays.front.domain.converter.habit.HabitConverter;
import com.depromeet.threedays.front.domain.model.habit.HabitAchievement;
import com.depromeet.threedays.front.domain.model.habit.HabitOverview;
import com.depromeet.threedays.front.domain.model.mate.Mate;
import com.depromeet.threedays.front.exception.MemberNotFoundException;
import com.depromeet.threedays.front.persistence.repository.RewardHistoryRepository;
import com.depromeet.threedays.front.persistence.repository.habit.HabitAchievementRepository;
import com.depromeet.threedays.front.persistence.repository.habit.HabitRepository;
import com.depromeet.threedays.front.persistence.repository.mate.MateRepository;
import com.depromeet.threedays.front.persistence.repository.member.MemberRepository;
import com.depromeet.threedays.front.web.request.habit.SearchHabitRequest;
import com.depromeet.threedays.front.web.response.assembler.MateAssembler;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class SearchHabitUseCase {

	private final MemberRepository memberRepository;
	private final HabitRepository repository;
	private final HabitAchievementRepository habitAchievementRepository;
	private final RewardHistoryRepository rewardHistoryRepository;
	private final MateRepository mateRepository;
	private final MateAssembler mateAssembler;

	public List<HabitOverview> execute(final SearchHabitRequest request) {
		Long memberId = AuditorHolder.get();
		memberRepository
				.findByIdAndStatus(memberId, MemberStatus.REGULAR)
				.orElseThrow(() -> new MemberNotFoundException(memberId));

		List<HabitEntity> habitEntities = new ArrayList<>();
		List<HabitOverview> habitOverviews = new ArrayList<>();

		if (request.getStatus() == null) {
			habitEntities = repository.findAllByMemberIdAndDeletedFalse(AuditorHolder.get()).orElse(null);
		}

		if (request.getStatus() != null) {
			habitEntities =
					repository.findAllByMemberIdAndDeletedFalseAndStatus(
							AuditorHolder.get(), request.getStatus());
		}

		for (HabitEntity habitEntity : habitEntities) {
			habitOverviews.add(this.setAssociation(habitEntity));
		}

		return habitOverviews;
	}

	private HabitOverview setAssociation(final HabitEntity entity) {
		Long habitId = entity.getId();
		HabitAchievementEntity achievementEntity =
				habitAchievementRepository
						.findFirstByHabitIdOrderByAchievementDateDesc(habitId)
						.orElse(null);
		HabitAchievement achievementData = this.calculateSequence(achievementEntity);
		Long rewardCount = rewardHistoryRepository.countByHabitId(habitId);
		Long totalAchievementCount = habitAchievementRepository.countByHabitId(habitId);

		return HabitConverter.from(
				entity,
				achievementData,
				rewardCount,
				getMate(entity.getStatus(), entity.getId()),
				totalAchievementCount);
	}

	private Mate getMate(final HabitStatus status, final Long habitId) {

		if (status.equals(HabitStatus.ACTIVE)) {
			return mateRepository
					.findFirstByHabitIdAndStatusAndDeletedFalseOrderByCreateAtDesc(habitId, MateStatus.ACTIVE)
					.map(mateAssembler::toMate)
					.orElse(null);
		}

		return mateRepository
				.findFirstByHabitIdAndStatusOrderByCreateAtDesc(habitId, MateStatus.ARCHIVED)
				.map(mateAssembler::toMate)
				.orElse(null);
	}

	private HabitAchievement calculateSequence(HabitAchievementEntity achievementEntity) {
		if (achievementEntity == null
				|| achievementEntity.getNextAchievementDate().isBefore(LocalDate.now())) {
			return HabitAchievement.builder().sequence(0).build();
		}

		if (achievementEntity.getAchievementDate().isEqual(LocalDate.now())) {
			return HabitAchievement.builder()
					.id(achievementEntity.getId())
					.sequence(achievementEntity.getSequence())
					.build();
		}

		return HabitAchievement.builder().sequence(achievementEntity.getSequence()).build();
	}
}
