package com.depromeet.threedays.front.domain.usecase.habit

import com.depromeet.threedays.front.IntegrationTestSpecification
import com.depromeet.threedays.front.data.habit.HabitDataInitializer
import com.depromeet.threedays.front.domain.model.notification.Notification
import com.depromeet.threedays.front.web.request.habit.UpdateHabitRequest
import org.springframework.beans.factory.annotation.Autowired
import spock.lang.Subject

import java.time.DayOfWeek
import java.time.LocalTime

class UpdateHabitUseCaseSpec extends IntegrationTestSpecification {

    @Subject
    @Autowired
    private UpdateHabitUseCase updateUseCase

    @Autowired
    private HabitDataInitializer dataInitializer

    def setup() {
        dataInitializer.initialize()
    }

    def "사용자는 정해진 명세에 맞춰 습관 정보를 갱신할 수 있다"() {
        given:
        def criterion = dataInitializer.data[2]
        def expected = UpdateHabitRequest.builder()
                .title("title")
                .imojiPath("imoji")
                .color("color")
                .dayOfWeeks(EnumSet.of(DayOfWeek.MONDAY, DayOfWeek.TUESDAY, DayOfWeek.FRIDAY))
                .notification(Notification.builder()
                        .contents("contents")
                        .notificationTime(LocalTime.now())
                        .build())
                .build()

        when:
        def actual = updateUseCase.execute(criterion.id, expected)

        then:
        actual.title == expected.title
        actual.id == criterion.id

    }
}
