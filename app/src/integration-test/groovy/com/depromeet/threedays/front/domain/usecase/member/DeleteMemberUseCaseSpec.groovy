package com.depromeet.threedays.front.domain.usecase.member

import com.depromeet.threedays.data.enums.MemberStatus
import com.depromeet.threedays.front.IntegrationTestSpecification
import com.depromeet.threedays.front.client.AuthClient
import com.depromeet.threedays.front.client.property.auth.AuthPropertyManager
import com.depromeet.threedays.front.data.habit.HabitDataInitializer
import com.depromeet.threedays.front.data.member.MemberInitializer
import com.depromeet.threedays.front.persistence.repository.member.MemberRepository
import org.springframework.beans.factory.annotation.Autowired

class DeleteMemberUseCaseSpec extends IntegrationTestSpecification {

    DeleteMemberUseCase useCase
    MemberRepository memberRepository
    AuthPropertyManager propertyManager
    AuthClient authClient

    @Autowired
    MemberInitializer initializer
    @Autowired
    HabitDataInitializer habitDataInitializer

    def setup() {
        initializer.initialize()
        habitDataInitializer.initialize()

        memberRepository = Mock(MemberRepository.class)
        propertyManager = Mock(AuthPropertyManager.class)
        authClient = Mock(AuthClient.class)
        useCase = new DeleteMemberUseCase(memberRepository, authClient, propertyManager)
    }

    def "사용자를 삭제하면 상태가 WITHDRAWN."() {
        setup:
        def criterionMember = initializer.data.first()
        habitDataInitializer.initialize()

        when:
        def actual = useCase.quit(criterionMember.id)

        then:
        memberRepository.findByIdAndStatus(_ as Long, _ as MemberStatus) >> Optional.of(criterionMember)
        actual.status == MemberStatus.WITHDRAWN
    }
}
