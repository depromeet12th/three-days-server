package com.depromeet.threedays.front.web.response;

import com.depromeet.threedays.data.enums.NotificationStatus;
import com.depromeet.threedays.data.enums.NotificationType;
import com.depromeet.threedays.front.domain.model.notification.NotificationHistory;
import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class NotificationHistoryResponse {
	private Long id;
	private Long memberId;
	private Long notificationId;
	private String title;
	private String contents;
	private NotificationStatus status;
	private NotificationType type;
	private LocalDateTime createAt;

	public static NotificationHistoryResponse from(NotificationHistory notificationHistory) {
		if (notificationHistory == null) {
			return null;
		}
		return new NotificationHistoryResponse(
				notificationHistory.getId(),
				notificationHistory.getMemberId(),
				notificationHistory.getNotificationId(),
				notificationHistory.getTitle(),
				notificationHistory.getContents(),
				notificationHistory.getStatus(),
				notificationHistory.getType(),
				notificationHistory.getCreateAt());
	}
}
