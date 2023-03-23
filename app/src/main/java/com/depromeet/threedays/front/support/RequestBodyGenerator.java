package com.depromeet.threedays.front.support;

import java.util.HashMap;
import java.util.Map;
import lombok.experimental.UtilityClass;

@UtilityClass
public class RequestBodyGenerator {

	private static final String CLIENT_ID_KEY = "client_id";
	private static final String CLIENT_SECRET_KEY = "client_secret";
	private static final String CODE_KEY = "code";
	private static final String GRANT_TYPE_KEY = "grant_type";
	private static final String AUTHORIZATION_CODE_VALUE = "authorization_code";

	public static Map<String, String> generateAppleAuthRequestBody(
			String clientId, String code, String clientSecret) {
		Map<String, String> body = new HashMap<>();
		body.put(CLIENT_ID_KEY, clientId);
		body.put(CLIENT_SECRET_KEY, clientSecret);
		body.put(CODE_KEY, code);
		body.put(GRANT_TYPE_KEY, AUTHORIZATION_CODE_VALUE);
		return body;
	}
}
