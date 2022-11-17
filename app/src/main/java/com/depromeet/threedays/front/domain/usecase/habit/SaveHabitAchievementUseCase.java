package com.depromeet.threedays.front.domain.usecase.habit;

import com.depromeet.threedays.data.entity.habit.HabitAchievementEntity;
import com.depromeet.threedays.data.entity.habit.HabitEntity;
import com.depromeet.threedays.data.enums.DayOfWeek;
import com.depromeet.threedays.front.controller.request.habit.SaveHabitAchievementRequest;
import com.depromeet.threedays.front.domain.converter.habit.HabitAchievementConverter;
import com.depromeet.threedays.front.domain.converter.habit.HabitConverter;
import com.depromeet.threedays.front.domain.converter.RewardHistoryConverter;
import com.depromeet.threedays.front.domain.model.habit.Habit;
import com.depromeet.threedays.front.domain.model.habit.HabitAchievement;
import com.depromeet.threedays.front.exception.ResourceNotFoundException;
import com.depromeet.threedays.front.repository.RewardHistoryRepository;
import com.depromeet.threedays.front.repository.habit.HabitAchievementRepository;
import com.depromeet.threedays.front.repository.habit.HabitRepository;
import java.time.LocalDate;
import java.util.EnumSet;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional
@RequiredArgsConstructor
public class SaveHabitAchievementUseCase {
	private static final int PROVIDE_REWARD_COUNT = 3;
	private final HabitRepository habitRepository;
	private final HabitAchievementRepository habitAchievementRepository;
	private final RewardHistoryRepository rewardHistoryRepository;

	public Habit execute(Long habitId, final SaveHabitAchievementRequest request) {
		LocalDate achievementDate = request.getAchievementDate();
		HabitEntity habitEntity =
				habitRepository.findById(habitId).orElseThrow(ResourceNotFoundException::new);

		int dayDifference = calculateDayDifference(habitEntity, achievementDate);

		HabitAchievementEntity habitAchievementEntity;
		try {
			habitAchievementEntity =
					habitAchievementRepository
							.findFirstByHabitIdOrderByAchievementDateDesc(habitId)
							.orElseThrow(ResourceNotFoundException::new);
		} catch (ResourceNotFoundException e) {
			HabitAchievementEntity savedHabitAchievementEntity =
					habitAchievementRepository.save(
							HabitAchievementConverter.to(habitEntity, request, dayDifference));

			HabitAchievement habitAchievement =
					HabitAchievementConverter.from(savedHabitAchievementEntity);

			Long totalReward = calculateTotalReward(habitEntity, habitAchievement);
			return HabitConverter.from(habitEntity, habitAchievement, totalReward);
		}

		LocalDate nextAchievementDate = habitAchievementEntity.getNextAchievementDate();

		if (achievementDate.isBefore(nextAchievementDate)) {
			HabitAchievementEntity savedHabitAchievementEntity =
					habitAchievementRepository.save(
							HabitAchievementConverter.to(habitEntity, habitAchievementEntity, request));

			HabitAchievement habitAchievement =
					HabitAchievementConverter.from(savedHabitAchievementEntity);

			Long totalReward = calculateTotalReward(habitEntity, habitAchievement);
			return HabitConverter.from(habitEntity, habitAchievement, totalReward);
		}

		if (achievementDate.isEqual(nextAchievementDate)) {
			HabitAchievementEntity savedHabitAchievementEntity =
					habitAchievementRepository.save(
							HabitAchievementConverter.to(
									habitEntity, habitAchievementEntity, request, dayDifference));

			HabitAchievement habitAchievement =
					HabitAchievementConverter.from(savedHabitAchievementEntity);

			Long totalReward = calculateTotalReward(habitEntity, habitAchievement);
			return HabitConverter.from(habitEntity, habitAchievement, totalReward);
		}

		if (achievementDate.isAfter(nextAchievementDate)) {
			HabitAchievementEntity savedHabitAchievementEntity =
					habitAchievementRepository.save(
							HabitAchievementConverter.to(habitEntity, request, dayDifference));

			HabitAchievement habitAchievement =
					HabitAchievementConverter.from(savedHabitAchievementEntity);

			Long totalReward = calculateTotalReward(habitEntity, habitAchievement);
			return HabitConverter.from(habitEntity, habitAchievement, totalReward);
		}
		return null;
	}

	private Long calculateTotalReward(HabitEntity habitEntity, HabitAchievement habitAchievement) {
		if(habitAchievement.getSequence() % PROVIDE_REWARD_COUNT == 0) {
			rewardHistoryRepository.save(RewardHistoryConverter.to(habitEntity, habitAchievement));
		}
		return rewardHistoryRepository.countByHabitId(habitEntity.getId());
	}

	private int calculateDayDifference(HabitEntity habitEntity, LocalDate achievementDate) {
		EnumSet<DayOfWeek> dayOfWeeks = habitEntity.getDayOfWeeks();
		int achievementDayOfWeek = achievementDate.getDayOfWeek().getValue();

		int count = 0;
		int dayDifference = 0;

		for (DayOfWeek dayOfWeek : dayOfWeeks) {
			int habitDayOfWeek = dayOfWeek.getValue();
			if (achievementDayOfWeek < habitDayOfWeek) {
				dayDifference = habitDayOfWeek - achievementDayOfWeek;
				break;
			}
			count++;
		}

		if (count == dayOfWeeks.size()) {
			for (DayOfWeek dayOfWeek : dayOfWeeks) {
				int habitDayOfWeek = dayOfWeek.getValue();
				dayDifference = (habitDayOfWeek + 7) - achievementDayOfWeek;
				break;
			}
		}
		return dayDifference;
	}
}
