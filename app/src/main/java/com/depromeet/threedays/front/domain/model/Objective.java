package com.depromeet.threedays.front.domain.model;

import com.depromeet.threedays.data.enums.DayOfWeek;
import java.time.LocalDateTime;
import java.util.EnumSet;
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
public class Objective {

	private Long objectiveId;
	private Long memberId;
	private String title;
	private String imojiPath;
	private String color;
	private EnumSet<DayOfWeek> dayOfWeeks;
	@Builder.Default private Integer reward = 0;
	private LocalDateTime createDate;
	private ObjectiveAchievement objectiveAchievement;
	private Partner partner;
	private Notification notification;
}
