package com.depromeet.threedays.data.entity.history;

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
@Table(name = "reward_history")
@EntityListeners(AuditingEntityListener.class)
public class RewardHistoryEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "reward_history_id")
	private Long id;

	@Column(nullable = false)
	private Long habitId;

	@Column(nullable = false)
	private Long memberId;

	@Column(nullable = false, updatable = false)
	@CreatedDate
	private LocalDateTime createAt;

	@Column(nullable = false)
	@LastModifiedDate
	private LocalDateTime updateAt;
}
