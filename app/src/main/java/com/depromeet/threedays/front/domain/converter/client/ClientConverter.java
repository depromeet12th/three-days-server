package com.depromeet.threedays.front.domain.converter.client;

import com.depromeet.threedays.data.entity.client.ClientEntity;
import com.depromeet.threedays.front.domain.command.client.SaveClientCommand;
import com.depromeet.threedays.front.domain.model.client.Client;

public class ClientConverter {
	private ClientConverter() {
		throw new UnsupportedOperationException();
	}

	public static Client from(final ClientEntity entity) {
		if (entity == null) {
			return null;
		}

		return Client.builder()
				.id(entity.getId())
				.fcmToken(entity.getFcmToken())
				.identificationKey(entity.getIdentificationKey())
				.memberId(entity.getMemberId())
				.build();
	}

	public static ClientEntity to(final SaveClientCommand command) {
		if (command == null) {
			return null;
		}
		return ClientEntity.builder()
				.memberId(command.getMemberId())
				.fcmToken(command.getFcmToken())
				.identificationKey(command.getIdentificationKey())
				.build();
	}
}
