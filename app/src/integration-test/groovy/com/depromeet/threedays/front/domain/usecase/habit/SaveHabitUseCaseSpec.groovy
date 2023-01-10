package com.depromeet.threedays.front.domain.usecase.habit

import com.depromeet.threedays.data.entity.member.MemberEntity
import com.depromeet.threedays.data.enums.MemberStatus
import com.depromeet.threedays.front.IntegrationTestSpecification
import com.depromeet.threedays.front.domain.model.notification.Notification
import com.depromeet.threedays.front.exception.PolicyViolationException
import com.depromeet.threedays.front.persistence.repository.member.MemberRepository
import com.depromeet.threedays.front.web.request.habit.SaveHabitRequest
import org.mockito.Mockito
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.mock.mockito.MockBean
import spock.lang.Subject

import java.time.DayOfWeek
import java.time.LocalTime

class SaveHabitUseCaseSpec extends IntegrationTestSpecification {

    @Subject
    @Autowired
    private SaveHabitUseCase saveUseCase

    @MockBean
    private MemberRepository memberRepository

    def setup() {
        Mockito.when(
                memberRepository.findByIdAndStatus(Mockito.any(Long.class), Mockito.any(MemberStatus.class))
        ).thenReturn(
                Optional.of(MemberEntity.builder().build())
        )
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
        def actual = saveUseCase.execute(expected)

        then:
        actual != null

        actual.id > 0L
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
