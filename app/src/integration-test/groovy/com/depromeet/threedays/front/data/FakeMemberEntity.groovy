package com.depromeet.threedays.front.data

import com.depromeet.threedays.data.entity.member.MemberEntity
import net.bytebuddy.utility.RandomString

class FakeMemberEntity {

    static MemberEntity createBy(final Long id){
        if(id == null){
            return null
        }

        return MemberEntity.builder()
                .id(id)
                .name(RandomString.make())
                .build()
    }

    static MemberEntity create(){
        return MemberEntity.builder()
                .name(RandomString.make())
                .build()
    }
}
