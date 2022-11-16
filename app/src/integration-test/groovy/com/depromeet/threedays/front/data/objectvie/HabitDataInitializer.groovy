package com.depromeet.threedays.front.data.objectvie

import com.depromeet.threedays.data.entity.habit.HabitEntity
import com.depromeet.threedays.front.repository.habit.HabitRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class HabitDataInitializer {

    private static final int DEFAULT_DATA_SIZE = 10

    @Autowired
    private HabitRepository repository

    private Collection<HabitEntity> data

    void initialize() {
        repository.deleteAll()

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
