package com.depromeet.threedays.front.repository;

import com.depromeet.threedays.data.entity.notification.NotificationEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotificationRepository extends JpaRepository<NotificationEntity, Long> {}
