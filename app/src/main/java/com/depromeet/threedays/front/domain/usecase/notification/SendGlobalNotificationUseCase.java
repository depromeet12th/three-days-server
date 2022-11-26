package com.depromeet.threedays.front.domain.usecase.notification;

import com.depromeet.threedays.data.entity.client.ClientEntity;
import com.depromeet.threedays.front.client.MessageClient;
import com.depromeet.threedays.front.client.property.FirebaseProperty;
import com.depromeet.threedays.front.domain.model.notification.NotificationMessage;
import com.depromeet.threedays.front.persistence.repository.client.ClientRepository;
import com.depromeet.threedays.front.web.response.NotificationBatchResponse;
import com.google.firebase.messaging.BatchResponse;
import com.google.firebase.messaging.MulticastMessage;
import com.google.firebase.messaging.Notification;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@RequiredArgsConstructor
@Service
public class SendGlobalNotificationUseCase {

	private final ClientRepository clientRepository;

	private final GetGlobalNotificationUseCase getUseCase;
	private final MessageClient messageClient;
	private final FirebaseProperty fireBaseProperty;

	public List<NotificationBatchResponse> execute() {
		List<NotificationMessage> messages = getUseCase.execute();

		List<NotificationBatchResponse> result = new ArrayList<>();
		for (NotificationMessage message : messages) {
			List<BatchResponse> responses = sendMessage(message);
			result.add(
					NotificationBatchResponse.builder()
							.title(message.getTitle())
							.content(message.getContents())
							.messageResponse(responses)
							.build());
		}
		return result;
	}

	private List<BatchResponse> sendMessage(NotificationMessage message) {

		Collection<List<ClientEntity>> clientGroups = makeGroups();

		List<BatchResponse> responses = new ArrayList<>();
		for (List<ClientEntity> group : clientGroups) {
			responses.add(
					messageClient.send(
							makeMulticastMessage(group, message.getTitle(), message.getContents())));
		}

		return responses;
	}

	private Collection<List<ClientEntity>> makeGroups() {
		List<ClientEntity> clients = clientRepository.findAll();
		AtomicInteger counter = new AtomicInteger();

		return clients.stream()
				.collect(
						Collectors.groupingBy(
								it -> counter.getAndIncrement() / fireBaseProperty.getMulticastMessageSize()))
				.values();
	}

	private MulticastMessage makeMulticastMessage(
			List<ClientEntity> group, String title, String contents) {
		Notification notification = Notification.builder().setTitle(title).setBody(contents).build();
		return MulticastMessage.builder()
				.addAllTokens(group.stream().map(ClientEntity::getFcmToken).collect(Collectors.toList()))
				.setNotification(notification)
				.build();
	}
}
