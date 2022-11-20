package com.depromeet.threedays.front.domain.model.habit;

import com.depromeet.threedays.data.enums.HabitStatus;
import com.depromeet.threedays.front.domain.model.mate.Mate;
import com.depromeet.threedays.front.domain.model.notification.Notification;
import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.util.EnumSet;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class Habit {

	private Long habitId;
	private Long memberId;
	private String title;
	private String imojiPath;
	private String color;
	private EnumSet<DayOfWeek> dayOfWeeks;
	@Builder.Default private Long reward = 0L;
	private Integer archiveNumberOfDate;
	private Long totalAchievementCount;
	private HabitStatus status;
	private HabitAchievement habitAchievement;
	private LocalDateTime createAt;
	private Boolean deleted;
	private Mate mate;
	private Notification notification;
}
