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

class GetMateCheckUseCaseSpec extends IntegrationTestSpecification {

    @Subject
    @Autowired
    private GetMateCheckUseCase getUseCase

    @Autowired
    private HabitDataInitializer habitDataInitializer

    @MockBean
    private MemberRepository memberRepository

    def setup() {
        Mockito.when(
                memberRepository.findByIdAndStatus(Mockito.any(Long.class), Mockito.any(MemberStatus.class))
        ).thenReturn(
                Optional.of(MemberEntity.builder().build())
        )
    }

    def "사용자는 짝꿍이 있을 경우 짝꿍 정보를 볼 수 있다"() {
        given:
        habitDataInitializer.initialize()

        when:
        def actual = getUseCase.execute()

        then:
        actual.size() >= 1
        actual[0].id == habitDataInitializer.associationData.id
        actual[0].title == habitDataInitializer.associationData.title
    }

    def "사용자는 짝꿍이 없을 경우 짝꿍 정보를 볼 수 없다"() {
        given:
        when:
        def actual = getUseCase.execute()
        then:
        actual.size() == 0
    }
}
