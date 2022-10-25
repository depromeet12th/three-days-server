package com.depromeet.threedays.front.client;

import com.depromeet.threedays.front.client.model.OAuthInfo;
import com.depromeet.threedays.front.client.property.OAuthProperty;
import com.depromeet.threedays.front.domain.model.Token;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.client.WebClient;

@Getter
@RequiredArgsConstructor
public abstract class OAuthClient {

	public static final String GRANT_TYPE = "authorization_code";

	protected final WebClient webClient;

	/** token을 통해 info를 요청할 때 service에서 호출하는 메소드 */
	public OAuthInfo readOAuthUserData(OAuthProperty property, Token token) {
		return getOAuthInfo(property, token);
	}

	/** code를 통해 info를 요청할 때 service에서 호출하는 메소드 */
	public OAuthInfo readOAuthUserData(OAuthProperty property, String code) {
		return getOAuthInfo(property, getToken(property, code));
	}

	public MultiValueMap<String, String> writeBodyDataForToken(
			OAuthProperty oAuthProperty, String code) {
		MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
		body.add("grant_type", GRANT_TYPE);
		body.add("client_id", oAuthProperty.getClientId());
		body.add("redirect_uri", oAuthProperty.getRedirectUri());
		body.add("client_secret", oAuthProperty.getClientSecret());
		body.add("code", code);
		return body;
	}

	public MultiValueMap<String, String> writeBodyDataForInfo(
			OAuthProperty oAuthProperty, Token token) {
		MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
		// TODO: add body for get info
		return body;
	}

	abstract Token getToken(OAuthProperty oAuthProperty, String code);

	abstract OAuthInfo getOAuthInfo(OAuthProperty oAuthProperty, Token accessToken);
}
