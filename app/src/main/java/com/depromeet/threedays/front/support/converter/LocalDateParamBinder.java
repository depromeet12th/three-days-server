package com.depromeet.threedays.front.support.converter;

import java.time.LocalDate;
import org.springframework.core.convert.converter.Converter;

public class LocalDateParamBinder implements Converter<String, LocalDate> {

	@Override
	public LocalDate convert(final String date) {
		return LocalDateConverter.from(date);
	}
}
