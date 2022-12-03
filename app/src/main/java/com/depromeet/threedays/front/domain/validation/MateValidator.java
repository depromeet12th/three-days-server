package com.depromeet.threedays.front.domain.validation;

import com.depromeet.threedays.front.domain.model.habit.Habit;
import com.depromeet.threedays.front.domain.model.mate.Mate;
import com.depromeet.threedays.front.exception.PolicyViolationException;
import com.depromeet.threedays.front.persistence.repository.mate.MateRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MateValidator {

	private final MateRepository repository;

	public void validateCreateConstraints(final Habit habit, final Mate target) {
		this.throwIfAlreadyExistMate(habit, target);
	}

	private void throwIfAlreadyExistMate(final Habit habit, final Mate target) {
		final String ALREADY_EXIST_MATE = "already.exist.mate";

		this.throwIf(
				repository.existsByMemberIdAndDeletedFalse(habit.getMemberId()), ALREADY_EXIST_MATE);
	}

	private void throwIf(final boolean condition, final String messageCodeSuffix) {
		final String MESSAGE_CODE_PREFIX = "mate.constraints.";

		if (condition) {
			throw new PolicyViolationException(MESSAGE_CODE_PREFIX + messageCodeSuffix);
		}
	}
}
