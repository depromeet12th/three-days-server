package com.depromeet.threedays.front.domain.usecase.mate;

import com.depromeet.threedays.front.domain.converter.mate.MateConverter;
import com.depromeet.threedays.front.domain.model.mate.Mate;
import com.depromeet.threedays.front.exception.ResourceNotFoundException;
import com.depromeet.threedays.front.persistence.repository.habit.HabitRepository;
import com.depromeet.threedays.front.persistence.repository.mate.MateRepository;
import com.depromeet.threedays.front.web.response.MateResponse;
import com.depromeet.threedays.front.web.response.assembler.MateAssembler;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class GetMateUseCase {

	private final HabitRepository habitRepository;
	private final MateRepository repository;

	private final MateAssembler mateAssembler;

	public MateResponse execute(final Long habitId, final Long id) {
		if (Boolean.FALSE.equals(habitRepository.existsByIdAndDeletedFalse(habitId))) {
			throw new ResourceNotFoundException();
		}

		Mate mate =
				repository
						.findById(id)
						.map(MateConverter::from)
						.orElseThrow(ResourceNotFoundException::new);

		return mateAssembler.toMateResponse(mate);
	}
}
