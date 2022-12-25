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
import com.depromeet.threedays.front.web.request.habit.UpdateHabitRequest;
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
				.color(habit.getColor())
				.imojiPath(habit.getImojiPath())
				.dayOfWeeks(habit.getDayOfWeeks())
				.reward(reward)
				.createAt(habit.getCreateAt())
				.archiveAt(habit.getArchiveAt())
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
				.status(habit.getStatus())
				.imojiPath(habit.getImojiPath())
				.dayOfWeeks(habit.getDayOfWeeks())
				.color(habit.getColor())
				.reward(reward)
				.createAt(habit.getCreateAt())
				.archiveAt(habit.getArchiveAt())
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
				.color(entity.getColor())
				.imojiPath(entity.getImojiPath())
				.dayOfWeeks(entity.getDayOfWeeks())
				.createAt(entity.getCreateAt())
				.archiveAt(entity.getArchiveAt())
				.habitAchievement(data)
				.build();
	}

	public static Habit from(final Habit data, final Long reward) {
		return Habit.builder()
				.id(data.getId())
				.memberId(data.getMemberId())
				.title(data.getTitle())
				.color(data.getColor())
				.imojiPath(data.getImojiPath())
				.dayOfWeeks(data.getDayOfWeeks())
				.reward(reward)
				.createAt(data.getCreateAt())
				.archiveAt(data.getArchiveAt())
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
				.archiveAt(entity.getArchiveAt())
				.build();
	}

	public static HabitOverview from(
			HabitEntity entity,
			HabitAchievement achievementData,
			Long rewardCount,
			Mate mate,
			Long totalHabitAchievement) {
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
				.archiveAt(entity.getArchiveAt())
				.reward(rewardCount)
				.status(entity.getStatus())
				.totalAchievementCount(totalHabitAchievement.intValue())
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
				.archiveAt(entity.getArchiveAt())
				.deleted(entity.getDeleted())
				.build();
	}

	public static Habit from(final Habit source, final UpdateHabitRequest request) {
		if (source == null || request == null) {
			return null;
		}

		return Habit.builder()
				.id(source.getId())
				.memberId(source.getMemberId())
				.title(request.getTitle())
				.imojiPath(request.getImojiPath())
				.dayOfWeeks(request.getDayOfWeeks())
				.archiveNumberOfDate(source.getArchiveNumberOfDate())
				.color(request.getColor())
				.status(source.getStatus())
				.createAt(source.getCreateAt())
				.archiveAt(source.getArchiveAt())
				.deleted(source.getDeleted())
				.build();
	}

	public static HabitEntity to(final Habit source, final UpdateHabitRequest request) {
		if (source == null || request == null) {
			return null;
		}

		return HabitEntity.builder()
				.id(source.getId())
				.memberId(source.getMemberId())
				.title(request.getTitle())
				.imojiPath(request.getImojiPath())
				.dayOfWeeks(request.getDayOfWeeks())
				.archiveNumberOfDate(source.getArchiveNumberOfDate())
				.color(request.getColor())
				.status(source.getStatus())
				.deleted(source.getDeleted())
				.build();
	}
}
