package com.depromeet.threedays.front.data.objectvie

import com.depromeet.threedays.data.entity.objective.ObjectiveEntity
import com.depromeet.threedays.front.repository.objective.ObjectiveRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class ObjectiveDataInitializer {

    private static final int DEFAULT_DATA_SIZE = 10

    @Autowired
    private ObjectiveRepository repository

    private Collection<ObjectiveEntity> data

    void initialize() {
        repository.deleteAll()

        this.setData()
    }

    Collection<ObjectiveEntity> getData() {
        return this.data
    }

    private void setData() {
        Collection<ObjectiveEntity> data = new ArrayList<>()
        for (int i = 0; i < DEFAULT_DATA_SIZE; i++) {
            data.add(repository.save(FakeObjectiveEntity.create()))
        }

        this.data = data
    }

}
