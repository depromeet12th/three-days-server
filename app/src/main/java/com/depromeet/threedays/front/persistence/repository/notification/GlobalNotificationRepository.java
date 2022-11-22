package com.depromeet.threedays.front.persistence.repository.notification;

import com.depromeet.threedays.data.entity.notification.GlobalNotificationEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GlobalNotificationRepository
		extends JpaRepository<GlobalNotificationEntity, Long> {}
