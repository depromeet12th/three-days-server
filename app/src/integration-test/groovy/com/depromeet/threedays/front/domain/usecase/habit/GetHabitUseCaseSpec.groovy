package com.depromeet.threedays.front.domain.usecase.habit

import com.depromeet.threedays.front.IntegrationTestSpecification
import com.depromeet.threedays.front.data.habit.HabitDataInitializer
import org.springframework.beans.factory.annotation.Autowired
import spock.lang.Subject

class GetHabitUseCaseSpec extends IntegrationTestSpecification {

    @Subject
    @Autowired
    private GetHabitUseCase getUseCase

    @Autowired
    private HabitDataInitializer dataInitializer

    def setup() {
        dataInitializer.initialize()
    }

    def "사용자는 자신의 습관 상세를 조회할 수 있다"() {
        given:
        def expected = dataInitializer.data.first()

        when:
        def actual = getUseCase.execute(expected.id)

        then:
        expected.id == actual.habitId
        expected.title == actual.title
    }

}
