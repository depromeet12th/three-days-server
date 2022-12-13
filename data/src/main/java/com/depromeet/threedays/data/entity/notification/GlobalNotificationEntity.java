package com.depromeet.threedays.data.entity.notification;

import java.time.DayOfWeek;
import java.time.LocalTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.depromeet.threedays.data.enums.NotificationType;
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
@Table(name = "global_notification")
public class GlobalNotificationEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "global_notification_id")
	private Long id;

	@Column(nullable = false)
	private LocalTime notificationTime;

	@Column(name = "title", length = 100)
	private String title;

	@Column(name = "contents", length = 100)
	private String contents;

	@Column(name = "day_of_week", nullable = false)
	@Enumerated(EnumType.STRING)
	private DayOfWeek dayOfWeek;

	@Column(name = "type")
	@Enumerated(EnumType.STRING)
	private NotificationType type;
}
