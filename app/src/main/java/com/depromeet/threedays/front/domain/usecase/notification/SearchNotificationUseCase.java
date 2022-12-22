package com.depromeet.threedays.front.domain.usecase.notification;

import com.depromeet.threedays.data.enums.NotificationStatus;
import com.depromeet.threedays.front.config.security.AuditorHolder;
import com.depromeet.threedays.front.domain.converter.NotificationHistoryConverter;
import com.depromeet.threedays.front.domain.model.notification.NotificationHistory;
import com.depromeet.threedays.front.persistence.repository.notification.NotificationHistoryRepository;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class SearchNotificationUseCase {

	private final NotificationHistoryRepository repository;

	public List<NotificationHistory> execute() {

		return repository
				.findAllByMemberIdAndStatusInAndCreateAtBetweenOrderByCreateAtDesc(
						AuditorHolder.get(),
						NotificationStatus.visibleStatuses(),
						LocalDateTime.now().minusDays(30),
						LocalDateTime.now())
				.stream()
				.map(NotificationHistoryConverter::from)
				.collect(Collectors.toList());
	}
}
