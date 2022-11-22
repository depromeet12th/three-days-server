package com.depromeet.threedays.front.client.property;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Getter
@Component
public class FirebaseProperty {
	@Value("${firebase.database.uri}")
	String dataBaseUri;

	@Value("${firebase.multicast-message.size}")
	Long multicastMessageSize;

	@Value("${firebase.projectId}")
	String projectId;

	@Value("${firebase.private.keyId}")
	String privateKeyId;

	@Value("${firebase.private.key}")
	String privateKey;

	@Value("${firebase.client.id}")
	String clientId;

	@Value("${firebase.client.email}")
	String clientEmail;

	@Value("${firebase.token.uri}")
	String tokenUri;
}
