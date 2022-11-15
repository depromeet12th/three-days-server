package com.depromeet.threedays.front.domain.converter.member;

import com.depromeet.threedays.data.entity.member.MemberEntity;
import com.depromeet.threedays.front.domain.command.SaveMemberCommand;
import com.depromeet.threedays.front.domain.model.member.Member;

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

	public static MemberEntity to(final SaveMemberCommand command) {
		if (command == null) {
			return null;
		}

		return MemberEntity.builder()
				.name(command.getName())
				.profile(MemberInfoConverter.convert(command.getMemberInfo()))
				.certificationSubject(command.getCertificationSubject())
				.build();
	}
}
