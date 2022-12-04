package com.depromeet.threedays.front.domain.usecase.mate

import com.depromeet.threedays.front.IntegrationTestSpecification
import com.depromeet.threedays.front.data.habit.HabitDataInitializer
import org.springframework.beans.factory.annotation.Autowired
import spock.lang.Subject

class GetMateUseCaseSpec extends IntegrationTestSpecification {

    @Subject
    @Autowired
    private GetMateUseCase getUseCase

    @Autowired
    private HabitDataInitializer habitDataInitializer

    def setup() {
        habitDataInitializer.initialize()
    }

    def "사용자는 짝꿍 상세정보를 조회할 수 있다"() {
        given:
        def criterionHabit = habitDataInitializer.data.first()

        when:
        def actual = getUseCase.execute(criterionHabit.id, habitDataInitializer.associationData.id)

        then:
        actual.id == habitDataInitializer.associationData.id
        actual.title == habitDataInitializer.associationData.title

    }
}
