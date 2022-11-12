package com.depromeet.threedays.front.domain.converter.member;

import com.depromeet.threedays.front.controller.request.member.SignMemberRequest;
import com.depromeet.threedays.front.domain.query.GetMemberQuery;
import lombok.experimental.UtilityClass;

@UtilityClass
public class MemberQueryConverter {

	public static GetMemberQuery from(final Long certificationId, final SignMemberRequest request) {
		return GetMemberQuery.builder()
				.certificationId(certificationId)
				.certificationSubject(request.getCertificationSubject())
				.build();
	}

}
