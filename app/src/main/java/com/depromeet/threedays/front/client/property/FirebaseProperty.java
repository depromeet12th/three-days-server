package com.depromeet.threedays.front.client.property;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Getter
@Component
public class FirebaseProperty {

	@Value("${firebase.multicast-message.size}")
	Long multicastMessageSize;

	@Value("${firebase.projectId:#{null}}")
	String projectId;

	@Value("${firebase.private.keyId:#{null}}")
	String privateKeyId;

	@Value("${firebase.private.key:#{null}}")
	String privateKey;

	@Value("${firebase.client.id:#{null}}")
	String clientId;

	@Value("${firebase.client.email:#{null}}")
	String clientEmail;

	@Value("${firebase.token.uri}")
	String tokenUri;
}
