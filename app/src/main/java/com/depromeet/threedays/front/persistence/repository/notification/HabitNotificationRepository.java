package com.depromeet.threedays.front.persistence.repository.notification;

import com.depromeet.threedays.data.entity.notification.HabitNotificationEntity;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HabitNotificationRepository extends JpaRepository<HabitNotificationEntity, Long> {

	Optional<HabitNotificationEntity> findFirstByHabitId(final Long habitId);
}
