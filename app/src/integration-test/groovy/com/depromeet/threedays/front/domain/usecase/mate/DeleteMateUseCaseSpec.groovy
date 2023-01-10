package com.depromeet.threedays.front.domain.usecase.mate

import com.depromeet.threedays.data.entity.member.MemberEntity
import com.depromeet.threedays.data.enums.MateStatus
import com.depromeet.threedays.data.enums.MemberStatus
import com.depromeet.threedays.front.IntegrationTestSpecification
import com.depromeet.threedays.front.data.habit.HabitDataInitializer
import com.depromeet.threedays.front.persistence.repository.mate.MateRepository
import com.depromeet.threedays.front.persistence.repository.member.MemberRepository
import org.mockito.Mockito
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.mock.mockito.MockBean
import spock.lang.Subject

class DeleteMateUseCaseSpec extends IntegrationTestSpecification {

    @Subject
    @Autowired
    private DeleteMateUseCase deleteUseCase

    @Autowired
    private MateRepository repository

    @Autowired
    private HabitDataInitializer habitDataInitializer

    @MockBean
    private MemberRepository memberRepository

    def setup() {
        habitDataInitializer.initialize()
        Mockito.when(
                memberRepository.findByIdAndStatus(Mockito.any(Long.class), Mockito.any(MemberStatus.class))
        ).thenReturn(
                Optional.of(MemberEntity.builder().build())
        )
    }

    def "사용자는 짝꿍을 삭제할 수 있다"() {
        given:
        def criterionHabit = habitDataInitializer.data.first()

        when:
        deleteUseCase.execute(criterionHabit.id, habitDataInitializer.associationData.id)

        then:
        !repository.existsByMemberIdAndStatusAndDeletedFalse(0L, MateStatus.ACTIVE)
    }


}
