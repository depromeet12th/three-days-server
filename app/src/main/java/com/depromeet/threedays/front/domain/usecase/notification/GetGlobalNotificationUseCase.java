package com.depromeet.threedays.front.domain.usecase.notification;

import com.depromeet.threedays.front.domain.converter.notification.GlobalNotificationConverter;
import com.depromeet.threedays.front.domain.model.notification.NotificationMessage;
import com.depromeet.threedays.front.persistence.repository.notification.GlobalNotificationRepository;
import java.time.DayOfWeek;
import java.time.LocalDate;
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
public class GetGlobalNotificationUseCase {

	private final GlobalNotificationRepository repository;

	@Value("batch.global.time-section")
	private int section;

	public List<NotificationMessage> execute() {
		DayOfWeek day = LocalDate.now().getDayOfWeek();
		LocalTime notificationTime = getTimeSection();

		return repository.findAllByNotificationTimeAndDayOfWeek(notificationTime, day).stream()
				.map(GlobalNotificationConverter::from)
				.collect(Collectors.toList());
	}

	private LocalTime getTimeSection() {
		LocalDateTime now = LocalDateTime.now();
		int timeSection = (now.getMinute() / section) * section;
		return LocalTime.of(now.getHour(), timeSection);
	}
}
