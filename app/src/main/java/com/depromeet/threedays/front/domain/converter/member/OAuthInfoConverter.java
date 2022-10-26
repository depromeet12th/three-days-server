package com.depromeet.threedays.front.domain.converter.member;

import com.depromeet.threedays.front.client.model.OAuthInfo;
import com.depromeet.threedays.front.exception.PolicyViolationException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OAuthInfoConverter {

	private static ObjectMapper objectMapper;

	@Autowired
	public OAuthInfoConverter(ObjectMapper objectMapper) {
		OAuthInfoConverter.objectMapper = objectMapper;
	}

	public static OAuthInfo from(final String profile) {
		if (profile == null) {
			return null;
		}
		try {
			return objectMapper.readValue(profile, OAuthInfo.class);
		} catch (JsonProcessingException e) {
			throw new PolicyViolationException("1111", e);
		}
	}

	public static String to(final OAuthInfo data) {
		if (data == null) {
			return null;
		}
		try {
			return objectMapper.writeValueAsString(data);
		} catch (JsonProcessingException e) {
			throw new PolicyViolationException("1111", e);
		}
	}
}
