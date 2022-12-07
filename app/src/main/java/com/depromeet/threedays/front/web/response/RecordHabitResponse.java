package com.depromeet.threedays.front.web.response;

import com.depromeet.threedays.data.enums.HabitStatus;
import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.util.EnumSet;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.With;

@Getter
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
@With
public class RecordHabitResponse {

	private Long id;
	private Long memberId;
	private String title;
	private String imojiPath;
	private String color;
	private EnumSet<DayOfWeek> dayOfWeeks;
	private HabitStatus status;
	private LocalDateTime createAt;
}
