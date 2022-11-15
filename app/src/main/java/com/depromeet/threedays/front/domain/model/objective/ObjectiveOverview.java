package com.depromeet.threedays.front.domain.model.objective;

import com.depromeet.threedays.data.enums.DayOfWeek;
import com.depromeet.threedays.front.domain.model.partner.Partner;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.EnumSet;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class ObjectiveOverview implements Serializable {

	private Long objectiveId;
	private Long memberId;
	private String title;
	private String imojiPath;
	private EnumSet<DayOfWeek> dayOfWeeks;
	private Long reward;
	private String color;
	private LocalDateTime createDate;
	private ObjectiveAchievement objectiveAchievement;
	private Partner partner;
}
