package com.depromeet.threedays.front.domain.usecase.member

import com.depromeet.threedays.front.IntegrationTestSpecification
import com.depromeet.threedays.front.config.security.AuditorHolder
import com.depromeet.threedays.front.data.member.MemberDataInitializer
import com.depromeet.threedays.front.domain.usecase.member.SaveConsentUseCase
import com.depromeet.threedays.front.persistence.repository.member.MemberRepository
import org.springframework.beans.factory.annotation.Autowired
import spock.lang.Subject

class SaveMemberConsentUseCaseSpec extends IntegrationTestSpecification {
    @Subject
    @Autowired
    SaveConsentUseCase saveUseCase

    @Autowired
    MemberDataInitializer initializer

    @Autowired
    MemberRepository repository


    def setup() {
        initializer.initialize()
    }

    def "AuditorHolder Mock Test"() {
        given:
        def first = repository.findAll().stream().findFirst().get()
        AuditorHolder auditorHolder = GroovyMock(AuditorHolder) {
            get() >> first.id
        }

        when:
        def l = auditorHolder.get()

        then:
        l == first.id
    }

    def "사용자는 알림수신정책을 수정할 수 있다"() {
//        given:
//        def first = repository.findAll().stream().findFirst().get()
//        def expected = true
//        def request = UpdateNotificationConsentRequest.builder()
//                .isOn(expected)
//                .build()
//
//        when:
//        saveUseCase.execute(request)
//        then:
//        repository.findById(first.id).get().getNotificationConsent() == expected
    }
}
