package com.depromeet.threedays.front.client.property.auth;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Getter
@Component
public class AppleAuthProperty extends AuthRequestProperty {

	@Value("${apple.key.uri}")
	private String keyURI;

	@Value("${apple.key_id}")
	private String keyId;

	@Value("${apple.client_id}")
	private String clientId;

	@Value("${apple.team_id}")
	private String teamId;

	public AppleAuthProperty(
			@Value("${apple.host}") String host,
			@Value("${apple.token.uri}") String uri,
			@Value("${apple.unlink.uri}") String unlink) {
		super(host, uri, unlink);
	}

	@Override
	public String getAdminKey() {
		return null;
	}
}
