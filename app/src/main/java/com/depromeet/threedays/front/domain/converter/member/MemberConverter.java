package com.depromeet.threedays.front.domain.converter.member;

import com.depromeet.threedays.data.entity.MemberEntity;
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

		return Member.builder().memberId(entity.getId()).nickname(entity.getNickname()).build();
	}

	public static MemberEntity to(final Member data) {
		if (data == null) {
			return null;
		}

		return MemberEntity.builder().id(data.getMemberId()).nickname(data.getNickname()).build();
	}

	public static MemberEntity to(final SaveMemberCommand data) {
		if (data == null) {
			return null;
		}

		return MemberEntity.builder().nickname(data.getNickname()).build();
	}
}
