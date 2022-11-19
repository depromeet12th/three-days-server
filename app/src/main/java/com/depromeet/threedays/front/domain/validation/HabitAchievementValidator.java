package com.depromeet.threedays.front.domain.validation;

import com.depromeet.threedays.front.domain.model.habit.Habit;
import com.depromeet.threedays.front.domain.model.habit.HabitAchievement;
import com.depromeet.threedays.front.exception.PolicyViolationException;
import java.time.LocalDate;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class HabitAchievementValidator {

	public void validateCreateConstraints(final HabitAchievement target) {
		this.throwIfDateInThePast(target);
	}

	public void validateCancelDateConstraints(final Habit data) {
		this.throwIfNotEqualToday(data.getHabitAchievement().getAchievementDate());
	}

	private void throwIfDateInThePast(HabitAchievement target) {
		this.throwIf(LocalDate.now().isEqual(target.getAchievementDate()), "date.is.not.equal");
	}

	private void throwIfNotEqualToday(final LocalDate achievementDate) {
		if (achievementDate == null) {
			return;
		}

		final String INVALID_REQUEST_DATE = "invalid.request.date";
		this.throwIf(achievementDate.isEqual(LocalDate.now()), INVALID_REQUEST_DATE);
	}

	private void throwIf(final boolean condition, final String messageCodeSuffix) {
		final String MESSAGE_CODE_PREFIX = "habit.achievement.constraints.";

		if (condition) {
			throw new PolicyViolationException(MESSAGE_CODE_PREFIX + messageCodeSuffix);
		}
	}
}
