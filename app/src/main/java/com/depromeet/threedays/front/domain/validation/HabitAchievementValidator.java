package com.depromeet.threedays.front.domain.validation;

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

	private void throwIfDateInThePast(HabitAchievement target) {
		this.throwIf(!LocalDate.now().isEqual(target.getAchievementDate()), "date.is.not.equal");
	}

	private void throwIf(final boolean condition, final String messageCodeSuffix) {
		final String MESSAGE_CODE_PREFIX = "habit.achievement.constraints.";

		if (condition) {
			throw new PolicyViolationException(MESSAGE_CODE_PREFIX + messageCodeSuffix);
		}
	}

}
