package com.depromeet.threedays.front.web.controller

import com.depromeet.threedays.front.IntegrationTestSpecification
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers

@SuppressWarnings('NonAsciiCharacters')
class HealthCheckControllerSpec extends IntegrationTestSpecification {

    @Autowired
    private MockMvc mockMvc

    def "health check 요청시 200 응답"() {
        given:

        when:
        def resultActions = mockMvc.perform(MockMvcRequestBuilders.get("/actuator/health"))

        then:
        resultActions
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("\$.status").value("UP"))

    }
}
