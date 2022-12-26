package com.depromeet.threedays.front.domain.usecase.mate;

import com.depromeet.threedays.front.domain.converter.mate.MateConverter;
import com.depromeet.threedays.front.domain.model.mate.Mate;
import com.depromeet.threedays.front.domain.validation.MateValidator;
import com.depromeet.threedays.front.persistence.repository.mate.MateRepository;
import com.depromeet.threedays.front.web.request.mate.SaveMateRequest;
import com.depromeet.threedays.front.web.response.assembler.MateAssembler;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional
@RequiredArgsConstructor
public class SaveMateUseCase {

	private final MateRepository repository;

	private final MateValidator validator;

	private final MateAssembler mateAssembler;

	public Mate execute(final Long habitId, final SaveMateRequest request) {

		Mate data = MateConverter.from(habitId, request).toBuilder().level(0).build();

		validator.validateCreateConstraints(data);

		return this.save(data);
	}

	private Mate save(final Mate data) {
		return mateAssembler.toMate(repository.save(MateConverter.to(data)));
	}
}
