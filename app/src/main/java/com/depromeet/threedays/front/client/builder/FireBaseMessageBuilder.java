package com.depromeet.threedays.front.client.builder;

import com.depromeet.threedays.data.entity.client.ClientEntity;
import com.depromeet.threedays.front.domain.model.client.Client;
import com.depromeet.threedays.front.domain.model.notification.HabitNotificationMessage;
import com.google.firebase.messaging.MulticastMessage;
import com.google.firebase.messaging.Notification;
import java.util.List;
import java.util.stream.Collectors;
import lombok.experimental.UtilityClass;

@UtilityClass
public class FireBaseMessageBuilder {
	public static MulticastMessage makeMulticastMessage(
			List<ClientEntity> group, String title, String contents) {
		Notification notification = Notification.builder().setTitle(title).setBody(contents).build();
		return MulticastMessage.builder()
				.addAllTokens(group.stream().map(ClientEntity::getFcmToken).collect(Collectors.toList()))
				.setNotification(notification)
				.build();
	}

	public static MulticastMessage makeMulticastMessage(HabitNotificationMessage messages) {
		Notification notification = Notification.builder().setBody(messages.getContent()).build();
		return MulticastMessage.builder()
				.addAllTokens(
						messages.getClients().stream().map(Client::getFcmToken).collect(Collectors.toList()))
				.setNotification(notification)
				.build();
	}
}
