package com.depromeet.threedays.front.support.converter;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import java.io.IOException;
import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.boot.jackson.JsonComponent;

@JsonComponent
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class LocalDateTimeJsonConverter {

	public static class Serializer extends JsonSerializer<LocalDateTime> {

		@Override
		public void serialize(
				LocalDateTime dateTime, JsonGenerator jsonGenerator,
				SerializerProvider serializerProvider)
				throws IOException {
			jsonGenerator.writeString(LocalDateTimeConverter.to(dateTime));
		}
	}

	public static class Deserializer extends JsonDeserializer<LocalDateTime> {

		@Override
		public LocalDateTime deserialize(
				JsonParser jsonParser, DeserializationContext deserializationContext)
				throws IOException {
			return LocalDateTimeConverter.from(jsonParser.getText());
		}
	}
}
