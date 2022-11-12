package com.depromeet.threedays.front.domain.converter.member;

import com.depromeet.threedays.front.client.model.MemberInfo;
import com.depromeet.threedays.front.controller.request.member.SignMemberRequest;
import com.depromeet.threedays.front.domain.command.SaveMemberCommand;
import lombok.experimental.UtilityClass;

@UtilityClass
public class MemberCommandConverter {

	public static SaveMemberCommand from(final MemberInfo data, final SignMemberRequest request) {
		if (data == null || request == null) {
			return null;
		}

		return SaveMemberCommand.builder()
				.name(data.getName())
				.certificationSubject(request.getCertificationSubject())
				.memberInfo(data)
				.certificationId(Long.parseLong(data.getId()))
				.build();
	}
}
