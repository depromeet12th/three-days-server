package com.depromeet.threedays.front.support.converter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import org.apache.commons.lang3.StringUtils;

public class LocalDateConverter {

	private static final DateTimeFormatter DEFAULT_FORMATTER = DateTimeFormatter.ISO_LOCAL_DATE;

	private LocalDateConverter() {
		throw new UnsupportedOperationException();
	}

	public static String to(final LocalDate localDate) {
		return to(localDate, DEFAULT_FORMATTER);
	}

	public static String to(final LocalDate localDate, final DateTimeFormatter formatter) {
		return localDate != null ? localDate.format(formatter) : null;
	}

	public static LocalDate from(final String str) {
		return StringUtils.isNotBlank(str) ? LocalDate.parse(str, DEFAULT_FORMATTER) : null;
	}
}
