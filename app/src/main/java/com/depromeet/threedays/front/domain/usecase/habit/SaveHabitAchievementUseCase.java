package com.depromeet.threedays.front.domain.usecase.habit;

import com.depromeet.threedays.data.entity.habit.HabitAchievementEntity;
import com.depromeet.threedays.front.domain.converter.RewardHistoryConverter;
import com.depromeet.threedays.front.domain.converter.habit.HabitAchievementConverter;
import com.depromeet.threedays.front.domain.converter.habit.HabitConverter;
import com.depromeet.threedays.front.domain.model.habit.Habit;
import com.depromeet.threedays.front.domain.model.habit.HabitAchievement;
import com.depromeet.threedays.front.domain.validation.HabitAchievementValidator;
import com.depromeet.threedays.front.exception.ResourceNotFoundException;
import com.depromeet.threedays.front.persistence.repository.RewardHistoryRepository;
import com.depromeet.threedays.front.persistence.repository.habit.HabitAchievementRepository;
import com.depromeet.threedays.front.persistence.repository.habit.HabitRepository;
import com.depromeet.threedays.front.support.DateCalculator;
import com.depromeet.threedays.front.web.request.habit.SaveHabitAchievementRequest;
import java.time.LocalDate;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional
@RequiredArgsConstructor
public class SaveHabitAchievementUseCase {

	private static final int PROVIDE_REWARD_COUNT = 3;
	private final HabitAchievementRepository repository;
	private final HabitRepository habitRepository;
	private final RewardHistoryRepository rewardHistoryRepository;
	private final HabitAchievementValidator validator;

	public Habit execute(Long habitId, final SaveHabitAchievementRequest request) {

		validator.validateCreateConstraints(HabitAchievementConverter.from(request));

		Habit habit =
				HabitConverter.from(
						habitRepository.findById(habitId).orElseThrow(ResourceNotFoundException::new));

		HabitAchievement lastHabitAchievement =
				HabitAchievementConverter.to(
						repository.findFirstByHabitIdOrderByAchievementDateDesc(habitId).orElse(null));

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

		if (request.getAchievementDate().isAfter(lastHabitAchievement.getNextAchievementDate())) {
			return HabitConverter.from(
					habit,
					this.save(habit, request, lastHabitAchievement.getNextAchievementDate(), 0),
					getTotalRewardCount(habit, lastHabitAchievement));
		}

		return HabitConverter.from(
				habit,
				this.save(
						habit,
						request,
						lastHabitAchievement.getNextAchievementDate(),
						lastHabitAchievement.getSequence() + 1),
				getTotalRewardCount(habit, lastHabitAchievement));
	}

	private HabitAchievement save(
			Habit habit,
			SaveHabitAchievementRequest request,
			LocalDate nextAchievementDate,
			int sequence) {
		HabitAchievementEntity entity =
				repository.save(
						HabitAchievementConverter.to(habit, request, nextAchievementDate, sequence));
		return HabitAchievementConverter.from(entity);
	}

	private Long getTotalRewardCount(Habit habit, HabitAchievement habitAchievement) {
		if (habitAchievement.getSequence() % PROVIDE_REWARD_COUNT == 0) {
			rewardHistoryRepository.save(RewardHistoryConverter.to(habit));
		}
		return rewardHistoryRepository.countByHabitId(habit.getHabitId());
	}
}
