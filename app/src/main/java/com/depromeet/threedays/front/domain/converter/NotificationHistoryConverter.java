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
}
