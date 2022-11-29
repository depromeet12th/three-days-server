package com.depromeet.threedays.front.data.habit;

import com.depromeet.threedays.data.entity.member.MemberEntity;
import com.depromeet.threedays.data.enums.CertificationSubject;
import net.bytebuddy.utility.RandomString;

public class FakeMemberEntity {

	static MemberEntity create() {
		return MemberEntity.builder()
				.name(RandomString.make())
				.profile("empty")
				.certificationId(RandomString.make())
				.certificationSubject(CertificationSubject.GOOGLE)
				.build();
	}
}
