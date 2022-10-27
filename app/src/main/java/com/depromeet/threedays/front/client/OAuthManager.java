package com.depromeet.threedays.front.client;

import com.depromeet.threedays.data.entity.member.certification.CertificationSubject;
import com.depromeet.threedays.front.client.model.OAuthInfo;
import com.depromeet.threedays.front.client.property.OAuthProperty;
import com.depromeet.threedays.front.controller.command.oauth.OAuthCommand;
import com.depromeet.threedays.front.domain.model.Token;
import java.util.Collection;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OAuthManager {

	private final Map<String, OAuthProperty> authPropertyMap;
	private final Map<String, OAuthClient> authClientMap;

	public OAuthProperty getOAuthProperty(CertificationSubject subject) {
		Collection<String> oAuthProperty = authPropertyMap.keySet();
		String clientName =
				oAuthProperty.stream()
						.filter((k) -> k.startsWith(subject.name().toLowerCase()))
						.findFirst()
						.orElseThrow(() -> new NoSuchFieldError());
		return authPropertyMap.get(clientName);
	}

	public OAuthClient getOAuthClient(CertificationSubject subject) {
		Collection<String> oAuthClient = authClientMap.keySet();
		String clientName =
				oAuthClient.stream()
						.filter((k) -> k.startsWith(subject.name().toLowerCase()))
						.findFirst()
						.orElseThrow(() -> new NoSuchFieldError());
		return authClientMap.get(clientName);
	}

	public OAuthInfo getOAuthInfo(OAuthCommand command) {
		OAuthClient client = getOAuthClient(command.getCertificationSubject());
		OAuthProperty property = getOAuthProperty(command.getCertificationSubject());
		return client.readOAuthUserData(
				property, new Token(command.getAccessToken(), command.getIdToken()));
	}
}
