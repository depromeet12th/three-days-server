package com.depromeet.threedays.front.domain.converter.member;

import com.depromeet.threedays.front.client.model.MemberInfo;
import com.depromeet.threedays.front.domain.command.SaveMemberCommand;
import com.depromeet.threedays.front.support.converter.MemberInfoJsonConverter;
import com.depromeet.threedays.front.web.request.member.SignMemberRequest;
import lombok.experimental.UtilityClass;

@UtilityClass
public class MemberCommandConverter {

	public static SaveMemberCommand from(final MemberInfo data, final SignMemberRequest request) {
		if (data == null || request == null) {
			return null;
		}

		return SaveMemberCommand.builder()
				.resource(MemberInfoJsonConverter.to(data))
				.name(data.getName())
				.certificationSubject(request.getCertificationSubject())
				.memberInfo(data)
				.certificationId(data.getId())
				.notificationConsent(true)
				.build();
	}
}
