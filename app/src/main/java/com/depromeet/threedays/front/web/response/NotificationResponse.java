package com.depromeet.threedays.front.web.response;

import com.depromeet.threedays.front.domain.model.notification.Notification;
import java.time.LocalTime;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class NotificationResponse {
	private final LocalTime notificationTime;
	private final String contents;

	public static NotificationResponse from(Notification notification) {
		if (notification == null) {
			return null;
		}
		return new NotificationResponse(notification.getNotificationTime(), notification.getContents());
	}
}
