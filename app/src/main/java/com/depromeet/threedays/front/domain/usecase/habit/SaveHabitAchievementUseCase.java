package com.depromeet.threedays.front.domain.usecase.habit;

import com.depromeet.threedays.data.entity.habit.HabitAchievementEntity;
import com.depromeet.threedays.data.entity.habit.HabitEntity;
import com.depromeet.threedays.data.enums.DayOfWeek;
import com.depromeet.threedays.front.controller.request.habit.SaveHabitAchievementRequest;
import com.depromeet.threedays.front.domain.converter.habit.HabitAchievementConverter;
import com.depromeet.threedays.front.domain.converter.habit.HabitConverter;
import com.depromeet.threedays.front.domain.model.habit.Habit;
import com.depromeet.threedays.front.domain.model.habit.HabitAchievement;
import com.depromeet.threedays.front.exception.ResourceNotFoundException;
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
	private final HabitRepository habitRepository;
	private final HabitAchievementRepository habitAchievementRepository;

	public Habit execute(Long habitId, final SaveHabitAchievementRequest request) {

		LocalDate achievementDate = request.getAchievementDate();
		HabitEntity habitEntity =
				habitRepository.findById(habitId).orElseThrow(ResourceNotFoundException::new);

		HabitAchievementEntity habitAchievementEntity;
		try {
			habitAchievementEntity =
					habitAchievementRepository
							.findFirstByHabitIdOrderByAchievementDateDesc(habitId)
							.orElseThrow(ResourceNotFoundException::new);
		} catch (ResourceNotFoundException e) {
			int dayDifference = calculateDayDifference(habitEntity, achievementDate);

			HabitAchievementEntity savedHabitAchievementEntity =
					habitAchievementRepository.save(
							HabitAchievementConverter.to(habitEntity, request, dayDifference));

			HabitAchievement habitAchievement =
					HabitAchievementConverter.from(savedHabitAchievementEntity);
			return HabitConverter.from(habitEntity, habitAchievement, 3L);
		}

		return null;
	}

	private int calculateDayDifference(HabitEntity habitEntity, LocalDate achievementDate) {
		EnumSet<DayOfWeek> dayOfWeeks = habitEntity.getDayOfWeeks();
		int achievementDayOfWeek = achievementDate.getDayOfWeek().getValue();

		int count = 0;
		int dayDifference = 0;

		for (DayOfWeek dayOfWeek : dayOfWeeks) {
			int objectiveDayOfWeek = dayOfWeek.getValue();
			if (achievementDayOfWeek < objectiveDayOfWeek) {
				dayDifference = objectiveDayOfWeek - achievementDayOfWeek;
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
