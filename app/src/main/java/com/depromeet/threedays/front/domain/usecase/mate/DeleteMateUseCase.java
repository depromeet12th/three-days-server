package com.depromeet.threedays.front.domain.usecase.mate;

import com.depromeet.threedays.data.entity.mate.MateEntity;
import com.depromeet.threedays.front.domain.validation.MateValidator;
import com.depromeet.threedays.front.persistence.repository.mate.MateRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional
@RequiredArgsConstructor
public class DeleteMateUseCase {

	private final MateRepository repository;

	private final MateValidator validator;

	public void execute(final Long habitId, final Long id) {
		validator.validateDeleteConstraints(habitId);

		MateEntity entity = repository.findById(id).orElse(null);

		if (entity == null) {
			return;
		}

		repository.save(entity.toBuilder().deleted(true).build());
	}
}
