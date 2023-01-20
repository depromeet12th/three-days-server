package com.depromeet.threedays.front.domain.usecase.client

import com.depromeet.threedays.data.entity.client.ClientEntity
import com.depromeet.threedays.front.AsyncIntegrationTestSpecification
import com.depromeet.threedays.front.data.member.MemberInitializer
import com.depromeet.threedays.front.persistence.repository.client.ClientRepository
import org.springframework.beans.factory.annotation.Autowired
import spock.lang.Subject

import java.time.LocalDateTime

class DeleteClientUseCaseSpec extends AsyncIntegrationTestSpecification {

    @Subject
    @Autowired
    private DeleteClientUseCase useCase

    @Autowired
    private MemberInitializer memberInitializer

    @Autowired
    private ClientRepository repository

    def setup() {
        memberInitializer.initialize()
    }

    def "유효하지 않은 사용자 id에 해당하는 클라이언트 정보를 삭제하려할때 예외가 핸들링 된다."() {
        given:
        repository.save(
                ClientEntity
                        .builder()
                        .identificationKey("identificationKey")
                        .fcmToken("fcmToken")
                        .memberId(1L)
                        .createAt(LocalDateTime.now())
                        .updateAt(LocalDateTime.now())
                        .build()
        )

        when:
        def result = useCase.execute(2L)

        then:
        result == null
    }
}
