package com.depromeet.threedays.front.domain.usecase.member

import com.depromeet.threedays.front.IntegrationTestSpecification
import com.depromeet.threedays.front.config.security.filter.token.TokenResolver
import com.depromeet.threedays.front.data.member.MemberDataInitializer
import com.depromeet.threedays.front.domain.model.member.Token
import com.depromeet.threedays.front.domain.usecase.member.GetTokenUseCase
import com.depromeet.threedays.front.exception.PolicyViolationException
import com.depromeet.threedays.front.support.TokenGenerator
import org.springframework.beans.factory.annotation.Autowired
import spock.lang.Subject

class GetTokenUseCaseSpec extends IntegrationTestSpecification {
    @Subject
    @Autowired
    private GetTokenUseCase getUseCase

    @Autowired
    private TokenGenerator tokenGenerator

    @Autowired
    private TokenResolver tokenResolver

    @Autowired
    MemberDataInitializer initializer

    def setup() {
        initializer.initialize()
    }

    def "사용자는 refreshToken을 통해 token을 갱신할 수 있다."() {
        given:
        def expected = initializer.getData().stream().findFirst().get().id
        def req = Token.builder().accessToken(tokenGenerator.generateAccessToken(expected))
                .refreshToken(tokenGenerator.generateRefreshToken(expected))
                .build()

        when:
        def token = getUseCase.execute(req)

        then:
        expected == (tokenResolver.extractIdByToken(token.refreshToken))
    }

    def "클라이언트가 확인할 수 없는 사용자의 토큰을 제공한다."() {
        given:
        def expected = 10000L
        def req = Token.builder().accessToken(tokenGenerator.generateAccessToken(expected))
                .refreshToken(tokenGenerator.generateRefreshToken(expected))
                .build()

        when:
        getUseCase.execute(req)

        then:
        thrown(PolicyViolationException.class)

    }


}
