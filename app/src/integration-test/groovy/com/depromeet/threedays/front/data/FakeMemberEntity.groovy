package com.depromeet.threedays.front.data

import com.depromeet.threedays.data.entity.MemberEntity
import net.bytebuddy.utility.RandomString

class FakeMemberEntity {

    static MemberEntity createBy(final Long id){
        if(id == null){
            return null
        }

        return MemberEntity.builder()
                .id(id)
                .nickname(RandomString.make())
                .build()
    }

    static MemberEntity create(){
        return MemberEntity.builder()
                .nickname(RandomString.make())
                .build()
    }
}
