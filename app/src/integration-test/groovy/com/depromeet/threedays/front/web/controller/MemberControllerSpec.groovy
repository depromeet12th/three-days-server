package com.depromeet.threedays.front.web.controller

import com.depromeet.threedays.data.entity.client.ClientEntity
import com.depromeet.threedays.front.AsyncIntegrationTestSpecification
import com.depromeet.threedays.front.config.security.filter.token.TokenGenerator
import com.depromeet.threedays.front.data.member.MemberInitializer
import com.depromeet.threedays.front.persistence.repository.client.ClientRepository
import com.depromeet.threedays.front.persistence.repository.member.MemberRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders

import java.time.LocalDateTime

class MemberControllerSpec extends AsyncIntegrationTestSpecification {

    @Autowired
    private MockMvc mockMvc

    @Autowired
    private TokenGenerator tokenGenerator

    @Autowired
    MemberInitializer initializer

    @Autowired
    ClientRepository clientRepository

    @Autowired
    MemberRepository repository

    private String token;

    def setup() {
        initializer.initialize()
    }

    def "사용자 탈퇴 로직이 비동기적으로 수행되는지 테스트."() {
        given:
        def id = initializer.data.first().id
        token = tokenGenerator.generateToken(id).getAccessToken()

        clientRepository.save(
                ClientEntity
                        .builder()
                        .identificationKey("identificationKey")
                        .fcmToken("fcmToken")
                        .memberId(id)
                        .createAt(LocalDateTime.now())
                        .updateAt(LocalDateTime.now())
                        .build()
        )

        when:
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/v1/members")
                    .header("Authorization", "Bearer " + token))
        then:
        print("success")
    }
}
