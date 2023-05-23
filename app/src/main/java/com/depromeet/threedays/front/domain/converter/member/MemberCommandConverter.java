package com.depromeet.threedays.front.domain.converter.member;

import com.depromeet.threedays.front.client.model.AppleMemberInfo;
import com.depromeet.threedays.front.client.model.MemberInfo;
import com.depromeet.threedays.front.domain.command.SaveMemberCommand;
import com.depromeet.threedays.front.web.request.member.SignMemberRequest;
import com.google.gson.JsonObject;
import lombok.experimental.UtilityClass;

@UtilityClass
public class MemberCommandConverter {

	public static SaveMemberCommand from(final MemberInfo data, final SignMemberRequest request) {
		if (data == null || request == null) {
			return null;
		}

		return SaveMemberCommand.builder()
				.resource(new JsonObject().toString())
				.name(data.getName(request.getCertificationSubject()))
				.certificationSubject(request.getCertificationSubject())
				.memberInfo(data)
				.certificationId(data.getId())
				.notificationConsent(true)
				.build();
	}

	public static SaveMemberCommand from(
			final AppleMemberInfo data, final SignMemberRequest request) {
		if (data == null || request == null) {
			return null;
		}

		return SaveMemberCommand.builder()
				.resource(getResource(data).toString())
				.name(data.getName(request.getCertificationSubject()))
				.certificationSubject(request.getCertificationSubject())
				.memberInfo(data)
				.certificationId(data.getId())
				.notificationConsent(true)
				.build();
	}

	private static JsonObject getResource(AppleMemberInfo data) {
		JsonObject resource = new JsonObject();
		resource.addProperty("refreshToken", data.getRefreshToken());
		return resource;
	}
}
