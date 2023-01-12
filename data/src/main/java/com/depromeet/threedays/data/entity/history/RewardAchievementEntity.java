package com.depromeet.threedays.data.entity.history;

import java.time.LocalDateTime;
import javax.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Entity
@Builder(toBuilder = true)
@Table(name = "reward_achievement")
@EntityListeners(AuditingEntityListener.class)
public class RewardAchievementEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "reward_achievement_id")
	private Long id;

	@Column(nullable = false)
	private Long rewardId;

	@Column(nullable = false)
	private Long habitAchievementId;

	@Column(nullable = false, updatable = false)
	@CreatedDate
	private LocalDateTime createAt;

	@Column(nullable = false)
	@LastModifiedDate
	private LocalDateTime updateAt;
}
