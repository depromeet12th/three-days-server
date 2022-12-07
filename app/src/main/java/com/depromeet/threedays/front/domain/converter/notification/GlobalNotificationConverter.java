package com.depromeet.threedays.front.domain.converter.notification;

import com.depromeet.threedays.data.entity.notification.GlobalNotificationEntity;
import com.depromeet.threedays.front.domain.model.notification.NotificationMessage;
import com.depromeet.threedays.front.web.request.notification.GlobalNotificationMessage;
import java.time.LocalTime;
import lombok.experimental.UtilityClass;

@UtilityClass
public class GlobalNotificationConverter {
	public static GlobalNotificationEntity to(GlobalNotificationMessage request) {
		return GlobalNotificationEntity.builder()
				.title(request.getTitle())
				.contents(request.getContents())
				.notificationTime(LocalTime.now())
				.build();
	}

	public static NotificationMessage from(GlobalNotificationEntity entity) {
		return NotificationMessage.builder()
				.notificationId(entity.getId())
				.contents(entity.getContents())
				.notificationTime(entity.getNotificationTime())
				.title(entity.getTitle())
				.build();
	}
}
