package com.depromeet.threedays.front.data.habit;

import com.depromeet.threedays.data.entity.member.MemberEntity;
import com.depromeet.threedays.data.enums.CertificationSubject;
import net.bytebuddy.utility.RandomString;

public class FakeMemberEntity {

	static MemberEntity create() {
		return MemberEntity.builder()
				.name(RandomString.make())
				.certificationId(RandomString.make())
				.certificationSubject(CertificationSubject.GOOGLE)
				.notificationConsent(true)
				.resource("{\"te\":\"te\"}")
				.build();
	}
}
