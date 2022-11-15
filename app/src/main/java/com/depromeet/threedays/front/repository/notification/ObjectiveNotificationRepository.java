package com.depromeet.threedays.front.repository.notification;

import com.depromeet.threedays.data.entity.notification.ObjectiveNotificationEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ObjectiveNotificationRepository
		extends JpaRepository<ObjectiveNotificationEntity, Long> {}
