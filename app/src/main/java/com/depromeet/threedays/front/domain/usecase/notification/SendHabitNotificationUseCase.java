package com.depromeet.threedays.front.domain.usecase.notification;

import com.depromeet.threedays.data.entity.client.ClientEntity;
import com.depromeet.threedays.data.entity.member.MemberEntity;
import com.depromeet.threedays.data.entity.notification.HabitNotificationEntity;
import com.depromeet.threedays.data.enums.NotificationStatus;
import com.depromeet.threedays.front.client.MessageClient;
import com.depromeet.threedays.front.client.builder.FireBaseMessageBuilder;
import com.depromeet.threedays.front.domain.converter.client.ClientConverter;
import com.depromeet.threedays.front.domain.converter.notification.HabitNotificationConverter;
import com.depromeet.threedays.front.domain.model.client.Client;
import com.depromeet.threedays.front.domain.model.notification.HabitNotificationMessage;
import com.depromeet.threedays.front.persistence.repository.client.ClientRepository;
import com.depromeet.threedays.front.persistence.repository.member.MemberRepository;
import com.depromeet.threedays.front.support.NotificationLogger;
import com.google.firebase.messaging.BatchResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@RequiredArgsConstructor
@Service
public class SendHabitNotificationUseCase {

	private final GetHabitNotificationUseCase getUseCase;
	private final SaveNotificationHistoryUseCase saveHistoryUseCase;
	private final MemberRepository memberRepository;
	private final ClientRepository clientRepository;

	private final MessageClient messageClient;

	/** 습관 알림 발송 */
	public List<BatchResponse> execute() {
		List<HabitNotificationMessage> messages = makeHabitNotificationMessage();
		return sendMessage(messages);
	}

	private List<HabitNotificationMessage> makeHabitNotificationMessage() {

		List<ClientEntity> clientEntities = clientRepository.findAll();

		List<HabitNotificationMessage> messages = getNotificationFilterByNotificationConsent();

		List<List<Client>> groupedClient =
				new ArrayList<>(
						clientEntities.stream()
								.map(ClientConverter::from)
								.collect(Collectors.groupingBy(Client::getMemberId))
								.values());

		return addClientList(groupedClient, messages);
	}

	/** 습관 알림 대상 중 발송가능한 알림 정보 목록 조회 (알림 수신 허용한 회원들의 습관) */
	private List<HabitNotificationMessage> getNotificationFilterByNotificationConsent() {
		Set<Long> memberIds = this.getNotificationConsentMemberIds();
		List<HabitNotificationEntity> list = getUseCase.execute();
		return list.stream()
				.filter(m -> memberIds.contains(m.getMemberId()))
				.map(HabitNotificationConverter::habitMessageFrom)
				.collect(Collectors.toList());
	}

	/** 습관 메시지에 그 회원이 가진 fcmToken 정보 매핑 */
	private List<HabitNotificationMessage> addClientList(
			List<List<Client>> groupedClient, List<HabitNotificationMessage> messages) {
		for (HabitNotificationMessage message : messages) {
			for (List<Client> client : groupedClient) {
				if (Objects.equals(message.getMemberId(), client.get(0).getMemberId())) {
					message.setClients(client);
				}
			}
			if (null == message.getClients()) {
				saveHistoryUseCase.execute(message, NotificationStatus.FAILURE);
				messages.remove(message);
			}
		}
		return messages;
	}

	/** 알림 받을 회원 목록 조회 */
	private Set<Long> getNotificationConsentMemberIds() {
		return memberRepository.findAllByNotificationConsent(true).stream()
				.map(MemberEntity::getId)
				.collect(Collectors.toSet());
	}

	/** 알림 발송 */
	private List<BatchResponse> sendMessage(final List<HabitNotificationMessage> messages) {
		List<BatchResponse> responses = new ArrayList<>();
		for (HabitNotificationMessage message : messages) {
			NotificationLogger.messagesLogger(message);
			BatchResponse response =
					messageClient.send(FireBaseMessageBuilder.makeMulticastMessage(message));
			responses.add(response);
			NotificationLogger.batchResponseLogger(response);
			saveHistoryUseCase.execute(response.getSuccessCount(), message);
		}
		return responses;
	}
}
