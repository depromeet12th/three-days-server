package com.depromeet.threedays.front.domain.usecase

import com.depromeet.threedays.front.IntegrationTestSpecification
import com.depromeet.threedays.front.data.member.MemberDataInitializer
import com.depromeet.threedays.front.domain.usecase.member.SaveNameUseCase
import com.depromeet.threedays.front.persistence.repository.member.MemberRepository
import org.springframework.beans.factory.annotation.Autowired
import spock.lang.Subject

class UpdateMemberUseCaseSpec extends IntegrationTestSpecification {
    @Subject
    @Autowired
    SaveNameUseCase updateUseCase

    @Autowired
    MemberDataInitializer initializer

    @Autowired
    MemberRepository repository

    def setup() {
        initializer.initialize()
    }

//    def "사용자는 이름을 수정할 수 있다"() {
//        given:
//        def expected = "name"
//        def request = UpdateNameRequest.builder()
//                .name(expected)
//                .build()
//        when:
//        updateUseCase.execute(request)
//        then:
//        repository.findById(0L).get().getName() == expected
//
//    }
}
