package com.depromeet.threedays.front.domain.model.notification;

import com.depromeet.threedays.data.enums.NotificationType;
import java.time.LocalTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class NotificationMessage {
	private Long notificationId;
	private LocalTime notificationTime;
	private String contents;
	private String title;
	private NotificationType type;
}
