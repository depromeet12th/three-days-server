package com.depromeet.threedays.front.support;

import com.depromeet.threedays.front.domain.query.member.AppleAuthRequestWithCodeQuery;
import com.depromeet.threedays.front.domain.query.member.AppleAuthRequestWithTokenQuery;
import com.depromeet.threedays.front.domain.query.member.AppleAuthRevokeRequestQuery;
import java.util.HashMap;
import java.util.Map;
import lombok.experimental.UtilityClass;

@UtilityClass
public class RequestBodyGenerator {

	private static final String CLIENT_ID_KEY = "client_id";
	private static final String CLIENT_SECRET_KEY = "client_secret";
	private static final String CODE_KEY = "code";
	private static final String REFRESH_TOKEN_KEY = "refresh_token";
	private static final String GRANT_TYPE_KEY = "grant_type";
	private static final String TOKEN_KEY = "token";
	private static final String TOKEN_TYPE_HINT = "token_type_hint";
	private static final String AUTHORIZATION_CODE_VALUE = "authorization_code";
	private static final String REFRESH_TOKEN_VALUE = "refresh_token";

	public static Map<String, String> generateAppleAuthRequestBody(
			AppleAuthRequestWithCodeQuery query) {
		Map<String, String> body = new HashMap<>();
		body.put(CLIENT_ID_KEY, query.getClientId());
		body.put(CLIENT_SECRET_KEY, query.getClientSecret());
		body.put(GRANT_TYPE_KEY, AUTHORIZATION_CODE_VALUE);
		body.put(CODE_KEY, query.getCode());
		return body;
	}

	public static Map<String, String> generateAppleAuthRequestBody(
			AppleAuthRequestWithTokenQuery query) {
		Map<String, String> body = new HashMap<>();
		body.put(CLIENT_ID_KEY, query.getClientId());
		body.put(CLIENT_SECRET_KEY, query.getClientSecret());
		body.put(GRANT_TYPE_KEY, REFRESH_TOKEN_VALUE);
		body.put(REFRESH_TOKEN_KEY, query.getRefreshToken());
		return body;
	}

	public static Map<String, String> generateAppleAuthRevokeRequestBody(
			AppleAuthRevokeRequestQuery query) {
		Map<String, String> body = new HashMap<>();
		body.put(CLIENT_ID_KEY, query.getClientId());
		body.put(CLIENT_SECRET_KEY, query.getClientSecret());
		body.put(TOKEN_KEY, query.getToken());
		body.put(TOKEN_TYPE_HINT, REFRESH_TOKEN_VALUE);
		return body;
	}
}
