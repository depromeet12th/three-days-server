package com.depromeet.threedays.front.domain.model.habit;

import com.depromeet.threedays.data.enums.HabitStatus;
import com.depromeet.threedays.front.domain.model.mate.Mate;
import java.io.Serializable;
import java.time.DayOfWeek;
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
public class HabitOverview implements Serializable {

	private Long id;
	private Long memberId;
	private String title;
	private String imojiPath;
	private EnumSet<DayOfWeek> dayOfWeeks;
	private Long reward;
	private String color;
	private HabitStatus status;
	private LocalDateTime createAt;
	private LocalDateTime archiveAt;
	private Integer totalAchievementCount;
	private Long todayHabitAchievementId;
	private Integer sequence;
	private Mate mate;
}
