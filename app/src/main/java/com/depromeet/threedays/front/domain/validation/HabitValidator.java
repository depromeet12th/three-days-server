package com.depromeet.threedays.front.domain.validation;

import com.depromeet.threedays.front.domain.model.habit.Habit;
import com.depromeet.threedays.front.exception.PolicyViolationException;
import java.time.DayOfWeek;
import java.util.EnumSet;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class HabitValidator {

	public void validateCreateConstraints(final Habit target) {
		this.throwIfInSufficientDayOfWeeks(target.getDayOfWeeks());
	}

	public void validateUpdateConstraints(final EnumSet<DayOfWeek> target) {
		this.throwIfInSufficientDayOfWeeks(target);
	}

	private void throwIfInSufficientDayOfWeeks(EnumSet<DayOfWeek> target) {
		if (target == null) {
			return;
		}

		final String INSUFFICIENT_DAY_OF_WEEKS = "insufficient.day.of.weeks";
		final int MINIMUM_SIZE = 3;

		this.throwIf(target.size() < MINIMUM_SIZE, INSUFFICIENT_DAY_OF_WEEKS);
	}

	private void throwIf(final boolean condition, final String messageCodeSuffix) {
		final String MESSAGE_CODE_PREFIX = "habit.constraints.";

		if (condition) {
			throw new PolicyViolationException(MESSAGE_CODE_PREFIX + messageCodeSuffix);
		}
	}
}
