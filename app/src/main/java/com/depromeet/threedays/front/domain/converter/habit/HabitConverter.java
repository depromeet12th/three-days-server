package com.depromeet.threedays.front.domain.converter.habit;

import com.depromeet.threedays.data.entity.habit.HabitEntity;
import com.depromeet.threedays.front.config.security.AuditorHolder;
import com.depromeet.threedays.front.controller.request.habit.SaveHabitRequest;
import com.depromeet.threedays.front.domain.model.habit.Habit;
import com.depromeet.threedays.front.domain.model.habit.HabitAchievement;
import com.depromeet.threedays.front.domain.model.habit.HabitOverview;
import com.depromeet.threedays.front.domain.model.mate.Mate;
import com.depromeet.threedays.front.domain.model.notification.Notification;
import java.util.Optional;
import lombok.experimental.UtilityClass;

@UtilityClass
public class HabitConverter {

	public static Habit from(SaveHabitRequest request) {

		Notification notification =
				Optional.ofNullable(request.getNotification()).orElseGet(Notification::new);

		return Habit.builder()
				.title(request.getTitle())
				.imojiPath(request.getImojiPath())
				.color(request.getColor())
				.memberId(AuditorHolder.get())
				.notification(notification)
				.dayOfWeeks(request.getDayOfWeeks())
				.build();
	}

	public static Habit from(
			Habit habit, HabitAchievement habitAchievement, Long reward) {
		return Habit.builder()
				.habitId(habit.getHabitId())
				.memberId(habit.getMemberId())
				.title(habit.getTitle())
				.imojiPath(habit.getImojiPath())
				.dayOfWeeks(habit.getDayOfWeeks())
				.reward(reward)
				.createDate(habit.getCreateDate())
				.habitAchievement(habitAchievement)
				.build();
	}

	public static HabitEntity to(Habit data) {
		return HabitEntity.builder()
				.memberId(data.getMemberId())
				.title(data.getTitle())
				.imojiPath(data.getImojiPath())
				.dayOfWeeks(data.getDayOfWeeks())
				.color(data.getColor())
				.build();
	}

	public static Habit from(HabitEntity entity, Notification notification) {

		return Habit.builder()
				.habitId(entity.getId())
				.memberId(entity.getMemberId())
				.title(entity.getTitle())
				.imojiPath(entity.getImojiPath())
				.dayOfWeeks(entity.getDayOfWeeks())
				.color(entity.getColor())
				.notification(notification)
				.createDate(entity.getCreateDate())
				.build();
	}

	public static HabitOverview from(
			HabitEntity entity, HabitAchievement achievementData, Long rewardCount, Mate mate) {

		return HabitOverview.builder()
				.habitId(entity.getId())
				.memberId(entity.getMemberId())
				.title(entity.getTitle())
				.imojiPath(entity.getImojiPath())
				.dayOfWeeks(entity.getDayOfWeeks())
				.color(entity.getColor())
				.createDate(entity.getCreateDate())
				.reward(rewardCount)
				.habitAchievement(achievementData)
				.mate(mate)
				.build();
	}

	public static Habit from(HabitEntity entity) {
		return Habit.builder()
				.habitId(entity.getId())
				.memberId(entity.getMemberId())
				.title(entity.getTitle())
				.imojiPath(entity.getImojiPath())
				.dayOfWeeks(entity.getDayOfWeeks())
				.color(entity.getColor())
				.createDate(entity.getCreateDate())
				.build();
	}
}
