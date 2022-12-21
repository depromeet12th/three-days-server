package com.depromeet.threedays.data.entity.notification;

import com.depromeet.threedays.data.enums.NotificationType;
import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.LocalTime;
import javax.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Entity
@Builder(toBuilder = true)
@Table(name = "global_notification")
@EntityListeners(AuditingEntityListener.class)
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

	@Column(nullable = false, updatable = false)
	@CreatedDate
	private LocalDateTime createAt;

	@Column(nullable = false)
	@LastModifiedDate
	private LocalDateTime updateAt;
}
