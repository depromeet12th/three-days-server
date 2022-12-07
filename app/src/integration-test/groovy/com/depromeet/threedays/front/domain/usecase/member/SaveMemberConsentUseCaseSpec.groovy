package com.depromeet.threedays.front.domain.usecase.member

import com.depromeet.threedays.front.IntegrationTestSpecification
import com.depromeet.threedays.front.data.member.MemberInitializer
import com.depromeet.threedays.front.persistence.repository.member.MemberRepository
import com.depromeet.threedays.front.web.request.member.MemberNotificationConsentUpdateRequest
import org.springframework.beans.factory.annotation.Autowired

class SaveMemberConsentUseCaseSpec extends IntegrationTestSpecification {
    @Autowired
    MemberInitializer initializer
    MemberRepository mockRepo
    SaveConsentUseCase useCase

    def setup() {
        initializer.initialize()
        mockRepo = Mock(MemberRepository.class)
        useCase = new SaveConsentUseCase(mockRepo)
    }

    def "사용자는 알림 수신 정책을 수정할 수 있다"() {
        setup:
        def id = initializer.data[2]
        def expected = MemberNotificationConsentUpdateRequest.builder().isOn(false).build()

        when:
        def actual = useCase.execute(expected)

        then:
        mockRepo.findById(_ as Long) >> Optional.of(id)
        !actual.notificationConsent
    }
}
