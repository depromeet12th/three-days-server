package com.depromeet.threedays.front.persistence.repository.notification;

import com.depromeet.threedays.data.entity.history.NotificationHistoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotificationHistoryRepository
		extends JpaRepository<NotificationHistoryEntity, Long> {}
