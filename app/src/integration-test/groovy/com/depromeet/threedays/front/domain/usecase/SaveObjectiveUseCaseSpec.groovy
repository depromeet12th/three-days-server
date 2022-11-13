package com.depromeet.threedays.front.domain.usecase

import com.depromeet.threedays.data.enums.DayOfWeek
import com.depromeet.threedays.front.IntegrationTestSpecification
import com.depromeet.threedays.front.controller.request.objective.SaveObjectiveRequest
import com.depromeet.threedays.front.domain.model.Notification
import com.depromeet.threedays.front.domain.usecase.objective.SaveObjectiveUseCase
import com.depromeet.threedays.front.exception.PolicyViolationException
import org.springframework.beans.factory.annotation.Autowired
import spock.lang.Subject

import java.time.LocalTime

class SaveObjectiveUseCaseSpec extends IntegrationTestSpecification {

    @Subject
    @Autowired
    private SaveObjectiveUseCase saveUseCase

    def setup() {
    }

    def "사용자는 정해진 명세에 맞춰 습관을 생성할 수 있다"() {
        given:
        def expected = SaveObjectiveRequest.builder()
                .title("title")
                .imojiPath("imoji")
                .color("color")
                .dayOfWeeks(EnumSet.of(DayOfWeek.MON, DayOfWeek.TUE, DayOfWeek.FRI))
                .notification(Notification.builder()
                        .contents("contents")
                        .notificationTime(LocalTime.now())
                        .build())
                .build()

        when:
        def acutal = saveUseCase.execute(expected)

        then:
        acutal != null

        acutal.objectiveId > 0L
    }

    def "사용자는 세 개 이하의 요일을 습관 수행일자로 선택했을 경우 습관을 생성할 수 없다."() {
        given:
        def expected = SaveObjectiveRequest.builder()
                .title("title")
                .imojiPath("imoji")
                .color("color")
                .dayOfWeeks(EnumSet.of(DayOfWeek.MON, DayOfWeek.TUE))
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
