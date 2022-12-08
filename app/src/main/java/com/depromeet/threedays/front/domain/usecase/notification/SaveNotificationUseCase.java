package com.depromeet.threedays.front.domain.usecase.notification;

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


	public void execute(final Long id, final EditStatusNotificationRequest request) {

		NotificationHistory history = repository.findById(id)
				.map(NotificationHistoryConverter::from).orElseThrow(
						ResourceNotFoundException::new);

		repository.save(NotificationHistoryConverter.to(history.withStatus(request.getStatus())));
	}
}
