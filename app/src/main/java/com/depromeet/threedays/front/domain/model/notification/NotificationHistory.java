package com.depromeet.threedays.front.domain.model.notification;

import com.depromeet.threedays.data.enums.NotificationStatus;
import com.depromeet.threedays.data.enums.NotificationType;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.With;

@Getter
@With
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class NotificationHistory {

	private Long id;
	private Long memberId;
	private Long notificationId;
	private String title;
	private String contents;
	private NotificationStatus status;
	private NotificationType type;
	private LocalDateTime createAt;
}
