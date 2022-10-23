package com.depromeet.threedays.data.entity.member.converter;

import com.depromeet.threedays.data.entity.member.Profile;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import javax.persistence.AttributeConverter;

public class ProfileAttributeConverter implements AttributeConverter<Profile, String> {

	private final ObjectMapper objectMapper =
			new ObjectMapper()
					.setSerializationInclusion(JsonInclude.Include.NON_NULL)
					.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
					.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);

	@Override
	public String convertToDatabaseColumn(Profile attribute) {
		try {
			return objectMapper.writeValueAsString(attribute);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public Profile convertToEntityAttribute(String dbData) {
		try {
			return objectMapper.readValue(dbData, Profile.class);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
}
