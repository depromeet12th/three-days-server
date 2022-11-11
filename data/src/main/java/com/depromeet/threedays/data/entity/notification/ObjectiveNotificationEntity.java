package com.depromeet.threedays.data.entity.notification;

import com.depromeet.threedays.data.enums.DayOfWeek;
import java.time.LocalDateTime;
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
@Table(name = "objective_notification")
public class ObjectiveNotificationEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "objective_notification_id")
	private Long id;

	@Column(nullable = false)
	private Long memberId;

	@Column(nullable = false)
	private Long objectiveId;

	@Column(nullable = false)
	private LocalDateTime notificationTime;

	@Column(name = "contents", length = 100)
	private String contents;

	@Column(name = "day_of_week", nullable = false)
	@Enumerated(EnumType.STRING)
	private DayOfWeek dayOfWeek;
}
