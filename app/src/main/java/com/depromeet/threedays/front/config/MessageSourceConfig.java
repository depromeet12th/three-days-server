package com.depromeet.threedays.front.config;

import java.nio.charset.StandardCharsets;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;

@Configuration
@RequiredArgsConstructor
public class MessageSourceConfig {

	private static final List<String> MESSAGE_SOURCE_CLASSPATH_LIST =
			List.of("classpath:messages/member", "classpath:messages/objective");

	@Bean
	public ReloadableResourceBundleMessageSource messageSource() {
		ReloadableResourceBundleMessageSource messageSource =
				new ReloadableResourceBundleMessageSource();

		messageSource.setDefaultEncoding(StandardCharsets.UTF_8.name());
		for (String path : MESSAGE_SOURCE_CLASSPATH_LIST) {
			messageSource.addBasenames(path);
		}

		return messageSource;
	}
}
