package com.depromeet.threedays.front.domain.converter.member;

import com.depromeet.threedays.front.client.model.OAuthInfo;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

public class OAuthInfoConverter {

	private static final ObjectMapper objectMapper =
			new ObjectMapper()
					.setSerializationInclusion(JsonInclude.Include.NON_NULL)
					.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
					.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);

	public static OAuthInfo from(final String profile) throws JsonProcessingException {
		if (profile == null) {
			return null;
		}
		return objectMapper.readValue(profile, OAuthInfo.class);
	}

	public static String to(final OAuthInfo data) throws JsonProcessingException {
		if (data == null) {
			return null;
		}
		return objectMapper.writeValueAsString(data);
	}
}
