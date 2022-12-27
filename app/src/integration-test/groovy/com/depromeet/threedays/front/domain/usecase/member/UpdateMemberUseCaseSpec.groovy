package com.depromeet.threedays.front.domain.usecase.member

import com.depromeet.threedays.data.enums.MemberStatus
import com.depromeet.threedays.front.IntegrationTestSpecification
import com.depromeet.threedays.front.data.member.MemberInitializer
import com.depromeet.threedays.front.persistence.repository.member.MemberRepository
import com.depromeet.threedays.front.web.request.member.MemberUpdateRequest
import org.json.simple.JSONObject
import org.springframework.beans.factory.annotation.Autowired


class UpdateMemberUseCaseSpec extends IntegrationTestSpecification {

    @Autowired
    MemberInitializer initializer
    MemberRepository mockRepo
    UpdateMemberUseCase useCase

    def setup() {
        initializer.initialize()
        mockRepo = Mock(MemberRepository.class)
        useCase = new UpdateMemberUseCase(mockRepo)
    }

    def "사용자는 name 데이터를 업데이트 할 수 있다"() {
        setup:
        def id = initializer.data[2]

        def json = new JSONObject()
        json.put("test", "test")
        def expected = MemberUpdateRequest.builder().resource(json).name("admin").notificationConsent(false).build()

        when:
        def actual = useCase.execute(expected)

        then:
        mockRepo.findByIdAndStatus(_ as Long, _ as MemberStatus) >> Optional.of(id)
        actual.resource.get("test") == json.get("test")
        actual.name == expected.name
        !actual.notificationConsent
    }
}
