package com.depromeet.threedays.front.domain.converter;

import com.depromeet.threedays.data.entity.history.NotificationHistoryEntity;
import com.depromeet.threedays.front.domain.model.notification.NotificationHistory;
import lombok.experimental.UtilityClass;

@UtilityClass
public class NotificationHistoryConverter {

	public static NotificationHistory from(final NotificationHistoryEntity entity) {
		if (entity == null) {
			return null;
		}

		return NotificationHistory.builder()
				.id(entity.getId())
				.memberId(entity.getMemberId())
				.notificationId(entity.getNotificationId())
				.contents(entity.getContents())
				.status(entity.getStatus())
				.type(entity.getType())
				.createAt(entity.getCreateAt())
				.build();
	}

	public static NotificationHistoryEntity to(final NotificationHistory data) {
		if (data == null) {
			return null;
		}

		return NotificationHistoryEntity.builder()
				.id(data.getId())
				.memberId(data.getMemberId())
				.notificationId(data.getNotificationId())
				.contents(data.getContents())
				.status(data.getStatus())
				.type(data.getType())
				.createAt(data.getCreateAt())
				.build();
	}
}
