package com.depromeet.threedays.front.support.converter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import org.apache.commons.lang3.StringUtils;

public class LocalDateTimeConverter {

	private static final DateTimeFormatter DEFAULT_FORMATTER = DateTimeFormatter.ISO_LOCAL_DATE_TIME;

	private LocalDateTimeConverter() {
		throw new UnsupportedOperationException();
	}

	public static String to(final LocalDateTime localDateTime) {
		return to(localDateTime, DEFAULT_FORMATTER);
	}

	public static String to(final LocalDateTime localDateTime, final DateTimeFormatter formatter) {
		return localDateTime != null ? localDateTime.format(formatter) : null;
	}

	public static LocalDateTime from(final String str) {
		return StringUtils.isNotBlank(str) ? LocalDateTime.parse(str, DEFAULT_FORMATTER) : null;
	}
}
