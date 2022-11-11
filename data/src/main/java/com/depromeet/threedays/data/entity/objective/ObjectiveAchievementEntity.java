package com.depromeet.threedays.data.entity.objective;

import java.time.LocalDate;
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
@Table(name = "objective_achievement")
public class ObjectiveAchievementEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "objective_achievement_id")
	private Long id;

	@Column(nullable = false)
	private Long objectiveId;

	@Column(nullable = false)
	private Long memberId;

	@Column(nullable = false)
	private Integer sequence;

	@Column(nullable = false)
	private LocalDate achievementDate;

	@Column(nullable = false)
	private LocalDate nextAchievementDate;
}
