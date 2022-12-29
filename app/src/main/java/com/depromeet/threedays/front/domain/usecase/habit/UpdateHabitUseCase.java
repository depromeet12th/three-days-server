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
import org.springframework.util.Assert;

@Component
@Transactional
@RequiredArgsConstructor
public class UpdateHabitUseCase {

	private final HabitRepository habitRepository;

	private final HabitNotificationRepository habitNotificationRepository;

	private final HabitValidator habitValidator;

	public Habit execute(final Long habitId, final UpdateHabitRequest request) {
		habitValidator.validateUpdateConstraints(request);
		Habit habit =
				habitRepository
						.findById(habitId)
						.map(HabitConverter::from)
						.orElseThrow(ResourceNotFoundException::new);

		updateAssociation(habit, request.getNotification(), request.getDayOfWeeks());

		return HabitConverter.from(
				habitRepository.save(HabitConverter.to(habit, request)), request.getNotification());
	}

	private void updateAssociation(
			Habit habit, Notification notification, EnumSet<DayOfWeek> dayOfWeeks) {
		Assert.notNull(habit, "'habit' must not be null");
		// 이전 알림 삭제
		habitNotificationRepository.deleteAllByHabitId(habit.getId());

		// 새로 저장할 알림이 없음
		if (notification == null) {
			return;
		}
		// 새로 저장할 알림이 있음
		for (DayOfWeek dayOfWeek : dayOfWeeks) {
			habitNotificationRepository.save(
					HabitNotificationConverter.to(habit, notification, dayOfWeek));
		}
	}
}
