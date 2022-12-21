package com.depromeet.threedays.data.entity.history;

import com.depromeet.threedays.data.enums.NotificationStatus;
import com.depromeet.threedays.data.enums.NotificationType;
import java.time.LocalDateTime;
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
@Table(name = "notification_history")
@EntityListeners(AuditingEntityListener.class)
public class NotificationHistoryEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "notification_history_id")
	private Long id;

	@Column(nullable = false)
	private Long memberId;

	@Column(nullable = false)
	private Long notificationId;

	@Column(length = 100)
	private String contents;

	@Column(nullable = false, updatable = false)
	@CreatedDate
	private LocalDateTime createAt;

	@Column(nullable = false)
	@LastModifiedDate
	private LocalDateTime updateAt;

	@Column(name = "status", length = 100, nullable = false)
	@Enumerated(EnumType.STRING)
	private NotificationStatus status;

	@Column(name = "type")
	@Enumerated(EnumType.STRING)
	private NotificationType type;
}
