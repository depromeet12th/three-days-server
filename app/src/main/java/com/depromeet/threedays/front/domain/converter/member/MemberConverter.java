package com.depromeet.threedays.front.domain.converter.member;

import com.depromeet.threedays.data.entity.member.MemberEntity;
import com.depromeet.threedays.front.domain.command.SaveMemberCommand;
import com.depromeet.threedays.front.domain.model.member.Member;
import com.depromeet.threedays.front.domain.model.member.SaveMemberUseCaseResponse;
import com.depromeet.threedays.front.domain.model.member.Token;
import com.depromeet.threedays.front.support.converter.MemberInfoJsonConverter;
import com.depromeet.threedays.front.web.response.SaveMemberResponse;
import com.depromeet.threedays.front.web.response.TokenResponse;

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
				.notificationConsent(entity.getNotificationConsent())
				.status(entity.getStatus())
				.resource(MemberInfoJsonConverter.from(entity.getResource()))
				.build();
	}

	public static SaveMemberResponse to(SaveMemberUseCaseResponse saveMemberUseCaseResponse) {
		if (saveMemberUseCaseResponse == null) {
			return null;
		}
		return SaveMemberResponse.builder()
				.id(saveMemberUseCaseResponse.getId())
				.name(saveMemberUseCaseResponse.getName())
				.tokenResponse(TokenResponse.from(saveMemberUseCaseResponse.getToken()))
				.certificationSubject(saveMemberUseCaseResponse.getCertificationSubject())
				.resource(saveMemberUseCaseResponse.getResource())
				.notificationConsent(saveMemberUseCaseResponse.getNotificationConsent())
				.build();
	}
}
