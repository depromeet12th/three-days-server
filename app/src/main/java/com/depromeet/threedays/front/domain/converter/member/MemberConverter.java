package com.depromeet.threedays.front.domain.converter.member;

import com.depromeet.threedays.data.entity.member.MemberEntity;
import com.depromeet.threedays.front.domain.command.SaveMemberCommand;
import com.depromeet.threedays.front.domain.model.member.Member;
import com.depromeet.threedays.front.domain.model.member.SaveMemberUseCaseResponse;
import com.depromeet.threedays.front.domain.model.member.Token;
import com.depromeet.threedays.front.support.converter.MemberInfoJsonConverter;
import com.depromeet.threedays.front.web.response.SaveMemberResponse;

import java.time.LocalDateTime;

public class MemberConverter {

	private MemberConverter() {
		throw new UnsupportedOperationException();
	}

	public static SaveMemberUseCaseResponse from(
			final MemberEntity entity, boolean isNew, Token token) {
		if (entity == null) {
			return null;
		}

		return SaveMemberUseCaseResponse.builder()
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
				.createAt(LocalDateTime.now())
				.notificationConsent(command.getNotificationConsent())
				.build();
	}

	public static Member from(final MemberEntity entity) {
		if (entity == null) {
			return null;
		}
		return Member.builder()
				.certificationSubject(entity.getCertificationSubject())
				.id(entity.getId())
				.name(entity.getName())
				.resource(MemberInfoJsonConverter.from(entity.getResource()))
				.notificationConsent(entity.getNotificationConsent())
				.build();
	}

	public static SaveMemberResponse to(SaveMemberUseCaseResponse member) {
		if (member == null) {
			return null;
		}
		return SaveMemberResponse.builder()
				.token(member.getToken())
				.id(member.getId())
				.name(member.getName())
				.certificationSubject(member.getCertificationSubject())
				.resource(member.getResource())
				.notificationConsent(member.getNotificationConsent())
				.build();
	}
}
