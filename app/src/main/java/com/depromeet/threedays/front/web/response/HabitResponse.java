package com.depromeet.threedays.front.web.response;

import com.depromeet.threedays.data.enums.HabitStatus;
import com.depromeet.threedays.front.domain.model.mate.Mate;
import com.depromeet.threedays.front.domain.model.notification.Notification;
import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.util.EnumSet;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class HabitResponse {

	private Long habitId;
	private Long memberId;
	private String title;
	private String imojiPath;
	private String color;
	private EnumSet<DayOfWeek> dayOfWeeks;
	private Long reward;
	private Integer sequence;
	private HabitStatus status;
	private Long totalAchievementCount;
	private Long todayHabitAchievementId;
	private Mate mate;
	private Notification notification;
	private LocalDateTime createAt;
}
