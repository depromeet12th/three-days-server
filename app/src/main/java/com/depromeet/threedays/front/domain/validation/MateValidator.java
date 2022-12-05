package com.depromeet.threedays.front.domain.validation;

import com.depromeet.threedays.front.domain.model.mate.Mate;
import com.depromeet.threedays.front.exception.PolicyViolationException;
import com.depromeet.threedays.front.persistence.repository.habit.HabitRepository;
import com.depromeet.threedays.front.persistence.repository.mate.MateRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MateValidator {

	private final MateRepository repository;
	private final HabitRepository habitRepository;

	public void validateCreateConstraints(final Mate target) {
		this.throwIfInvalidHabit(target.getHabitId());
		this.throwIfAlreadyExistMate(target);
	}

	public void validateDeleteConstraints(final Long habitId) {
		this.throwIfInvalidHabit(habitId);
	}

	private void throwIfInvalidHabit(final Long habitId) {
		final String INVALID_HABIT = "invalid.habit";

		this.throwIf(!habitRepository.existsByIdAndDeletedFalse(habitId), INVALID_HABIT);
	}

	private void throwIfAlreadyExistMate(final Mate target) {
		final String ALREADY_EXIST_MATE = "already.exist.mate";

		this.throwIf(
				repository.existsByMemberIdAndDeletedFalse(target.getMemberId()), ALREADY_EXIST_MATE);
	}

	private void throwIf(final boolean condition, final String messageCodeSuffix) {
		final String MESSAGE_CODE_PREFIX = "mate.constraints.";

		if (condition) {
			throw new PolicyViolationException(MESSAGE_CODE_PREFIX + messageCodeSuffix);
		}
	}
}
