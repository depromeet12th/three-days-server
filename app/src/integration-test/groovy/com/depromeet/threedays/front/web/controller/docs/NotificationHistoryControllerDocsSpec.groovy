package com.depromeet.threedays.front.web.controller.docs

import com.depromeet.threedays.front.RestDocsSpecification
import com.depromeet.threedays.front.domain.usecase.notification.SearchNotificationUseCase
import com.depromeet.threedays.front.web.controller.NotificationHistoryController
import com.fasterxml.jackson.databind.ObjectMapper
import org.spockframework.spring.SpringBean
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.security.test.context.support.WithMockUser
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.web.servlet.MockMvc

@AutoConfigureMockMvc(addFilters = false)
@WithMockUser(username = "0", roles = "USER")
@WebMvcTest(controllers = NotificationHistoryController.class)
@ContextConfiguration(classes = NotificationHistoryController.class)
class NotificationHistoryControllerDocsSpec extends RestDocsSpecification {
    @Autowired
    private ObjectMapper objectMapper

    @Autowired
    private MockMvc mockMvc

    @SpringBean
    SearchNotificationUseCase searchNotificationUseCase = Stub() {
        execute() >> CustomSchema.notificationHistoryList()
    }

//    def '알림 이력 조회'() {
//        given:
//        expect:
//        mockMvc.perform(get("/api/v1/notifications"))
//        .andExpect(status().is2xxSuccessful())
//    }
}
