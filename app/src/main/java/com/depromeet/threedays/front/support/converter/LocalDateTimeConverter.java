package com.depromeet.threedays.front.support.converter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import org.apache.commons.lang3.StringUtils;

public class LocalDateTimeConverter {

	private LocalDateTimeConverter() {
		throw new UnsupportedOperationException();
	}

	public static String to(final LocalDateTime localDateTime) {
		return to(localDateTime, LocalDateTimeConverter.buildFormatter());
	}

	public static String to(final LocalDateTime localDateTime, final DateTimeFormatter formatter) {
		return localDateTime != null ? localDateTime.format(formatter) : null;
	}

	public static LocalDateTime from(final String str) {
		return StringUtils.isNotBlank(str)
				? LocalDateTime.parse(str, LocalDateTimeConverter.buildFormatter())
				: null;
	}

	private static DateTimeFormatter buildFormatter() {
		return new DateTimeFormatterBuilder()
				.parseCaseInsensitive()
				.append(DateTimeFormatter.ISO_LOCAL_DATE)
				.appendLiteral(' ')
				.append(DateTimeFormatter.ISO_LOCAL_TIME)
				.toFormatter();
	}
}
