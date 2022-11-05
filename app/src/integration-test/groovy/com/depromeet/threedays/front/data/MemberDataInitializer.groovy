package com.depromeet.threedays.front.data

import com.depromeet.threedays.data.entity.member.MemberEntity
import com.depromeet.threedays.front.repository.MemberRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class MemberDataInitializer {

    private static final int DEFAULT_DATA_SIZE = 10

    @Autowired
    private MemberRepository repository

    private Collection<MemberEntity> data

    void initialize(final Long id){
        repository.deleteAll()

        this.setData(id)
    }

    Collection<MemberEntity> getData(){
        return this.data
    }

    private void setData(final Long id){
        Collection<MemberEntity> data = new ArrayList<>()
        for (int i = 0; i < DEFAULT_DATA_SIZE; i++) {
            MemberEntity entity = repository.save(this.createEntity(i, id))
            data.add(entity)
        }

        this.data = data
    }

    @SuppressWarnings('GrMethodMayBeStatic')
    private MemberEntity createEntity(final int index, final Long id) {
        boolean isRandom = id == null || index > 0

        return isRandom ? FakeMemberEntity.create() : FakeMemberEntity.createBy(id)
    }
}
