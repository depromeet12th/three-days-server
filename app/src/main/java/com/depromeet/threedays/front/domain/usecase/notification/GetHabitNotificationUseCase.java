package com.depromeet.threedays.front.domain.usecase.notification;

import com.depromeet.threedays.data.entity.notification.HabitNotificationEntity;
import com.depromeet.threedays.front.persistence.repository.notification.HabitNotificationRepository;
import com.depromeet.threedays.front.support.DateCalculator;
import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
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

	/** 현재 시각, 요일에 맞는 습관 알림 목록 조회 */
	public List<HabitNotificationEntity> execute() {
		DayOfWeek day = LocalDateTime.now().getDayOfWeek();
		LocalTime timeSection = DateCalculator.getTimeSection(LocalDateTime.now(), section);

		return repository.findByNotificationTimeAndDayOfWeek(timeSection, day);
	}
}
