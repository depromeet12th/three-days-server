package com.depromeet.threedays.front.support.converter;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import org.apache.commons.lang3.StringUtils;

public class LocalTimeConverter {

	private static final DateTimeFormatter DEFAULT_FORMATTER = DateTimeFormatter.ISO_LOCAL_TIME;

	private LocalTimeConverter() {
		throw new UnsupportedOperationException();
	}

	public static String to(final LocalTime localTime) {
		return to(localTime, DEFAULT_FORMATTER);
	}

	public static String to(final LocalTime localTime, final DateTimeFormatter formatter) {
		return localTime != null ? localTime.format(formatter) : null;
	}

	public static LocalTime from(final String str) {
		return StringUtils.isNotBlank(str) ? LocalTime.parse(str, DEFAULT_FORMATTER) : null;
	}
}
