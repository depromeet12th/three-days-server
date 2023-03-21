package com.depromeet.threedays.front.web.controller.docs

import com.depromeet.threedays.front.RestDocsSpecification
import com.depromeet.threedays.front.domain.usecase.notification.SendGlobalNotificationUseCase
import com.depromeet.threedays.front.domain.usecase.notification.SendHabitNotificationUseCase
import com.depromeet.threedays.front.web.controller.NotificationController
import com.epages.restdocs.apispec.ResourceSnippetParameters
import com.epages.restdocs.apispec.Schema
import com.fasterxml.jackson.databind.ObjectMapper
import org.spockframework.spring.SpringBean
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.http.MediaType
import org.springframework.security.test.context.support.WithMockUser
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.web.servlet.MockMvc

import static com.epages.restdocs.apispec.MockMvcRestDocumentationWrapper.document
import static com.epages.restdocs.apispec.ResourceDocumentation.resource
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@AutoConfigureMockMvc(addFilters = false)
@WithMockUser(username = "0", roles = "USER")
@WebMvcTest(controllers = NotificationController.class)
@ContextConfiguration(classes = NotificationController.class)
class NotificationControllerDocsSpec extends RestDocsSpecification {

    @Autowired
    private ObjectMapper objectMapper

    @Autowired
    private MockMvc mockMvc

    @SpringBean
    SendGlobalNotificationUseCase sendGlobalNotificationUseCase = Stub() {
        execute() >> CustomSchema.notificationBatchResponseList()
    }

    @SpringBean
    SendHabitNotificationUseCase sendHabitNotificationUseCase = Stub() {
        execute() >> CustomSchema.batchResponseList()
    }
    private static final String TAG = "Notification"


    def '전체 알림 발송'() {
        given:
        expect:
        mockMvc.perform(post("/api/v1/notifications/global")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is2xxSuccessful())
                .andDo(document("globalNotification",
                        resource(
                                ResourceSnippetParameters.builder()
                                        .summary("전체 알림 발송 API")
                                        .tag(TAG)
                                        .responseSchema(Schema.schema("NotificationBatchResponse"))
                                        .responseFields(Descriptor.notificationBatchResponseList())
                                        .build()
                        )
                ))
    }

    def '습관 알림'() {
        given:
        expect:
        mockMvc.perform(post("/api/v1/notifications/habit")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is2xxSuccessful())
                .andDo(document("habitNotification",
                        resource(
                                ResourceSnippetParameters.builder()
                                        .summary("습관 알림 발송 API")
                                        .tag(TAG)
                                        .responseSchema(Schema.schema("BatchResponse"))
                                        .responseFields(Descriptor.batchResponseList())
                                        .build()
                        )
                )
                )
    }
}
