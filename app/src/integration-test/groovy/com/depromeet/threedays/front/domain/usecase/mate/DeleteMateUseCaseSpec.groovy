package com.depromeet.threedays.front.domain.usecase.mate


import com.depromeet.threedays.front.IntegrationTestSpecification
import com.depromeet.threedays.front.data.habit.HabitDataInitializer
import com.depromeet.threedays.front.persistence.repository.mate.MateRepository
import org.springframework.beans.factory.annotation.Autowired
import spock.lang.Subject

class DeleteMateUseCaseSpec extends IntegrationTestSpecification {

    @Subject
    @Autowired
    private DeleteMateUseCase deleteUseCase

    @Autowired
    private MateRepository repository

    @Autowired
    private HabitDataInitializer habitDataInitializer

    def setup() {
        habitDataInitializer.initialize()
    }

    def "사용자는 짝꿍을 삭제할 수 있다"() {
        given:
        def criterionHabit = habitDataInitializer.data.first()

        when:
        deleteUseCase.execute(criterionHabit.id, habitDataInitializer.associationData.id)

        then:
        !repository.existsByMemberIdAndDeletedFalse(0L)
    }


}
