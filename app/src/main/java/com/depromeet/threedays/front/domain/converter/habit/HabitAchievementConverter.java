package com.depromeet.threedays.front.domain.converter.habit;

import com.depromeet.threedays.data.entity.habit.HabitAchievementEntity;
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
}
