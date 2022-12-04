package com.depromeet.threedays.front.domain.usecase.mate

import com.depromeet.threedays.data.enums.MateType
import com.depromeet.threedays.front.IntegrationTestSpecification
import com.depromeet.threedays.front.data.habit.HabitDataInitializer
import com.depromeet.threedays.front.domain.converter.habit.HabitConverter
import com.depromeet.threedays.front.domain.usecase.mate.SaveMateUseCase
import com.depromeet.threedays.front.exception.PolicyViolationException
import com.depromeet.threedays.front.persistence.repository.mate.MateRepository
import com.depromeet.threedays.front.web.request.mate.SaveMateRequest
import org.springframework.beans.factory.annotation.Autowired
import spock.lang.Subject

class SaveMateUseCaseSpec extends IntegrationTestSpecification {

    @Subject
    @Autowired
    private SaveMateUseCase saveUseCase

    @Autowired
    private MateRepository repository

    @Autowired
    private HabitDataInitializer dataInitializer

    def setup() {
        dataInitializer.initialize()
    }

    def "사용자는 습관에 짝꿍을 생성할 수 있다"() {
        given:
        def criterionHabit = HabitConverter.from(dataInitializer.data.first())
        def expected = SaveMateRequest.builder()
                .title("title")
                .characterType(MateType.CARROT)
                .build()
        repository.deleteAll()

        when:
        def actual = saveUseCase.execute(criterionHabit.id, expected)

        then:
        expected.title == actual.title
        expected.characterType == actual.characterType
    }

    def "사용자는 이미 짝꿍이 존재하는 경우 짝꿍을 생성할 수 없다"() {
        given:
        def criterionHabit = HabitConverter.from(dataInitializer.data.first())
        def expected = SaveMateRequest.builder()
                .title("title")
                .characterType(MateType.CARROT)
                .build()

        when:
        saveUseCase.execute(criterionHabit.id, expected)

        then:
        thrown(PolicyViolationException)
    }

}
