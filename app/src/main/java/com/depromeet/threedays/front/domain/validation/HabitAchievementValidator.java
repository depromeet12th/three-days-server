package com.depromeet.threedays.front.domain.validation;

import com.depromeet.threedays.data.entity.habit.HabitEntity;
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

	public void validateDeleteConstraints(
			final HabitEntity habitEntity, final HabitAchievement target) {
		this.throwIfInvalidHabit(habitEntity, target);
		this.throwIfNotEqualToday(target.getAchievementDate());
	}

	private void throwIfInvalidHabit(final HabitEntity habitEntity, final HabitAchievement target) {
		final String INVALID_HABIT = "invalid.habit";
		this.throwIf(habitEntity.getId().equals(target.getHabitId()), INVALID_HABIT);
	}

	private void throwIfDateInThePast(final HabitAchievement target) {
		final String DATE_IS_IN_THE_PAST = "date.is.in.the.past";
		this.throwIf(!LocalDate.now().isEqual(target.getAchievementDate()), DATE_IS_IN_THE_PAST);
	}

	private void throwIfNotEqualToday(final LocalDate achievementDate) {
		if (achievementDate == null) {
			return;
		}

		final String INVALID_REQUEST_DATE = "invalid.achievement.date";
		this.throwIf(achievementDate.isEqual(LocalDate.now()), INVALID_REQUEST_DATE);
	}

	private void throwIf(final boolean condition, final String messageCodeSuffix) {
		final String MESSAGE_CODE_PREFIX = "habit.achievement.constraints.";

		if (condition) {
			throw new PolicyViolationException(MESSAGE_CODE_PREFIX + messageCodeSuffix);
		}
	}
}
