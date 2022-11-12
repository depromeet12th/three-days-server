package com.depromeet.threedays.front.config;

import com.depromeet.threedays.front.support.converter.LocalDateJsonConverter;
import com.depromeet.threedays.front.support.converter.LocalDateParamBinder;
import com.depromeet.threedays.front.support.converter.LocalDateTimeJsonConverter;
import com.depromeet.threedays.front.support.converter.LocalDateTimeParamBinder;
import com.depromeet.threedays.front.support.converter.LocalTimeParamBinder;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@RequiredArgsConstructor
public class WebMvcConfig implements WebMvcConfigurer {

	@Override
	public void addFormatters(FormatterRegistry registry) {
		registry.addConverter(new LocalDateParamBinder());
		registry.addConverter(new LocalTimeParamBinder());
		registry.addConverter(new LocalDateTimeParamBinder());
	}

	@Bean
	public ObjectMapper objectMapper() {
		ObjectMapper objectMapper = new ObjectMapper();

		objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		objectMapper.configure(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT, true);
		objectMapper.registerModule(javaTimeModule());
		objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

		return objectMapper;
	}

	@Override
	public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
		argumentResolvers.add(new PageableHandlerMethodArgumentResolver());
	}

	private SimpleModule javaTimeModule() {
		return new JavaTimeModule()
				.addSerializer(LocalDateTime.class, new LocalDateTimeJsonConverter.Serializer())
				.addDeserializer(LocalDateTime.class, new LocalDateTimeJsonConverter.Deserializer())
				.addSerializer(LocalDate.class, new LocalDateJsonConverter.Serializer())
				.addDeserializer(LocalDate.class, new LocalDateJsonConverter.Deserializer());
	}
}
