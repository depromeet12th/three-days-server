package com.depromeet.threedays.front.domain.usecase.member

import com.depromeet.threedays.front.IntegrationTestSpecification
import com.depromeet.threedays.front.data.member.MemberDataInitializer
import com.depromeet.threedays.front.persistence.repository.member.MemberRepository
import com.depromeet.threedays.front.web.request.member.MemberNameUpdateRequest
import org.springframework.beans.factory.annotation.Autowired

class SaveMemberNameUseCaseSpec extends IntegrationTestSpecification {

    @Autowired
    MemberDataInitializer initializer
    MemberRepository mockRepo
    SaveNameUseCase useCase
    def setup() {
        mockRepo = Mock(MemberRepository.class)
        useCase = new SaveNameUseCase(mockRepo)
        initializer.initialize()
    }

    def "사용자는 이름을 새로 등록할 수 있다"() {
        setup:
        def id = initializer.data[2]
        def expected = MemberNameUpdateRequest.builder().name("admin").build()

        when:
        def actual = useCase.execute(expected)

        then:
        mockRepo.findById(_) >> Optional.of(id)
        actual.name == expected.name
    }
}
