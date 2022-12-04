package com.depromeet.threedays.front.domain.converter.member;

import com.depromeet.threedays.data.entity.member.MemberEntity;
import com.depromeet.threedays.front.domain.command.SaveMemberCommand;
import com.depromeet.threedays.front.domain.model.member.Member;
import com.depromeet.threedays.front.domain.model.member.Token;

public class MemberConverter {

	private MemberConverter() {
		throw new UnsupportedOperationException();
	}

	public static Member from(final MemberEntity entity, boolean isNew, Token token) {
		if (entity == null) {
			return null;
		}

		return Member.builder()
				.id(entity.getId())
				.name(entity.getName())
				.isNew(isNew)
				.token(token)
				.build();
	}

	public static Member from(final MemberEntity entity) {
		if (entity == null) {
			return null;
		}
		return Member.builder().id(entity.getId()).name(entity.getName()).isNew(false).build();
	}

	public static MemberEntity to(final SaveMemberCommand command) {
		if (command == null) {
			return null;
		}

		return MemberEntity.builder()
				.name(command.getName())
				.certificationId(command.getCertificationId())
				.certificationSubject(command.getCertificationSubject())
				.build();
	}
}
