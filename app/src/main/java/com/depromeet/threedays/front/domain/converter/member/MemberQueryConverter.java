package com.depromeet.threedays.front.domain.converter.member;

import com.depromeet.threedays.front.domain.query.GetMemberQuery;
import com.depromeet.threedays.front.web.request.member.SignMemberRequest;
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
