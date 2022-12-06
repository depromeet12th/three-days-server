package com.depromeet.threedays.front.support.converter;

import com.depromeet.threedays.front.client.model.MemberInfo;
import com.depromeet.threedays.front.exception.JsonParsingException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MemberInfoJsonConverter {
	public static ObjectMapper objectMapper;

	@Autowired
	private MemberInfoJsonConverter(ObjectMapper mapper){
		objectMapper = mapper;
	}

	public static String to(MemberInfo info){
		try {
			return objectMapper.writeValueAsString(info);
		} catch (JsonProcessingException e) {
			throw new JsonParsingException("json.parsing.error");
		}
	}
}
