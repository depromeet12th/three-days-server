package com.depromeet.threedays.front.domain.model;

import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class ObjectiveAchievement {

	private Long objectiveAchievementId;
	private Integer sequence;
	private LocalDate achievementDate;
	private LocalDate nextAchievementDate;

}
