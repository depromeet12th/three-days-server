package com.depromeet.threedays.front.domain.usecase.habit;

import com.depromeet.threedays.front.domain.converter.habit.HabitConverter;
import com.depromeet.threedays.front.domain.converter.notification.HabitNotificationConverter;
import com.depromeet.threedays.front.domain.model.habit.Habit;
import com.depromeet.threedays.front.domain.model.notification.Notification;
import com.depromeet.threedays.front.domain.validation.HabitValidator;
import com.depromeet.threedays.front.exception.ResourceNotFoundException;
import com.depromeet.threedays.front.persistence.repository.habit.HabitRepository;
import com.depromeet.threedays.front.persistence.repository.notification.HabitNotificationRepository;
import com.depromeet.threedays.front.web.request.habit.UpdateHabitRequest;
import java.time.DayOfWeek;
import java.util.EnumSet;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional
@RequiredArgsConstructor
public class UpdateHabitUseCase {

	private final HabitRepository repository;

	private final HabitNotificationRepository habitNotificationRepository;

	private final HabitValidator validator;

	public Habit execute(final Long id, final UpdateHabitRequest request) {
		validator.validateUpdateConstraints(request);
		Habit source =
				repository
						.findById(id)
						.map(HabitConverter::from)
						.orElseThrow(ResourceNotFoundException::new);

		updateAssociation(id, request.getNotification(), request.getDayOfWeeks());

		return HabitConverter.from(
				repository.save(HabitConverter.to(source, request)), request.getNotification());
	}

	private void updateAssociation(Long habitId, Notification data, EnumSet<DayOfWeek> dayOfWeeks) {
		if (data == null) {
			return;
		}

		habitNotificationRepository.deleteAllByHabitId(habitId);

		for (DayOfWeek dayOfWeek : dayOfWeeks) {
			habitNotificationRepository.save(HabitNotificationConverter.to(data, habitId, dayOfWeek));
		}
	}
}
