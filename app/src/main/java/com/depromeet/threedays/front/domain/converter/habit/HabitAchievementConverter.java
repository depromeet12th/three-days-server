package com.depromeet.threedays.front.domain.converter.habit;

import com.depromeet.threedays.data.entity.habit.HabitAchievementEntity;
import com.depromeet.threedays.front.domain.model.habit.Habit;
import com.depromeet.threedays.front.domain.model.habit.HabitAchievement;
import com.depromeet.threedays.front.web.request.habit.SaveHabitAchievementRequest;
import java.time.LocalDate;
import lombok.experimental.UtilityClass;

@UtilityClass
public class HabitAchievementConverter {

	public static HabitAchievement from(HabitAchievementEntity entity) {
		if (entity == null) {
			return null;
		}

		return HabitAchievement.builder()
				.habitId(entity.getHabitId())
				.achievementDate(entity.getAchievementDate())
				.nextAchievementDate(entity.getNextAchievementDate())
				.habitAchievementId(entity.getId())
				.sequence(entity.getSequence())
				.build();
	}

	public static HabitAchievementEntity to(
			Habit habit,
			SaveHabitAchievementRequest request,
			LocalDate nextAchievementDate,
			int sequence) {
		return HabitAchievementEntity.builder()
				.habitId(habit.getHabitId())
				.memberId(habit.getMemberId())
				.achievementDate(request.getAchievementDate())
				.nextAchievementDate(nextAchievementDate)
				.sequence(sequence)
				.build();
	}

	public static HabitAchievement from(SaveHabitAchievementRequest request) {
		return HabitAchievement.builder().achievementDate(request.getAchievementDate()).build();
	}
}
