package com.depromeet.threedays.front.data.habit

import com.depromeet.threedays.data.entity.notification.HabitNotificationEntity
import net.bytebuddy.utility.RandomString

import java.time.DayOfWeek
import java.time.LocalDateTime
import java.time.LocalTime

class FakeHabitNotificationEntity {

    static HabitNotificationEntity create(Long habitId, Long memberId,DayOfWeek dayOfWeek) {
        return HabitNotificationEntity.builder().habitId(habitId)
                .memberId(memberId)
                .dayOfWeek(dayOfWeek)
                .notificationTime(LocalTime.now().minusHours(10))
                .contents(RandomString.make())
                .createAt(LocalDateTime.now())
                .build()
    }
}
