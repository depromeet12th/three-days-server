package com.depromeet.threedays.front.client;

import com.google.firebase.messaging.*;
import org.springframework.stereotype.Service;

@Service
public class MessageClient {

	public BatchResponse send(MulticastMessage message) {
		try {
			return FirebaseMessaging.getInstance().sendMulticast(message);
		} catch (FirebaseMessagingException e) {
			return null;
		}
	}
}
