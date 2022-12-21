package com.depromeet.threedays.front.domain.usecase.mate

import com.depromeet.threedays.front.IntegrationTestSpecification
import com.depromeet.threedays.front.data.habit.HabitDataInitializer
import org.springframework.beans.factory.annotation.Autowired
import spock.lang.Subject

class GetMateCheckUseCaseSpec extends IntegrationTestSpecification {

    @Subject
    @Autowired
    private GetMateCheckUseCase getUseCase

    @Autowired
    private HabitDataInitializer habitDataInitializer

    def "사용자는 짝꿍이 있을 경우 짝꿍 정보를 볼 수 있다"() {
        given:
        habitDataInitializer.initialize()

        when:
        def actual = getUseCase.execute()

        then:
        actual.id == habitDataInitializer.associationData.id
        actual.title == habitDataInitializer.associationData.title
    }

    def "사용자는 짝꿍이 없을 경우 짝꿍 정보를 볼 수 없다"() {
        given:
        when:
        def actual = getUseCase.execute()
        then:
        actual == null
    }
}
