package com.depromeet.threedays.front.domain.usecase.notification;

import com.depromeet.threedays.data.entity.client.ClientEntity;
import com.depromeet.threedays.data.entity.member.MemberEntity;
import com.depromeet.threedays.data.entity.notification.HabitNotificationEntity;
import com.depromeet.threedays.front.client.MessageClient;
import com.depromeet.threedays.front.client.builder.FireBaseMessageBuilder;
import com.depromeet.threedays.front.domain.converter.client.ClientConverter;
import com.depromeet.threedays.front.domain.converter.notification.HabitNotificationConverter;
import com.depromeet.threedays.front.domain.model.client.Client;
import com.depromeet.threedays.front.domain.model.notification.HabitNotificationMessage;
import com.depromeet.threedays.front.persistence.repository.client.ClientRepository;
import com.depromeet.threedays.front.persistence.repository.member.MemberRepository;
import com.depromeet.threedays.front.web.request.habit.NotificationRequest;
import com.google.firebase.messaging.BatchResponse;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@RequiredArgsConstructor
@Service
public class SendHabitNotificationUseCase {

	private final GetHabitNotificationUseCase getUseCase;
	private final MemberRepository memberRepository;
	private final ClientRepository clientRepository;

	private final MessageClient messageClient;

	public List<BatchResponse> execute(NotificationRequest request) {
		List<HabitNotificationMessage> messages =
				makeHabitNotificationMessage(request.getNotificationTime());
		return sendMessage(messages);
	}

	private List<HabitNotificationMessage> makeHabitNotificationMessage(
			LocalDateTime notificationTime) {

		List<ClientEntity> clientEntities = clientRepository.findAll();

		List<HabitNotificationMessage> messages =
				getNotificationFilterByNotificationConsent(notificationTime);

		List<List<Client>> groupedClient =
				new ArrayList<>(
						clientEntities.stream()
								.map(ClientConverter::from)
								.collect(Collectors.groupingBy(Client::getMemberId))
								.values());

		return mappingClientWithNotification(groupedClient, messages);
	}

	private List<HabitNotificationMessage> getNotificationFilterByNotificationConsent(
			LocalDateTime notificationTime) {
		List<Long> memberIds = this.getMemberIds();
		List<HabitNotificationEntity> list = getUseCase.execute(notificationTime);
		return list.stream()
				.filter(m -> memberIds.contains(m.getMemberId()))
				.map(HabitNotificationConverter::habitMessagefrom)
				.collect(Collectors.toList());
	}

	private List<HabitNotificationMessage> mappingClientWithNotification(
			List<List<Client>> groupedClient, List<HabitNotificationMessage> messages) {
		for (HabitNotificationMessage message : messages) { // 습관 알림
			for (List<Client> client : groupedClient) { // 같은 멤버를 가지는 클라이언트들
				if (message.getMemberId().equals(client.get(0).getMemberId())) {
					message.setClients(client);
				}
			}
		}
		return messages;
	}

	private List<Long> getMemberIds() {
		List<MemberEntity> members = memberRepository.findAllByNotificationConsent(true).orElse(null);
		if (members == null) {
			return Collections.emptyList();
		}
		return members.stream().map(MemberEntity::getId).collect(Collectors.toList());
	}

	private List<BatchResponse> sendMessage(List<HabitNotificationMessage> messages) {
		List<BatchResponse> responses = new ArrayList<>();
		for (HabitNotificationMessage message : messages) {
			responses.add(messageClient.send(FireBaseMessageBuilder.makeMulticastMessage(message)));
		}
		return responses;
	}
}
