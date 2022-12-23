package com.depromeet.threedays.front.client;

import com.depromeet.threedays.front.client.property.FirebaseProperty;
import com.google.firebase.messaging.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class MessageClient {

	private final FirebaseProperty firebaseProperty;

	public BatchResponse send(MulticastMessage message) {
		if (firebaseProperty.getPrivateKey() == null) {
			return null;
		}
		try {
			return FirebaseMessaging.getInstance().sendMulticast(message);
		} catch (FirebaseMessagingException e) {
			log.error("Failed to send push message.", e);
			return null;
		}
	}
}
