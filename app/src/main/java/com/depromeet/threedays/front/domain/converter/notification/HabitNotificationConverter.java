package com.depromeet.threedays.front.domain.converter.notification;

import com.depromeet.threedays.data.entity.notification.HabitNotificationEntity;
import com.depromeet.threedays.front.config.security.AuditorHolder;
import com.depromeet.threedays.front.domain.model.habit.Habit;
import com.depromeet.threedays.front.domain.model.notification.HabitNotificationMessage;
import com.depromeet.threedays.front.domain.model.notification.Notification;
import java.time.DayOfWeek;
import lombok.experimental.UtilityClass;
import org.springframework.util.Assert;

@UtilityClass
public class HabitNotificationConverter {

	public static HabitNotificationEntity to(
			Habit habit, Notification notification, DayOfWeek dayOfWeek) {
		Assert.notNull(habit, "'habit' must not be null");
		Assert.notNull(notification, "'notification' must not be null");
		return HabitNotificationEntity.builder()
				.habitId(habit.getId())
				.memberId(AuditorHolder.get())
				.title(habit.resolveNotificationTitle())
				.contents(notification.getContents())
				.notificationTime(notification.getNotificationTime())
				.dayOfWeek(dayOfWeek)
				.build();
	}

	public static Notification from(HabitNotificationEntity entity) {
		if (entity == null) {
			return null;
		}

		return Notification.builder()
				.notificationTime(entity.getNotificationTime())
				.contents(entity.getContents())
				.build();
	}

	public static HabitNotificationMessage habitMessageFrom(HabitNotificationEntity entity) {
		if (entity == null) {
			return null;
		}

		return HabitNotificationMessage.builder()
				.notificationId(entity.getId())
				.habitId(entity.getHabitId())
				.title(entity.getTitle())
				.content(entity.getContents())
				.notificationTime(entity.getNotificationTime())
				.memberId(entity.getMemberId())
				.build();
	}
}
