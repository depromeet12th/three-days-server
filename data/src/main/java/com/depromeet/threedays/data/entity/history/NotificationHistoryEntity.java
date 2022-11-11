package com.depromeet.threedays.data.entity.history;

import com.depromeet.threedays.data.enums.NotificationStatus;
import com.depromeet.threedays.data.enums.NotificationType;
import java.time.LocalDateTime;
import java.time.LocalTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Entity
@Builder(toBuilder = true)
@Table(name = "notification_history")
public class NotificationHistoryEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "notification_history_id")
	private Long id;

	@Column(nullable = false)
	private LocalTime memberId;

	@Column(nullable = false)
	private Long notificationId;

	@Column(length = 100)
	private String contents;

	@Column(nullable = false, updatable = false)
	@Builder.Default
	private LocalDateTime createDate = LocalDateTime.now();

	@Column(name = "status", length = 100, nullable = false)
	@Enumerated(EnumType.STRING)
	private NotificationStatus status;

	@Column(name = "type")
	@Enumerated(EnumType.STRING)
	private NotificationType type;
}
