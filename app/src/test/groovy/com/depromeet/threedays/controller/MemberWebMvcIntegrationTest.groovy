package com.depromeet.threedays.controller

import com.depromeet.threedays.controller.member.dto.response.MemberResponseDto
import com.depromeet.threedays.service.MemberService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.context.TestConfiguration
import org.springframework.context.annotation.Bean
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import spock.lang.Specification
import spock.mock.DetachedMockFactory

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@WebMvcTest
class MemberWebMvcIntegrationTest extends Specification {
    @Autowired
    MockMvc mockMvc

    @Autowired
    MemberService memberService

    String URL = "/api/v1/members"

    def "id로 member 검색하는 컨트롤러 테스트"() {
        given:
        def id = 1L
        def nickname = "test000"
        memberService.findUserById(_) >> new MemberResponseDto(id, nickname)

        expect:
        mockMvc.perform(MockMvcRequestBuilders.get(URL + "/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("\$.id").value(id))
                .andExpect(jsonPath("\$.nickname").value(nickname))
    }

    @TestConfiguration
    static class MockConfig {
        def detachedMockFactory = new DetachedMockFactory()

        @Bean
        MemberService memberService() {
            return detachedMockFactory.Stub(MemberService)
        }
    }
}
