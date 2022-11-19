package com.depromeet.threedays.front.data.member

import com.depromeet.threedays.data.entity.member.MemberEntity
import com.depromeet.threedays.front.data.habit.FakeMemberEntity
import com.depromeet.threedays.front.persistence.repository.member.MemberRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class MemberDataInitializer {
    private static final int DEFAULT_DATA_SIZE = 10

    @Autowired
    private MemberRepository repository

    private Collection<MemberEntity> data

    void initialize() {
        repository.deleteAll()
        this.setData()
    }

    Collection<MemberEntity> getData() {
        return this.data
    }

    private void setData() {
        Collection<MemberEntity> data = new ArrayList<>()
        for (int i = 0; i < DEFAULT_DATA_SIZE; i++) {
            data.add(repository.save(FakeMemberEntity.create()))
        }
        this.data = data
    }
}
