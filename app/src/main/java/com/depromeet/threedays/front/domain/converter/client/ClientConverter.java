package com.depromeet.threedays.front.domain.converter.client;

import com.depromeet.threedays.data.entity.client.ClientEntity;
import com.depromeet.threedays.front.domain.model.client.Client;
import com.depromeet.threedays.front.web.request.client.ClientRequest;

import java.time.LocalDateTime;

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

	public static ClientEntity to(final Long memberId, final ClientRequest request) {
		if (memberId == null || request == null) {
			return null;
		}

		return ClientEntity.builder()
				.memberId(memberId)
				.fcmToken(request.getFcmToken())
				.identificationKey(request.getIdentificationKey())
				.updateAt(LocalDateTime.now())
				.build();
	}
}
