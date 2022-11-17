package com.depromeet.threedays.front.domain.converter.client;

import com.depromeet.threedays.front.controller.request.member.ClientRequest;
import com.depromeet.threedays.front.domain.command.client.SaveClientCommand;
import com.depromeet.threedays.front.domain.command.client.UpdateClientCommand;
import lombok.experimental.UtilityClass;

@UtilityClass
public class ClientCommandConverter {
	public static SaveClientCommand from(final Long memberId, final ClientRequest request) {
		if (memberId == null || request == null) {
			return null;
		}
		return SaveClientCommand.builder()
				.memberId(memberId)
				.fcmToken(request.getFcmToken())
				.identificationKey(request.getIdentificationKey())
				.build();
	}

	public static UpdateClientCommand fromUpdate(final Long id, final ClientRequest request) {
		if (id == null || request == null) {
			return null;
		}
		return UpdateClientCommand.builder().id(id).fcmToken(request.getFcmToken()).build();
	}
}
