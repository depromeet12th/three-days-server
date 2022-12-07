package com.depromeet.threedays.front.web.response.converter;

import com.depromeet.threedays.front.domain.model.habit.Habit;
import com.depromeet.threedays.front.web.response.RecordHabitResponse;
import lombok.experimental.UtilityClass;

@UtilityClass
public class RecordHabitResponseConverter {

	public static RecordHabitResponse from(final Habit habit) {
		if (habit == null) {
			return null;
		}

		return RecordHabitResponse.builder()
				.id(habit.getId())
				.memberId(habit.getMemberId())
				.title(habit.getTitle())
				.imojiPath(habit.getImojiPath())
				.color(habit.getColor())
				.dayOfWeeks(habit.getDayOfWeeks())
				.status(habit.getStatus())
				.createAt(habit.getCreateAt())
				.build();
	}
}
