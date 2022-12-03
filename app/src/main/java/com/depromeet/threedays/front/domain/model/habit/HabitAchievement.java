package com.depromeet.threedays.front.domain.model.habit;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import java.io.Serializable;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class HabitAchievement implements Serializable {

	private Long id;

	private Long habitId;
	@Builder.Default
	private Integer sequence = 0;

	@JsonInclude(Include.NON_NULL)
	private LocalDate achievementDate;

	@JsonInclude(Include.NON_NULL)
	private LocalDate nextAchievementDate;
}
