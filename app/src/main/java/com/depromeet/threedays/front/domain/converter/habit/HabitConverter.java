package com.depromeet.threedays.front.domain.converter.habit;

import com.depromeet.threedays.data.entity.habit.HabitEntity;
import com.depromeet.threedays.data.enums.HabitStatus;
import com.depromeet.threedays.front.config.security.AuditorHolder;
import com.depromeet.threedays.front.domain.model.habit.Habit;
import com.depromeet.threedays.front.domain.model.habit.HabitAchievement;
import com.depromeet.threedays.front.domain.model.habit.HabitOverview;
import com.depromeet.threedays.front.domain.model.mate.Mate;
import com.depromeet.threedays.front.domain.model.notification.Notification;
import com.depromeet.threedays.front.web.request.habit.SaveHabitRequest;
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
				.archiveNumberOfDate(0)
				.status(HabitStatus.ACTIVE)
				.deleted(false)
				.build();
	}

	public static Habit from(Habit habit, HabitAchievement habitAchievement, Long reward) {
		return Habit.builder()
				.habitId(habit.getHabitId())
				.memberId(habit.getMemberId())
				.title(habit.getTitle())
				.imojiPath(habit.getImojiPath())
				.dayOfWeeks(habit.getDayOfWeeks())
				.reward(reward)
				.createAt(habit.getCreateAt())
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
				.archiveNumberOfDate(data.getArchiveNumberOfDate())
				.status(data.getStatus())
				.deleted(data.getDeleted())
				.build();
	}

	public static Habit from(HabitEntity entity, HabitAchievement data) {
		return Habit.builder()
				.habitId(entity.getId())
				.memberId(entity.getMemberId())
				.title(entity.getTitle())
				.imojiPath(entity.getImojiPath())
				.dayOfWeeks(entity.getDayOfWeeks())
				.createAt(entity.getCreateAt())
				.habitAchievement(data)
				.build();
	}

	public static Habit from(HabitEntity entity, HabitAchievement habitAchievementData, Mate mateData) {
		return Habit.builder()
					.habitId(entity.getId())
					.memberId(entity.getMemberId())
					.title(entity.getTitle())
					.imojiPath(entity.getImojiPath())
					.dayOfWeeks(entity.getDayOfWeeks())
					.createAt(entity.getCreateAt())
					.status(entity.getStatus())
					.habitAchievement(habitAchievementData)
					.mate(mateData)
					.build();
	}

	public static Habit from(Habit data, Long reward) {
		return Habit.builder()
				.habitId(data.getHabitId())
				.memberId(data.getMemberId())
				.title(data.getTitle())
				.imojiPath(data.getImojiPath())
				.dayOfWeeks(data.getDayOfWeeks())
				.reward(reward)
				.createAt(data.getCreateAt())
				.habitAchievement(data.getHabitAchievement())
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
				.status(entity.getStatus())
				.archiveNumberOfDate(entity.getArchiveNumberOfDate())
				.notification(notification)
				.createAt(entity.getCreateAt())
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
				.createAt(entity.getCreateAt())
				.reward(rewardCount)
				.todayHabitAchievementId(achievementData.getHabitAchievementId())
				.sequence(achievementData.getSequence())
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
				.createAt(entity.getCreateAt())
				.build();
	}

	public static Habit from(HabitEntity entity, Mate data) {
		return Habit.builder()
				.habitId(entity.getId())
				.memberId(entity.getMemberId())
				.title(entity.getTitle())
				.imojiPath(entity.getImojiPath())
				.dayOfWeeks(entity.getDayOfWeeks())
				.color(entity.getColor())
				.createAt(entity.getCreateAt())
				.mate(data)
				.status(entity.getStatus())
				.build();
	}
}
