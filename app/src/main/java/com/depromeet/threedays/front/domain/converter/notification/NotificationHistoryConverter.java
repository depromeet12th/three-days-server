package com.depromeet.threedays.front.domain.converter.notification;

import com.depromeet.threedays.data.entity.client.ClientEntity;
import com.depromeet.threedays.data.entity.history.NotificationHistoryEntity;
import com.depromeet.threedays.data.enums.NotificationStatus;
import com.depromeet.threedays.data.enums.NotificationType;
import com.depromeet.threedays.front.domain.model.client.Client;
import com.depromeet.threedays.front.domain.model.notification.HabitNotificationMessage;
import com.depromeet.threedays.front.domain.model.notification.NotificationMessage;
import java.time.LocalDateTime;
import lombok.experimental.UtilityClass;

@UtilityClass
public class NotificationHistoryConverter {

	public static NotificationHistoryEntity from(
			NotificationMessage message, ClientEntity client, NotificationStatus status) {
		return NotificationHistoryEntity.builder()
				.notificationId(message.getNotificationId())
				.title(message.getTitle())
				.contents(message.getContents())
				.status(status)
				.type(message.getType())
				.memberId(client.getMemberId())
				.build();
	}

	public static NotificationHistoryEntity from(
			HabitNotificationMessage message, Client client, NotificationStatus status) {
		return NotificationHistoryEntity.builder()
				.notificationId(message.getNotificationId())
				.title(message.getTitle())
				.contents(message.getContent())
				.status(status)
				.type(NotificationType.HABIT)
				.memberId(client.getMemberId())
				.build();
	}

	public static NotificationHistoryEntity from(
			final HabitNotificationMessage data, NotificationStatus status) {
		if (data == null) {
			return null;
		}

		return NotificationHistoryEntity.builder()
				.memberId(data.getMemberId())
				.notificationId(data.getNotificationId())
				.title(data.getTitle())
				.contents(data.getContent())
				.status(status)
				.type(NotificationType.HABIT)
				.createAt(LocalDateTime.now())
				.build();
	}
}
