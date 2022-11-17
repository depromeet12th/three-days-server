package com.depromeet.threedays.front.domain.converter.notification;

import com.depromeet.threedays.data.entity.notification.HabitNotificationEntity;
import com.depromeet.threedays.front.config.security.AuditorHolder;
import com.depromeet.threedays.front.domain.model.notification.Notification;
import java.time.DayOfWeek;
import lombok.experimental.UtilityClass;

@UtilityClass
public class HabitNotificationConverter {

	public static HabitNotificationEntity to(Notification data, Long habitId, DayOfWeek dayOfWeek) {
		return HabitNotificationEntity.builder()
				.habitId(habitId)
				.memberId(AuditorHolder.get())
				.contents(data.getContents())
				.notificationTime(data.getNotificationTime())
				.dayOfWeek(dayOfWeek)
				.build();
	}
}
