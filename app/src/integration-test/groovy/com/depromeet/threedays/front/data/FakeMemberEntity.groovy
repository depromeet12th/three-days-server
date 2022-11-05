package com.depromeet.threedays.front.data

import com.depromeet.threedays.data.entity.member.MemberEntity
import com.depromeet.threedays.data.entity.member.certification.Certification
import com.depromeet.threedays.data.entity.member.certification.CertificationSubject
import net.bytebuddy.utility.RandomString

class FakeMemberEntity {

    static MemberEntity createBy(final Long id){
        if(id == null){
            return null
        }

        Certification certification = Certification.builder().certificationId(RandomString.make()).certificationSubject(CertificationSubject.GOOGLE).build()
        return MemberEntity.builder()
                .id(id)
                .name(RandomString.make())
                .certification(certification)
                .fcmToken(RandomString.make())
                .profile(RandomString.make())
                .build()
    }

    static MemberEntity create(){
        Certification certification = Certification.builder().certificationId(RandomString.make()).certificationSubject(CertificationSubject.GOOGLE).build()
        return MemberEntity.builder()
                .name(RandomString.make())
                .certification(certification)
                .fcmToken(RandomString.make())
                .profile(RandomString.make())
                .build()
    }
}
