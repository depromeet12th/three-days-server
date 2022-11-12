package com.depromeet.threedays.front.support.converter;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import java.io.IOException;
import java.time.LocalDate;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.boot.jackson.JsonComponent;

@JsonComponent
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class LocalDateJsonConverter {

	public static class Serializer extends JsonSerializer<LocalDate> {

		@Override
		public void serialize(
				LocalDate localDate, JsonGenerator jsonGenerator,
				SerializerProvider serializerProvider)
				throws IOException {
			jsonGenerator.writeString(LocalDateConverter.to(localDate));
		}
	}

	public static class Deserializer extends JsonDeserializer<LocalDate> {

		@Override
		public LocalDate deserialize(
				JsonParser jsonParser, DeserializationContext deserializationContext)
				throws IOException {
			return LocalDateConverter.from(jsonParser.getText());
		}
	}
}
