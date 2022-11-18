package com.depromeet.threedays.front.domain.usecase

import com.depromeet.threedays.front.IntegrationTestSpecification
import com.depromeet.threedays.front.controller.request.member.UpdateNameRequest
import com.depromeet.threedays.front.data.objectvie.MemberDataInitializer
import com.depromeet.threedays.front.domain.usecase.member.UpdateMemberUseCase
import com.depromeet.threedays.front.repository.member.MemberRepository
import org.springframework.beans.factory.annotation.Autowired
import spock.lang.Subject

class UpdateMemberUseCaseSpec extends IntegrationTestSpecification{
    @Subject
    @Autowired
    UpdateMemberUseCase updateUseCase;

    @Autowired
    MemberDataInitializer initializer

    @Autowired
    MemberRepository repository

    def setup(){
        initializer.initialize()
    }

    def "사용자는 이름을 수정할 수 있다"(){
        given:
        def expected = "name"
        def request = UpdateNameRequest.builder()
        .name(expected)
        .build()
        def userId = initializer.getData().iterator().next().getId()
        when:
        updateUseCase.execute(userId, request)

        then:
        repository.findById(userId).get().getName() == expected

    }
}
