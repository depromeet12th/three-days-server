package com.depromeet.threedays.front.domain.usecase.member

import com.depromeet.threedays.data.enums.MemberStatus
import com.depromeet.threedays.front.IntegrationTestSpecification
import com.depromeet.threedays.front.data.member.MemberInitializer
import com.depromeet.threedays.front.persistence.repository.member.MemberRepository
import com.depromeet.threedays.front.web.request.member.MemberResourceUpdateRequest
import org.json.simple.JSONObject
import org.springframework.beans.factory.annotation.Autowired

class SaveMemberResourceUseCaseSpec extends IntegrationTestSpecification {

    @Autowired
    MemberInitializer initializer
    MemberRepository mockRepo
    SaveResourceUseCase useCase

    def setup() {
        initializer.initialize()
        mockRepo = Mock(MemberRepository.class)
        useCase = new SaveResourceUseCase(mockRepo)
    }

    def "사용자는 resource 값을 추가할 수 있다."() {
        setup:
        def id = initializer.data[2]

        def json = new JSONObject()
        json.put("test", "test")
        def expected = MemberResourceUpdateRequest.builder().resource(json).build()

        when:
        def actual = useCase.execute(expected)

        then:
        mockRepo.findByIdAndStatus(_ as Long, _ as MemberStatus) >> Optional.of(id)
        actual.resource.get("test") == json.get("test")
    }
}
