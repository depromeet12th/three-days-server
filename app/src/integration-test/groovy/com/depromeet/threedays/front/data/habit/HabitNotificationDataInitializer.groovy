package com.depromeet.threedays.front.data.habit

import com.depromeet.threedays.data.entity.notification.HabitNotificationEntity
import com.depromeet.threedays.front.persistence.repository.notification.HabitNotificationRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

import java.time.DayOfWeek

@Component
class HabitNotificationDataInitializer {

    @Autowired
    private HabitNotificationRepository repository

    private Collection<HabitNotificationEntity> data

    void initialize(Long habitId, Long memberId) {
        repository.deleteAll()
        this.setData(habitId, memberId)
    }

    void setData(Long habitId, Long memberId) {
        Collection<HabitNotificationEntity> data = new ArrayList<>()
        for (DayOfWeek dayOfWeek : DayOfWeek.values()) {
            data.add(repository.save(FakeHabitNotificationEntity.create(habitId, memberId, dayOfWeek)))
        }
        this.data = data
    }

    Collection<HabitNotificationEntity> getData() {
        return data
    }
}
