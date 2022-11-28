package com.depromeet.threedays.front.data.mate

import com.depromeet.threedays.data.entity.mate.MateEntity
import com.depromeet.threedays.front.persistence.repository.mate.MateRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class MateDataInitializer {
    @Autowired
    private MateRepository repository

    private MateEntity data

    void initialize(Long habitId) {
        repository.deleteByHabitId(habitId)
        this.save(habitId)
    }

    MateEntity getData() {
        return this.data
    }

    private void save(Long habitId) {
        this.data = repository.save(FakeMateEntity.create(habitId))
    }
}
