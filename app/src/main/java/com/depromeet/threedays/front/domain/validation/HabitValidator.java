package com.depromeet.threedays.front.domain.validation;

import com.depromeet.threedays.front.domain.model.habit.Habit;
import com.depromeet.threedays.front.exception.PolicyViolationException;
import java.time.DayOfWeek;
import java.util.EnumSet;

import com.depromeet.threedays.front.web.request.habit.UpdateHabitRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class HabitValidator {
	private static final String MESSAGE_CODE_PREFIX = "habit.constraints.";

	public void validateCreateConstraints(final Habit target) {
		this.throwIfInSufficientDayOfWeeks(target.getDayOfWeeks());
	}

	public void validateUpdateConstraints(final UpdateHabitRequest target) {
		this.throwIfInSufficientDayOfWeeks(target.getDayOfWeeks());
		this.throwIfInSufficientNotification(target);
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

		if (condition) {
			throw new PolicyViolationException(MESSAGE_CODE_PREFIX + messageCodeSuffix);
		}
	}

	private void throwIfInSufficientNotification(UpdateHabitRequest target) {
		if (target == null) {
			return;
		}

		final String INSUFFICIENT_NOTIFICATION = "insufficient.notification";

		this.throwIf(target.getNotification() != null && (target.getNotification()
																.getNotificationTime() == null || target.getNotification()
																										.getContents() == null), INSUFFICIENT_NOTIFICATION);
	}
}
