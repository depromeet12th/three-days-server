package com.depromeet.threedays.front.domain.converter.client;

import com.depromeet.threedays.front.controller.request.member.ClientRequest;
import com.depromeet.threedays.front.domain.query.client.GetClientQuery;
import lombok.experimental.UtilityClass;

@UtilityClass
public class ClientQueryConverter {
	public static GetClientQuery from(final Long memberId, final ClientRequest request) {
		return GetClientQuery.builder()
				.identificationKey(request.getIdentificationKey())
				.memberId(memberId)
				.build();
	}
}
