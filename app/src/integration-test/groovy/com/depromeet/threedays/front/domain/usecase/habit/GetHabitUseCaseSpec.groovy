package com.depromeet.threedays.front.domain.usecase.habit

import com.depromeet.threedays.data.entity.member.MemberEntity
import com.depromeet.threedays.data.enums.MemberStatus
import com.depromeet.threedays.front.IntegrationTestSpecification
import com.depromeet.threedays.front.data.habit.HabitDataInitializer
import com.depromeet.threedays.front.persistence.repository.member.MemberRepository
import org.mockito.Mockito
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.mock.mockito.MockBean
import spock.lang.Subject

class GetHabitUseCaseSpec extends IntegrationTestSpecification {

    @Subject
    @Autowired
    private GetHabitUseCase getUseCase

    @Autowired
    private HabitDataInitializer dataInitializer

    @MockBean
    private MemberRepository memberRepository

    def setup() {
        dataInitializer.initialize()
        Mockito.when(
                memberRepository.findByIdAndStatus(Mockito.any(Long.class), Mockito.any(MemberStatus.class))
        ).thenReturn(
                Optional.of(MemberEntity.builder().build())
        )
    }

    def "사용자는 자신의 습관 상세를 조회할 수 있다"() {
        given:
        def expected = dataInitializer.data.first()

        when:
        def actual = getUseCase.execute(expected.id)

        then:
        expected.id == actual.id
        expected.title == actual.title
    }

}
