package com.depromeet.threedays.front.domain.converter.member;

import com.depromeet.threedays.data.entity.member.MemberEntity;
import com.depromeet.threedays.front.client.model.OAuthInfo;
import com.depromeet.threedays.front.controller.command.member.SaveMemberCommand;
import com.depromeet.threedays.front.domain.model.Member;

public class MemberConverter {

	private MemberConverter() {
		throw new UnsupportedOperationException();
	}

	public static Member from(final MemberEntity entity) {
		if (entity == null) {
			return null;
		}

		return Member.builder().memberId(entity.getId()).name(entity.getName()).build();
	}

	public static MemberEntity to(final Member data) {
		if (data == null) {
			return null;
		}

		return MemberEntity.builder().id(data.getMemberId()).name(data.getName()).build();
	}

	public static MemberEntity to(final SaveMemberCommand data) {
		if (data == null) {
			return null;
		}
		return MemberEntity.builder().name(data.getName()).build();
	}

	public static MemberEntity to(final OAuthInfo data) {
		if (data == null) {
			return null;
		}
		// TODO: OAuthInfo 이용해서 MemberEntity build
		// 기존의 Profile을 OAuthInfo로 대체하면서, 어떻게 모듈 간의 의존성 끊을지 고민할 것
		return MemberEntity.builder().name(data.getName()).build();
	}
}
