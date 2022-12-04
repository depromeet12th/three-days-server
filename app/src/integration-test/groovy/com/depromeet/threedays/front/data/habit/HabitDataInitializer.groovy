package com.depromeet.threedays.front.data.habit

import com.depromeet.threedays.data.entity.habit.HabitEntity
import com.depromeet.threedays.front.persistence.repository.habit.HabitRepository
import com.depromeet.threedays.front.persistence.repository.mate.MateRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class HabitDataInitializer {

    private static final int DEFAULT_DATA_SIZE = 10

    @Autowired
    private HabitRepository repository

    @Autowired
    private MateRepository mateRepository

    private Collection<HabitEntity> data

    void initialize() {
        repository.deleteAll()
        mateRepository.deleteAll()

        this.setData()
    }

    Collection<HabitEntity> getData() {
        return this.data
    }

    private void setData() {
        Collection<HabitEntity> data = new ArrayList<>()
        for (int i = 0; i < DEFAULT_DATA_SIZE; i++) {
            data.add(repository.save(FakeHabitEntity.create()))
        }

        this.data = data
    }

}
