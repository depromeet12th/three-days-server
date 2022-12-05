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
import lombok.experimental.UtilityClass;

@UtilityClass
public class HabitConverter {

	public static Habit from(final SaveHabitRequest request) {
		if (request == null) {
			return null;
		}

		return Habit.builder()
				.title(request.getTitle())
				.imojiPath(request.getImojiPath())
				.color(request.getColor())
				.memberId(AuditorHolder.get())
				.notification(request.getNotification())
				.dayOfWeeks(request.getDayOfWeeks())
				.archiveNumberOfDate(0)
				.status(HabitStatus.ACTIVE)
				.deleted(false)
				.build();
	}

	public static Habit from(
			final Habit habit, final HabitAchievement habitAchievement, final Long reward) {
		if (habit == null) {
			return null;
		}

		return Habit.builder()
				.id(habit.getId())
				.memberId(habit.getMemberId())
				.title(habit.getTitle())
				.imojiPath(habit.getImojiPath())
				.dayOfWeeks(habit.getDayOfWeeks())
				.reward(reward)
				.createAt(habit.getCreateAt())
				.habitAchievement(habitAchievement)
				.mate(habit.getMate())
				.build();
	}

	public static Habit from(
			final Habit habit,
			final HabitAchievement habitAchievement,
			final Long reward,
			final Mate mate) {
		return Habit.builder()
				.id(habit.getId())
				.memberId(habit.getMemberId())
				.title(habit.getTitle())
				.imojiPath(habit.getImojiPath())
				.dayOfWeeks(habit.getDayOfWeeks())
				.reward(reward)
				.createAt(habit.getCreateAt())
				.habitAchievement(habitAchievement)
				.mate(mate)
				.build();
	}

	public static HabitEntity to(final Habit data) {
		if (data == null) {
			return null;
		}

		return HabitEntity.builder()
				.id(data.getId())
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

	public static Habit from(final HabitEntity entity, final HabitAchievement data) {
		if (entity == null) {
			return null;
		}

		return Habit.builder()
				.id(entity.getId())
				.memberId(entity.getMemberId())
				.title(entity.getTitle())
				.imojiPath(entity.getImojiPath())
				.dayOfWeeks(entity.getDayOfWeeks())
				.createAt(entity.getCreateAt())
				.habitAchievement(data)
				.build();
	}

	public static Habit from(final Habit data, final Long reward) {
		return Habit.builder()
				.id(data.getId())
				.memberId(data.getMemberId())
				.title(data.getTitle())
				.imojiPath(data.getImojiPath())
				.dayOfWeeks(data.getDayOfWeeks())
				.reward(reward)
				.createAt(data.getCreateAt())
				.habitAchievement(data.getHabitAchievement())
				.build();
	}

	public static Habit from(final HabitEntity entity, final Notification notification) {
		if (entity == null) {
			return null;
		}

		return Habit.builder()
				.id(entity.getId())
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
		if (entity == null) {
			return null;
		}

		return HabitOverview.builder()
				.id(entity.getId())
				.memberId(entity.getMemberId())
				.title(entity.getTitle())
				.imojiPath(entity.getImojiPath())
				.dayOfWeeks(entity.getDayOfWeeks())
				.color(entity.getColor())
				.createAt(entity.getCreateAt())
				.reward(rewardCount)
				.status(entity.getStatus())
				.todayHabitAchievementId(achievementData.getId())
				.sequence(achievementData.getSequence())
				.mate(mate)
				.build();
	}

	public static Habit from(HabitEntity entity) {
		return Habit.builder()
				.id(entity.getId())
				.memberId(entity.getMemberId())
				.title(entity.getTitle())
				.imojiPath(entity.getImojiPath())
				.dayOfWeeks(entity.getDayOfWeeks())
				.archiveNumberOfDate(entity.getArchiveNumberOfDate())
				.color(entity.getColor())
				.status(entity.getStatus())
				.createAt(entity.getCreateAt())
				.deleted(entity.getDeleted())
				.build();
	}
}
