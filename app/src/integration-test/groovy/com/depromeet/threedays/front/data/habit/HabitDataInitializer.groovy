package com.depromeet.threedays.front.data.habit

import com.depromeet.threedays.data.entity.habit.HabitEntity
import com.depromeet.threedays.data.entity.mate.MateEntity
import com.depromeet.threedays.data.enums.MateStatus
import com.depromeet.threedays.data.enums.MateType
import com.depromeet.threedays.front.data.mate.FakeMateEntity
import com.depromeet.threedays.front.persistence.repository.habit.HabitRepository
import com.depromeet.threedays.front.persistence.repository.mate.MateRepository
import net.bytebuddy.utility.RandomString
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

import java.time.LocalDateTime

@Component
class HabitDataInitializer {

    private static final int DEFAULT_DATA_SIZE = 10

    @Autowired
    private HabitRepository repository

    @Autowired
    private MateRepository mateRepository

    private Collection<HabitEntity> data
    private MateEntity associationData

    void initialize() {
        repository.deleteAll()
        mateRepository.deleteAll()

        this.setData()
        this.setAssociation(data.first().id)
    }

    void initialize(Long memberId) {
        repository.deleteAll()
        this.save(memberId)
    }

    private void save(Long memberId) {
        this.data.add(repository.save(FakeHabitEntity.create(memberId)))
    }

    Collection<HabitEntity> getData() {
        return this.data
    }

    MateEntity getAssociationData() {
        return this.associationData
    }

    private void setData() {
        Collection<HabitEntity> data = new ArrayList<>()
        for (int i = 0; i < DEFAULT_DATA_SIZE; i++) {
            data.add(repository.save(FakeHabitEntity.create()))
        }

        this.data = data
    }

    private void setAssociation(final Long habitId) {
        def mateEntity = MateEntity.builder()
                .memberId(0L)
                .title(RandomString.make())
                .habitId(habitId)
                .level(3)
                .levelUpAt(LocalDateTime.now())
                .characterType(MateType.CARROT)
                .createAt(LocalDateTime.now())
                .updateAt(LocalDateTime.now())
                .deleted(false)
                .status(MateStatus.ACTIVE)
                .build();

        this.associationData = mateRepository.save(mateEntity)
    }

}
