package com.depromeet.threedays.front.domain.usecase.notification;

import com.depromeet.threedays.data.entity.client.ClientEntity;
import com.depromeet.threedays.data.entity.history.NotificationHistoryEntity;
import com.depromeet.threedays.data.enums.NotificationStatus;
import com.depromeet.threedays.front.domain.converter.notification.NotificationHistoryConverter;
import com.depromeet.threedays.front.domain.model.notification.HabitNotificationMessage;
import com.depromeet.threedays.front.domain.model.notification.NotificationMessage;
import com.depromeet.threedays.front.persistence.repository.notification.NotificationHistoryRepository;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@RequiredArgsConstructor
@Service
public class SaveNotificationHistoryUseCase {

	private final NotificationHistoryRepository repository;

	public void execute(int successCount, NotificationMessage message, List<ClientEntity> group) {
		if (successCount == 0) {
			repository.saveAll(createList(NotificationStatus.SENT, message, group));
		} else {
			repository.saveAll(createList(NotificationStatus.FAILURE, message, group));
		}
	}

	public void execute(int successCount, HabitNotificationMessage message) {
		if (successCount == 0) {
			repository.saveAll(createList(NotificationStatus.FAILURE, message));
		} else {
			repository.saveAll(createList(NotificationStatus.SENT, message));
		}
	}

	public List<NotificationHistoryEntity> createList(
			NotificationStatus status, NotificationMessage message, List<ClientEntity> group) {
		return group.stream()
				.map(c -> NotificationHistoryConverter.from(message, c, status))
				.collect(Collectors.toList());
	}

	public List<NotificationHistoryEntity> createList(
			NotificationStatus status, HabitNotificationMessage message) {
		return message.getClients().stream()
				.map(c -> NotificationHistoryConverter.from(message, c, status))
				.collect(Collectors.toList());
	}
}
