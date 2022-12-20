package com.depromeet.threedays.front.domain.usecase.habit

import com.depromeet.threedays.front.IntegrationTestSpecification
import com.depromeet.threedays.front.data.habit.HabitDataInitializer
import com.depromeet.threedays.front.data.habit.HabitNotificationDataInitializer
import com.depromeet.threedays.front.domain.model.notification.Notification
import com.depromeet.threedays.front.exception.PolicyViolationException
import com.depromeet.threedays.front.persistence.repository.habit.HabitRepository
import com.depromeet.threedays.front.persistence.repository.notification.HabitNotificationRepository
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

    @Autowired
    private HabitNotificationDataInitializer notificationDataInitializer

    @Autowired
    private HabitRepository repository

    @Autowired
    private HabitNotificationRepository notificationRepository

    def setup() {
        dataInitializer.initialize()
    }

    def "사용자는 정해진 명세에 맞춰 습관 정보를 갱신할 수 있다"() {
        given:
        def criterion = dataInitializer.data[2]

        def notification = Notification.builder()
                .contents("contents")
                .notificationTime(LocalTime.now())
                .build()

        def expected = UpdateHabitRequest.builder()
                .title("title")
                .imojiPath("imoji")
                .color("color")
                .dayOfWeeks(EnumSet.of(DayOfWeek.MONDAY, DayOfWeek.TUESDAY, DayOfWeek.FRIDAY))
                .notification(notification)
                .build()

        when:
        def actual = updateUseCase.execute(criterion.id, expected)

        then:
        actual.title == expected.title
        actual.id == criterion.id
        actual.notification.notificationTime == expected.notification.notificationTime
        actual.notification.contents == expected.notification.contents
    }

    def "사용자는 기존 습관 알림이 없고, 알림을 설정하지 않았을때 습관 정보를 갱신할 수 있다."() {
        given:
        def criterion = dataInitializer.data[2]

        def expected = UpdateHabitRequest.builder()
                .title("title")
                .imojiPath("imoji")
                .color("color")
                .dayOfWeeks(EnumSet.of(DayOfWeek.MONDAY, DayOfWeek.TUESDAY, DayOfWeek.FRIDAY))
                .notification(null)
                .build()

        when:
        def actual = updateUseCase.execute(criterion.id, expected)

        then:
        actual.notification == null
    }

    def "사용자는 기존 습관 알림이 없고, 알림시간을 수정했을때 습관 정보를 갱신할 수 있다."() {
        given:
        def criterion = dataInitializer.data[2]

        def notification = Notification.builder()
                .contents("contents")
                .notificationTime(LocalTime.now())
                .build()

        def expected = UpdateHabitRequest.builder().title("title")
                .imojiPath("imoji")
                .color("color")
                .dayOfWeeks(EnumSet.of(DayOfWeek.MONDAY, DayOfWeek.TUESDAY, DayOfWeek.FRIDAY))
                .notification(notification)
                .build()
        when:
        def actual = updateUseCase.execute(criterion.id, expected)

        then:
        actual.notification.notificationTime == expected.notification.notificationTime
        actual.notification.contents == expected.notification.contents
    }

    def "사용자는 기존 습관 알림이 있고, 알림을 수정했을때 습관 정보를 갱신할 수 있다."() {
        given:
        def criterion = dataInitializer.data[2]
        notificationDataInitializer.initialize(criterion.id, criterion.memberId)
        notificationRepository.save(notificationDataInitializer.data[1])

        def notification = Notification.builder()
                .contents("contents")
                .notificationTime(LocalTime.now())
                .build()

        def expected = UpdateHabitRequest.builder()
                .title("title")
                .imojiPath("imoji")
                .color("color")
                .dayOfWeeks(EnumSet.of(DayOfWeek.MONDAY, DayOfWeek.TUESDAY, DayOfWeek.FRIDAY))
                .notification(notification)
                .build()
        when:
        def actual = updateUseCase.execute(criterion.id, expected)
        then:
        actual.title == expected.title
        actual.id == criterion.id
        actual.notification.notificationTime == expected.notification.notificationTime
    }

    def "사용자의 기존 습관 알림이 있고, 알림을 수정하지 않았을때 습관 정보를 갱신할 수 있다."() {
        given:
        def criterion = dataInitializer.data[2]
        notificationDataInitializer.initialize(criterion.id, criterion.memberId)
        notificationRepository.save(notificationDataInitializer.data[1])

        def expected = UpdateHabitRequest.builder()
                .title("title")
                .imojiPath("imoji")
                .color("color")
                .dayOfWeeks(EnumSet.of(DayOfWeek.MONDAY, DayOfWeek.TUESDAY, DayOfWeek.FRIDAY))
                .notification(null)
                .build()
        when:
        def actual = updateUseCase.execute(criterion.id, expected)
        then:
        actual.notification == null
    }

    def "사용자는 기존 습관 알림이 여러 요일에 등록되어 있고, 요일을 수정했을때 습관 정보를 갱신할 수 있다."() {
        given:
        def criterion = dataInitializer.data[2]
        notificationDataInitializer.initialize(criterion.id, criterion.memberId)
        for (int i = 1; i <= 5; i++) {
            notificationRepository.save(notificationDataInitializer.data[i])
        }

        EnumSet<DayOfWeek> dayOfWeeks = EnumSet.of(DayOfWeek.MONDAY, DayOfWeek.TUESDAY, DayOfWeek.FRIDAY)

        def notification = Notification.builder()
                .contents("contents")
                .notificationTime(LocalTime.now())
                .build()

        def expected = UpdateHabitRequest.builder()
                .title("title")
                .imojiPath("imoji")
                .color("color")
                .dayOfWeeks(dayOfWeeks)
                .notification(notification)
                .build()
        when:
        def actual = updateUseCase.execute(criterion.id, expected)
        then:
        actual.dayOfWeeks == dayOfWeeks
    }

    def "사용자는 세 개 이하의 요일로 습관 수행 요일을 수정했을때 습관 정보를 갱신할 수 없다."() {
        given:
        def criterion = dataInitializer.data[2]

        def expected = UpdateHabitRequest.builder()
                .title("title")
                .imojiPath("imoji")
                .color("color")
                .dayOfWeeks(EnumSet.of(DayOfWeek.MONDAY, DayOfWeek.TUESDAY))
                .notification(null)
                .build()
        when:
        updateUseCase.execute(criterion.id, expected)

        then:
        thrown(PolicyViolationException.class)
    }
}
