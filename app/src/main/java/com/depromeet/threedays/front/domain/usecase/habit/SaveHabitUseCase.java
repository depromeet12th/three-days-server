package com.depromeet.threedays.front.domain.usecase.habit;

import com.depromeet.threedays.data.entity.habit.HabitEntity;
import com.depromeet.threedays.data.enums.DayOfWeek;
import com.depromeet.threedays.front.controller.request.habit.SaveHabitRequest;
import com.depromeet.threedays.front.domain.converter.habit.HabitConverter;
import com.depromeet.threedays.front.domain.converter.notification.HabitNotificationConverter;
import com.depromeet.threedays.front.domain.model.habit.Habit;
import com.depromeet.threedays.front.domain.model.notification.Notification;
import com.depromeet.threedays.front.domain.validation.HabitValidator;
import com.depromeet.threedays.front.repository.habit.HabitRepository;
import com.depromeet.threedays.front.repository.notification.HabitNotificationRepository;
import java.util.EnumSet;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional
@RequiredArgsConstructor
public class SaveHabitUseCase {

	private final HabitRepository repository;
	private final HabitNotificationRepository habitNotificationRepository;

	private final HabitValidator validator;

	public Habit execute(final SaveHabitRequest request) {
		Habit data = HabitConverter.from(request);
		validator.validateCreateConstraints(data);

		return this.save(data);
	}

	private Habit save(Habit data) {
		HabitEntity entity = repository.save(HabitConverter.to(data));

		this.saveAssociation(entity.getId(), data.getNotification(), data.getDayOfWeeks());
		return HabitConverter.from(entity, data.getNotification());
	}

	private void saveAssociation(Long habitId, Notification data,
			EnumSet<DayOfWeek> dayOfWeeks) {
		for (DayOfWeek dayOfWeek : dayOfWeeks) {
			habitNotificationRepository.save(
					HabitNotificationConverter.to(data, habitId, dayOfWeek));
		}
	}
}
