package com.depromeet.threedays.front.persistence.repository.notification;

import com.depromeet.threedays.data.entity.notification.GlobalNotificationEntity;
import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GlobalNotificationRepository
		extends JpaRepository<GlobalNotificationEntity, Long> {
	List<GlobalNotificationEntity> findAllByNotificationTimeAndDayOfWeek(
			final LocalTime notificationTime, final DayOfWeek dayOfWeek);
}
