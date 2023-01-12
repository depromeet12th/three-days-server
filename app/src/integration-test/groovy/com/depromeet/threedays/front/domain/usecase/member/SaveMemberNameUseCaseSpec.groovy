package com.depromeet.threedays.front.domain.usecase.member

import com.depromeet.threedays.data.enums.MemberStatus
import com.depromeet.threedays.front.IntegrationTestSpecification
import com.depromeet.threedays.front.data.member.MemberInitializer
import com.depromeet.threedays.front.persistence.repository.member.MemberRepository
import com.depromeet.threedays.front.web.request.member.MemberNameUpdateRequest
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.mock.mockito.MockBean

class SaveMemberNameUseCaseSpec extends IntegrationTestSpecification {

    @Autowired
    MemberInitializer initializer
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
        mockRepo.findByIdAndStatus(_ as Long, _ as MemberStatus) >> Optional.of(id)
        actual.name == expected.name
    }
}
