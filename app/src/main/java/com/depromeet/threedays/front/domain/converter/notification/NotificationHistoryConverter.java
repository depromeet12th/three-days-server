package com.depromeet.threedays.front.domain.converter.notification;

import com.depromeet.threedays.data.entity.client.ClientEntity;
import com.depromeet.threedays.data.entity.history.NotificationHistoryEntity;
import com.depromeet.threedays.data.enums.NotificationStatus;
import com.depromeet.threedays.front.domain.model.client.Client;
import com.depromeet.threedays.front.domain.model.notification.HabitNotificationMessage;
import com.depromeet.threedays.front.domain.model.notification.NotificationMessage;
import lombok.experimental.UtilityClass;

@UtilityClass
public class NotificationHistoryConverter {
	public static NotificationHistoryEntity from(
			NotificationMessage message, ClientEntity client, NotificationStatus status) {
		return NotificationHistoryEntity.builder()
				.notificationId(message.getNotificationId())
				.contents(message.getContents())
				.status(status)
				.memberId(client.getMemberId())
				.build();
	}

	public static NotificationHistoryEntity from(
			HabitNotificationMessage message, Client client, NotificationStatus status) {
		return NotificationHistoryEntity.builder()
				.notificationId(message.getNotificationId())
				.contents(message.getContent())
				.status(status)
				.memberId(client.getMemberId())
				.build();
	}
}