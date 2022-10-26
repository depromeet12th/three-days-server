package com.depromeet.threedays.front.client;

import com.depromeet.threedays.data.entity.member.certification.CertificationSubject;
import com.depromeet.threedays.front.client.property.OAuthProperty;
import java.util.Map;
import javax.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OAuthManager {

	private final Map<String, OAuthProperty> authPropertyMap;
	private final Map<String, OAuthClient> authClientMap;

	private static final String PROPERTY_CLASS_NAME = "OAuthProperty";
	private static final String CLIENT_CLASS_NAME = "OAuthClient";

	@PostConstruct
	private void init() {
		for (String i : authPropertyMap.keySet()) {
			System.out.println("authProperty: " + authPropertyMap.get(i));
		}
		for (String i : authClientMap.keySet()) {
			System.out.println("authClient: " + authClientMap.get(i));
		}
	}

	public OAuthProperty getOAuthProperty(CertificationSubject subject) {
		String propertyName = subject.toString().toLowerCase() + PROPERTY_CLASS_NAME;
		return authPropertyMap.get(propertyName);
	}

	public OAuthClient getOAuthClient(CertificationSubject subject) {
		String clientName = subject.toString().toLowerCase() + CLIENT_CLASS_NAME;
		return authClientMap.get(clientName);
	}
}
