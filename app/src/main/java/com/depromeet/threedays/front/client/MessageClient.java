package com.depromeet.threedays.front.client;

import com.depromeet.threedays.front.client.property.FirebaseProperty;
import com.google.firebase.messaging.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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
			return null;
		}
	}
}
