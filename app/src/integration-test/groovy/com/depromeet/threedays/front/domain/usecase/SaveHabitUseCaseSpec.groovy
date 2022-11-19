package com.depromeet.threedays.front.domain.usecase

import com.depromeet.threedays.front.IntegrationTestSpecification
import com.depromeet.threedays.front.domain.model.notification.Notification
import com.depromeet.threedays.front.domain.usecase.habit.SaveHabitUseCase
import com.depromeet.threedays.front.exception.PolicyViolationException
import com.depromeet.threedays.front.web.request.habit.SaveHabitRequest
import org.springframework.beans.factory.annotation.Autowired
import spock.lang.Subject

import java.time.DayOfWeek
import java.time.LocalTime

class SaveHabitUseCaseSpec extends IntegrationTestSpecification {

    @Subject
    @Autowired
    private SaveHabitUseCase saveUseCase

    def setup() {
    }

    def "사용자는 정해진 명세에 맞춰 습관을 생성할 수 있다"() {
        given:
        def expected = SaveHabitRequest.builder()
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
        def acutal = saveUseCase.execute(expected)

        then:
        acutal != null

        acutal.habitId > 0L
    }

    def "사용자는 세 개 이하의 요일을 습관 수행일자로 선택했을 경우 습관을 생성할 수 없다."() {
        given:
        def expected = SaveHabitRequest.builder()
                .title("title")
                .imojiPath("imoji")
                .color("color")
                .dayOfWeeks(EnumSet.of(DayOfWeek.MONDAY, DayOfWeek.TUESDAY))
                .notification(Notification.builder()
                        .contents("contents")
                        .notificationTime(LocalTime.now())
                        .build())
                .build()

        when:
        saveUseCase.execute(expected)

        then:
        thrown(PolicyViolationException.class)
    }
}
