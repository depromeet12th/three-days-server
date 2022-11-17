package com.depromeet.threedays.front.domain.converter.habit;

import com.depromeet.threedays.data.entity.habit.HabitAchievementEntity;
import com.depromeet.threedays.data.entity.habit.HabitEntity;
import com.depromeet.threedays.front.controller.request.habit.SaveHabitAchievementRequest;
import com.depromeet.threedays.front.domain.model.habit.HabitAchievement;
import lombok.experimental.UtilityClass;

@UtilityClass
public class HabitAchievementConverter {

	public static HabitAchievement from(HabitAchievementEntity entity) {
		return HabitAchievement.builder()
				.achievementDate(entity.getAchievementDate())
				.nextAchievementDate(entity.getNextAchievementDate())
				.habitAchievementId(entity.getId())
				.sequence(entity.getSequence())
				.build();
	}

	public static HabitAchievementEntity to(HabitEntity habitEntity, SaveHabitAchievementRequest request, int dayDifference) {
		return HabitAchievementEntity.builder()
									 .habitId(habitEntity.getId())
									 .memberId(habitEntity.getMemberId())
									 .achievementDate(request.getAchievementDate())
									 .nextAchievementDate(request.getAchievementDate()
																 .plusDays(dayDifference))
									 .sequence(1)
									 .build();
	}

	public static HabitAchievementEntity to(HabitEntity habitEntity, HabitAchievementEntity habitAchievementEntity, SaveHabitAchievementRequest request) {
		return HabitAchievementEntity.builder()
									 .habitId(habitEntity.getId())
									 .memberId(habitEntity.getMemberId())
									 .achievementDate(request.getAchievementDate())
									 .nextAchievementDate(habitAchievementEntity.getNextAchievementDate())
									 .sequence(habitAchievementEntity.getSequence() + 1)
									 .build();
	}
}
