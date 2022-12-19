package com.depromeet.threedays.data.entity.habit;

import java.time.LocalDate;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
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
@Table(name = "habit_achievement")
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
	@Builder.Default
	private LocalDateTime createAt = LocalDateTime.now();

	@Column(nullable = false)
	private LocalDateTime updateAt;
}
