package com.depromeet.threedays.front.domain.usecase.notification;

import com.depromeet.threedays.data.entity.history.NotificationHistoryEntity;
import com.depromeet.threedays.front.domain.converter.NotificationHistoryConverter;
import com.depromeet.threedays.front.domain.model.notification.NotificationHistory;
import com.depromeet.threedays.front.exception.ResourceNotFoundException;
import com.depromeet.threedays.front.persistence.repository.notification.NotificationHistoryRepository;
import com.depromeet.threedays.front.web.request.notification.EditStatusNotificationRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional
@RequiredArgsConstructor
public class SaveNotificationUseCase {

	private final NotificationHistoryRepository repository;

	public NotificationHistory execute(
			final Long notificationHistoryId, final EditStatusNotificationRequest request) {
		NotificationHistory history =
				repository
						.findById(notificationHistoryId)
						.map(NotificationHistoryConverter::from)
						.orElseThrow(ResourceNotFoundException::new);
		NotificationHistoryEntity notificationHistoryEntity =
				NotificationHistoryConverter.to(history.withStatus(request.getStatus()));
		repository.save(notificationHistoryEntity);
		return NotificationHistoryConverter.from(notificationHistoryEntity);
	}
}
