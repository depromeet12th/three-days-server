package com.depromeet.threedays.front.domain.usecase.mate

import com.depromeet.threedays.data.entity.member.MemberEntity
import com.depromeet.threedays.data.enums.MemberStatus
import com.depromeet.threedays.front.IntegrationTestSpecification
import com.depromeet.threedays.front.data.habit.HabitDataInitializer
import com.depromeet.threedays.front.persistence.repository.member.MemberRepository
import org.mockito.Mockito
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.mock.mockito.MockBean
import spock.lang.Subject

class GetMateUseCaseSpec extends IntegrationTestSpecification {

    @Subject
    @Autowired
    private GetMateUseCase getUseCase

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
