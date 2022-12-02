package com.depromeet.threedays.front.web.response.converter;

import com.depromeet.threedays.front.domain.model.habit.Habit;
import com.depromeet.threedays.front.domain.model.habit.HabitAchievement;
import com.depromeet.threedays.front.web.response.HabitResponse;
import java.util.Optional;
import lombok.experimental.UtilityClass;

@UtilityClass
public class HabitResponseConverter {

	public static HabitResponse from(Habit habit) {

		HabitAchievement habitAchievement =
				Optional.ofNullable(habit.getHabitAchievement()).orElseGet(HabitAchievement::new);

		return HabitResponse.builder()
				.habitId(habit.getHabitId())
				.memberId(habit.getMemberId())
				.title(habit.getTitle())
				.imojiPath(habit.getImojiPath())
				.color(habit.getColor())
				.dayOfWeeks(habit.getDayOfWeeks())
				.reward(habit.getReward())
				.status(habit.getStatus())
				.totalAchievementCount(habit.getTotalAchievementCount())
				.sequence(habitAchievement.getSequence())
				.todayHabitAchievementId(habitAchievement.getHabitAchievementId())
				.mate(habit.getMate())
				.notification(habit.getNotification())
				.createAt(habit.getCreateAt())
				.build();
	}
}
