package com.depromeet.threedays.front.domain.usecase.notification;

import com.depromeet.threedays.front.domain.converter.notification.GlobalNotificationConverter;
import com.depromeet.threedays.front.domain.model.notification.NotificationMessage;
import com.depromeet.threedays.front.persistence.repository.notification.GlobalNotificationRepository;
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
public class GetGlobalNotificationUseCase {

	private final GlobalNotificationRepository repository;

	@Value("${batch.global}")
	private Integer section;

	public List<NotificationMessage> execute(LocalDateTime notificationTime) {
		DayOfWeek day = notificationTime.getDayOfWeek();
		LocalTime timeSection = DateCalculator.getTimeSection(notificationTime, section);

		return repository.findAllByNotificationTimeAndDayOfWeek(timeSection, day).stream()
				.map(GlobalNotificationConverter::from)
				.collect(Collectors.toList());
	}
}
