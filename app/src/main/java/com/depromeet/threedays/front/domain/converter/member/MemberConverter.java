package com.depromeet.threedays.front.domain.converter.member;

import com.depromeet.threedays.data.entity.member.MemberEntity;
import com.depromeet.threedays.front.domain.command.SaveMemberCommand;
import com.depromeet.threedays.front.domain.model.member.Member;
import com.depromeet.threedays.front.domain.model.member.MemberOverview;
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
				.certificationSubject(entity.getCertificationSubject())
				.notificationConsent(entity.getNotificationConsent())
				.resource(entity.getResource())
				.build();
	}

	public static MemberEntity to(final SaveMemberCommand command) {
		if (command == null) {
			return null;
		}

		return MemberEntity.builder()
				.name(command.getName())
				.certificationId(command.getCertificationId())
				.certificationSubject(command.getCertificationSubject())
				.resource(command.getResource())
				.notificationConsent(command.getNotificationConsent())
				.build();
	}

	public static MemberOverview from(final MemberEntity entity){
		if(entity == null){
			return null;
		}
		return MemberOverview.builder()
				.certificationSubject(entity.getCertificationSubject())
				.id(entity.getId())
				.name(entity.getName())
				.resource(entity.getResource())
				.notificationConsent(entity.getNotificationConsent())
				.build();
	}
}
