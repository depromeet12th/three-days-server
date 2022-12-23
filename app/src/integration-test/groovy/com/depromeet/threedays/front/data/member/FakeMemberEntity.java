package com.depromeet.threedays.front.data.member;

import com.depromeet.threedays.data.entity.member.MemberEntity;
import com.depromeet.threedays.data.enums.CertificationSubject;
import com.depromeet.threedays.data.enums.MemberStatus;
import net.bytebuddy.utility.RandomString;

import java.time.LocalDateTime;

public class FakeMemberEntity {
	private FakeMemberEntity() {
	}

	public static MemberEntity create() {
		return MemberEntity.builder()
				.certificationId(RandomString.make())
				.name(RandomString.make())
				.certificationSubject(CertificationSubject.GOOGLE)
				.status(MemberStatus.REGULAR)
				.resource("{\"te\":\"te\"}")
				.notificationConsent(true)
				.createAt(LocalDateTime.now())
				.updateAt(LocalDateTime.now())
				.build();
	}
}
