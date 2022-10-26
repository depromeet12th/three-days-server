package com.depromeet.threedays.front.client;

import com.depromeet.threedays.data.entity.member.certification.CertificationSubject;
import com.depromeet.threedays.front.client.model.OAuthInfo;
import com.depromeet.threedays.front.client.property.OAuthProperty;
import com.depromeet.threedays.front.controller.command.oauth.OAuthCommand;
import com.depromeet.threedays.front.domain.model.Token;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OAuthManager {

	private final Map<String, OAuthProperty> authPropertyMap;
	private final Map<String, OAuthClient> authClientMap;

	private static final String PROPERTY_CLASS_NAME = "OAuthProperty";
	private static final String CLIENT_CLASS_NAME = "OAuthClient";

	public OAuthProperty getOAuthProperty(CertificationSubject subject) {
		String propertyName = subject.name().toLowerCase() + PROPERTY_CLASS_NAME;
		return authPropertyMap.get(propertyName);
	}

	public OAuthClient getOAuthClient(CertificationSubject subject) {
		String clientName = subject.name().toLowerCase() + CLIENT_CLASS_NAME;
		return authClientMap.get(clientName);
	}

	public OAuthInfo getOAuthInfo(OAuthCommand command) {
		OAuthClient client = getOAuthClient(command.getCertificationSubject());
		OAuthProperty property = getOAuthProperty(command.getCertificationSubject());
		return client.readOAuthUserData(
				property, new Token(command.getAccessToken(), command.getIdToken()));
	}
}
