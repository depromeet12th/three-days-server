package com.depromeet.threedays.front.domain.converter.client;

import com.depromeet.threedays.front.domain.query.client.GetClientQuery;
import com.depromeet.threedays.front.web.request.member.ClientRequest;
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
