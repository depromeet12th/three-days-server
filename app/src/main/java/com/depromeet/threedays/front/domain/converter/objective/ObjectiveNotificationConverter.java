package com.depromeet.threedays.front.domain.converter.objective;

import com.depromeet.threedays.data.entity.notification.ObjectiveNotificationEntity;
import com.depromeet.threedays.data.enums.DayOfWeek;
import com.depromeet.threedays.front.config.security.AuditorHolder;
import com.depromeet.threedays.front.domain.model.Notification;
import lombok.experimental.UtilityClass;

@UtilityClass
public class ObjectiveNotificationConverter {

	public static ObjectiveNotificationEntity to(
			Notification data, Long objectiveId, DayOfWeek dayOfWeek) {
		return ObjectiveNotificationEntity.builder()
				.objectiveId(objectiveId)
				.memberId(AuditorHolder.get())
				.contents(data.getContents())
				.notificationTime(data.getNotificationTime())
				.dayOfWeek(dayOfWeek)
				.build();
	}
}
