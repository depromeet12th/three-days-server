package com.depromeet.threedays.front.domain.usecase.member

import com.depromeet.threedays.front.IntegrationTestSpecification
import com.depromeet.threedays.front.domain.usecase.client.SaveClientUseCase
import com.depromeet.threedays.front.persistence.repository.client.ClientRepository
import com.depromeet.threedays.front.web.request.client.ClientRequest
import org.springframework.beans.factory.annotation.Autowired
import spock.lang.Subject

//TODO: TestCode Refactor by jh
class SaveClientUseCaseSpec extends IntegrationTestSpecification {
    @Subject
    @Autowired
    private SaveClientUseCase saveUseCase

    @Autowired
    private ClientRepository repository

    def setup() {
        repository.deleteAll()
    }

    def "사용자는 기기의 identificationKey와 fcmToken을 등록할 수 있다"() {
        given:
        def expected = ClientRequest.builder()
                .fcmToken("fcmToken")
                .identificationKey("ikey")
                .build()

        when:
        saveUseCase.execute(expected)

        then:
        repository.findAll().size() > 0
    }

    def "사용자는 이미 등록된 기기의 fcmToken을 갱신할 수 있다"() {
        given:
        def request = ClientRequest.builder()
                .fcmToken("fcmToken")
                .identificationKey("ikey")
                .build()
        saveUseCase.execute(request)

        when:
        def newToken = "newToken"
        def actual = ClientRequest.builder()
                .fcmToken(newToken)
                .identificationKey("ikey")
                .build()
        saveUseCase.execute(actual)

        then:
        repository.findAll().get(0).getFcmToken() == newToken

    }


}
