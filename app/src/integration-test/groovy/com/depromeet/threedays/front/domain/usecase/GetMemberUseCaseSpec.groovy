package com.depromeet.threedays.front.domain.usecase

import com.depromeet.threedays.front.IntegrationTestSpecification
import com.depromeet.threedays.front.data.MemberDataInitializer
import com.depromeet.threedays.front.domain.usecase.member.GetMemberUseCase
import org.springframework.beans.factory.annotation.Autowired
import spock.lang.Subject

class GetMemberUseCaseSpec extends IntegrationTestSpecification{

    @Subject
    @Autowired
    private GetMemberUseCase getUseCase

    @Autowired
    private MemberDataInitializer dataInitializer

    def setup(){
        dataInitializer.initialize()
    }

    def "사용자의 아이디로 멤버 상세 정보를 조회할 수 있다"(){
        given:
        def expected = dataInitializer.data.first()
        def id = expected.id

        when:
        def actual = getUseCase.execute(id)

        then:
        actual != null

        actual.memberId == id
        actual.nickname == actual.nickname
    }
}
