package com.depromeet.threedays.front.persistence.repository.notification;

import com.depromeet.threedays.data.entity.history.NotificationHistoryEntity;
import com.depromeet.threedays.data.enums.NotificationStatus;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotificationHistoryRepository
		extends JpaRepository<NotificationHistoryEntity, Long> {

	List<NotificationHistoryEntity> findAllByMemberIdAndStatusInAndCreateAtBetweenOrderByCreateAtDesc(
			final Long memberId, final Collection<NotificationStatus> statuses, final
	LocalDateTime startTime, final LocalDateTime endTime);
}
