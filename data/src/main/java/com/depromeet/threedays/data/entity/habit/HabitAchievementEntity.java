package com.depromeet.threedays.data.entity.habit;

import java.time.LocalDate;
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
@Table(name = "habit_achievement")
@EntityListeners(AuditingEntityListener.class)
public class HabitAchievementEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "habit_achievement_id")
	private Long id;

	@Column(nullable = false)
	private Long habitId;

	@Column(nullable = false)
	private Long memberId;

	@Column(nullable = false)
	private Integer sequence;

	@Column(nullable = false)
	private LocalDate achievementDate;

	@Column(nullable = false)
	private LocalDate nextAchievementDate;

	@Column(nullable = false, updatable = false)
	@CreatedDate
	private LocalDateTime createAt;

	@Column(nullable = false)
	@LastModifiedDate
	private LocalDateTime updateAt;
}
