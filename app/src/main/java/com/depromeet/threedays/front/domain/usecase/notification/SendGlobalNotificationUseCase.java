package com.depromeet.threedays.front.domain.usecase.notification;

import com.depromeet.threedays.data.entity.client.ClientEntity;
import com.depromeet.threedays.data.entity.member.MemberEntity;
import com.depromeet.threedays.front.client.MessageClient;
import com.depromeet.threedays.front.client.builder.FireBaseMessageBuilder;
import com.depromeet.threedays.front.client.property.FirebaseProperty;
import com.depromeet.threedays.front.domain.model.notification.NotificationMessage;
import com.depromeet.threedays.front.persistence.repository.client.ClientRepository;
import com.depromeet.threedays.front.persistence.repository.member.MemberRepository;
import com.depromeet.threedays.front.web.request.habit.NotificationRequest;
import com.depromeet.threedays.front.web.response.NotificationBatchResponse;
import com.google.firebase.messaging.BatchResponse;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
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
	private final MemberRepository memberRepository;
	private final GetGlobalNotificationUseCase getUseCase;
	private final MessageClient messageClient;
	private final FirebaseProperty fireBaseProperty;

	public List<NotificationBatchResponse> execute(NotificationRequest request) {
		List<NotificationMessage> messages = getUseCase.execute(request.getNotificationTime());

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
							FireBaseMessageBuilder.makeMulticastMessage(
									group, message.getTitle(), message.getContents())));
		}

		return responses;
	}

	private List<Long> getMemberIds() {
		List<MemberEntity> members = memberRepository.findAllByNotificationConsent(true).orElse(null);
		return members.stream().map(MemberEntity::getId).collect(Collectors.toList());
	}

	private Collection<List<ClientEntity>> makeGroups() {
		List<ClientEntity> clients = clientRepository.findAll();
		List<Long> memberIds = getMemberIds();
		if (memberIds == null) {
			return Collections.emptyList();
		}

		AtomicInteger counter = new AtomicInteger();

		return clients.stream()
				.filter(m -> memberIds.contains(m.getMemberId()))
				.collect(
						Collectors.groupingBy(
								it -> counter.getAndIncrement() / fireBaseProperty.getMulticastMessageSize()))
				.values();
	}
}
