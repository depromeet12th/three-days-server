package com.depromeet.threedays.front.domain.usecase.habit;

import com.depromeet.threedays.front.domain.converter.habit.HabitConverter;
import com.depromeet.threedays.front.domain.model.habit.Habit;
import com.depromeet.threedays.front.exception.ResourceNotFoundException;
import com.depromeet.threedays.front.persistence.repository.habit.HabitRepository;
import com.depromeet.threedays.front.web.request.habit.UpdateHabitRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional
@RequiredArgsConstructor
public class UpdateHabitUseCase {

	private final HabitRepository repository;

	public Habit execute(final Long id, final UpdateHabitRequest request) {

		Habit source =
				repository
						.findById(id)
						.map(HabitConverter::from)
						.orElseThrow(ResourceNotFoundException::new);

		return HabitConverter.from(repository.save(HabitConverter.to(source, request)));
	}
}
