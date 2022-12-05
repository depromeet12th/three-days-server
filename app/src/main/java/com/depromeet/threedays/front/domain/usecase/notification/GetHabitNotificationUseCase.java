package com.depromeet.threedays.front.domain.usecase.notification;

import com.depromeet.threedays.data.entity.notification.HabitNotificationEntity;
import com.depromeet.threedays.front.persistence.repository.notification.HabitNotificationRepository;
import com.depromeet.threedays.front.support.DateCalculator;
import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@RequiredArgsConstructor
@Service
public class GetHabitNotificationUseCase {
	private final HabitNotificationRepository repository;

	@Value("${batch.habit}")
	private int section;

	public List<HabitNotificationEntity> execute(LocalDateTime notificationTime) {
		DayOfWeek day = notificationTime.getDayOfWeek();
		LocalTime timeSection = DateCalculator.getTimeSection(notificationTime, section);

		return repository.findAllByNotificationTimeAndDayOfWeek(timeSection, day).stream()
				.collect(Collectors.toList());
	}
}
