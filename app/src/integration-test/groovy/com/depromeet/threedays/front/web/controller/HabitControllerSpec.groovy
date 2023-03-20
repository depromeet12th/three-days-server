package com.depromeet.threedays.front.web.controller

import com.depromeet.threedays.front.IntegrationTestSpecification
import com.depromeet.threedays.front.support.TokenGenerator
import com.depromeet.threedays.front.web.request.member.MemberNameUpdateRequest
import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers

class HabitControllerSpec extends IntegrationTestSpecification {

    @Autowired
    private MockMvc mockMvc

    @Autowired
    private ObjectMapper objectMapper

    @Autowired
    private TokenGenerator tokenGenerator

    private String BAD_REQUEST_MESSAGE = "알 수 없는 오류가 발생했어요."
    private String token
    def setup() {
        token = tokenGenerator.generateAccessToken(1L)
    }

    def "HttpMediaTypeNotSupportedException 이 핸들링 되는지 테스트"() {
        given:
        when:
        def resultActions = mockMvc.perform(
                MockMvcRequestBuilders.post("/api/v1/habits")
                        .header("Authorization", "Bearer " + token)
                        .contentType(MediaType.TEXT_PLAIN)
        )
        then:
        resultActions
                .andExpect(MockMvcResultMatchers.status().is4xxClientError())
                .andExpect(MockMvcResultMatchers.jsonPath("\$.message").value(BAD_REQUEST_MESSAGE))
    }

    def "MethodArgumentNotValidException 이 핸들링 되는지 테스트"() {
        given:
        def content = objectMapper.writeValueAsString(new MemberNameUpdateRequest("saint6839"))

        when:
        def resultActions = mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/habits")
                .header("Authorization", "Bearer " + token)
                .content(content)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
        then:
        resultActions.andExpect(MockMvcResultMatchers.status().is4xxClientError())
                .andExpect(MockMvcResultMatchers.jsonPath("\$.message").value(BAD_REQUEST_MESSAGE))
    }

    def "HttpMessageNotReadableException 이 핸들링 되는지 테스트"() {
        given:
        when:
        def resultActions = mockMvc.perform(MockMvcRequestBuilders.put("/api/v1/habits/1")
                .header("Authorization", "Bearer " + token)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))

        then:
        resultActions.andExpect(MockMvcResultMatchers.status().is4xxClientError())
                .andExpect(MockMvcResultMatchers.jsonPath("\$.message").value(BAD_REQUEST_MESSAGE))

    }

    def "HttpRequestMethodNotSupportedException 이 핸들링 되는지 테스트"() {
        given:
        when:
        def resultActions = mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/habits/1")
                .header("Authorization", "Bearer " + token)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))

        then:
        resultActions.andExpect(MockMvcResultMatchers.status().is4xxClientError())
                .andExpect(MockMvcResultMatchers.jsonPath("\$.message").value(BAD_REQUEST_MESSAGE))

    }
}
