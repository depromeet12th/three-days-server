package com.depromeet.threedays.front.domain.converter.member;

import com.depromeet.threedays.front.client.model.MemberInfo;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.experimental.UtilityClass;

@UtilityClass
public class MemberInfoConverter {

	private static final ObjectMapper objectMapper = new ObjectMapper();

	public static MemberInfo convert(final String profile) {
		if (profile == null) {
			return null;
		}
		try {
			return objectMapper.readValue(profile, MemberInfo.class);
		} catch (JsonProcessingException e) {
			throw new IllegalArgumentException(e);
		}
	}

	public static String convert(final MemberInfo data) {
		if (data == null) {
			return null;
		}
		try {
			return objectMapper.writeValueAsString(data);
		} catch (JsonProcessingException e) {
			throw new IllegalArgumentException(e);
		}
	}
}
