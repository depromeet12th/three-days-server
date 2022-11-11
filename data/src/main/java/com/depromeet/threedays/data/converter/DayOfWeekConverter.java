package com.depromeet.threedays.data.converter;

import com.depromeet.threedays.data.enums.DayOfWeek;
import java.util.Arrays;
import java.util.EnumSet;
import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import org.springframework.util.StringUtils;

@Converter
public class DayOfWeekConverter implements AttributeConverter<EnumSet<DayOfWeek>, String> {

	@Override
	public String convertToDatabaseColumn(EnumSet<DayOfWeek> attribute) {
		StringBuilder sb = new StringBuilder();
		attribute.forEach(e -> sb.append(e.name()).append(","));
		String result = sb.toString();
		if (result.charAt(result.length() - 1) == ',')
			result = result.substring(0, result.length() - 1);
		return result;
	}

	@Override
	public EnumSet<DayOfWeek> convertToEntityAttribute(String dbData) {
		if (dbData == null || "".equals(dbData) || dbData.contains(".")) {
			return EnumSet.noneOf(DayOfWeek.class);
		}

		EnumSet<DayOfWeek> attribute = EnumSet.noneOf(DayOfWeek.class);
		String[] dbDataArray = StringUtils.trimAllWhitespace(dbData).toUpperCase().split(",");
		Arrays.stream(dbDataArray).forEach(e -> attribute.add(DayOfWeek.valueOf(e)));

		return attribute;
	}
}
