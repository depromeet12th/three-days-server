package com.depromeet.threedays.front.domain.converter.member;

import com.depromeet.threedays.data.entity.member.MemberEntity;
import com.depromeet.threedays.front.client.model.MemberInfo;
import com.depromeet.threedays.front.controller.command.oauth.SignMemberRequest;
import com.depromeet.threedays.front.domain.model.Member;

public class MemberConverter {

	private MemberConverter() {
		throw new UnsupportedOperationException();
	}

	public static Member from(final MemberEntity entity, boolean isNew) {
		if (entity == null) {
			return null;
		}

		// TODO: 토큰 생성하여 넣어주는 로직 추가

		return Member.builder().memberId(entity.getId()).name(entity.getName()).isNew(isNew).build();
	}

	public static MemberEntity to(final MemberInfo data, SignMemberRequest request) {
		if (data == null) {
			return null;
		}

		return MemberEntity.builder()
				.name(data.getName())
				.profile(MemberInfoConverter.convert(data))
				.certificationSubject(request.getCertificationSubject())
				.build();
	}
}
