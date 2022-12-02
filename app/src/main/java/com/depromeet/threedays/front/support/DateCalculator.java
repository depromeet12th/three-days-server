package com.depromeet.threedays.front.support;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.TemporalAdjusters;
import java.util.Set;
import lombok.experimental.UtilityClass;

@UtilityClass
public class DateCalculator {

	public static LocalDate findNextDate(Set<DayOfWeek> dayOfWeeks, LocalDate date) {
		LocalDate nextDate = LocalDate.MAX;

		for (DayOfWeek dayOfWeek : dayOfWeeks) {
			LocalDate nextDayOfWeekDate = date.with(TemporalAdjusters.next(dayOfWeek));
			if (nextDate.isAfter(nextDayOfWeekDate)) {
				nextDate = nextDayOfWeekDate;
			}
		}

		return nextDate;
	}

	public static LocalTime getTimeSection(LocalDateTime notificationTime, int section) {
		int timeSection = (notificationTime.getMinute() / section) * section;
		return LocalTime.of(notificationTime.getHour(), timeSection);
	}
}
