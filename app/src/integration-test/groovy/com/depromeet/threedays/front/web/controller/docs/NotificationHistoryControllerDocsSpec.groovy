package com.depromeet.threedays.front.web.controller.docs

import com.depromeet.threedays.data.enums.NotificationStatus
import com.depromeet.threedays.front.RestDocsSpecification
import com.depromeet.threedays.front.domain.usecase.notification.SaveNotificationUseCase
import com.depromeet.threedays.front.domain.usecase.notification.SearchNotificationUseCase
import com.depromeet.threedays.front.web.controller.NotificationHistoryController
import com.depromeet.threedays.front.web.request.notification.EditStatusNotificationRequest
import com.epages.restdocs.apispec.ResourceSnippetParameters
import com.epages.restdocs.apispec.Schema
import com.epages.restdocs.apispec.SimpleType
import com.fasterxml.jackson.databind.ObjectMapper
import org.spockframework.spring.SpringBean
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.http.MediaType
import org.springframework.security.test.context.support.WithMockUser
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.web.servlet.MockMvc

import static com.epages.restdocs.apispec.ResourceDocumentation.parameterWithName
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.*
import static com.epages.restdocs.apispec.MockMvcRestDocumentationWrapper.document
import static com.epages.restdocs.apispec.ResourceDocumentation.resource
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

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
    SearchNotificationUseCase searchUseCase = Stub() {
        execute() >> CustomSchema.notificationHistoryList()
    }

    @SpringBean
    SaveNotificationUseCase saveUseCase = Stub() {
        execute(_ as Long, _ as EditStatusNotificationRequest) >> CustomSchema.notificationHistory()
    }
    private static final TAG = "Notification"

    def '알림 이력 조회'() {
        given:
        expect:
        mockMvc.perform(get("/api/v1/notifications")
                .content()
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is2xxSuccessful())
                .andDo(document("browseNotificationHistory",
                        resource(
                                ResourceSnippetParameters.builder()
                                        .summary("알림 이력 조회 API")
                                        .tag(TAG)
                                        .responseSchema(Schema.schema("NotificationHistoryResponse"))
                                        .responseFields(Descriptor.notificationHistoryResponseList())
                                        .build()
                        )
                )
                )
    }

    def '알림 이력 상태 수정'() {
        given:
        def request = EditStatusNotificationRequest.builder().status(NotificationStatus.SUCCESS).build()
        def content = objectMapper.writeValueAsString(request)
        expect:
        mockMvc.perform(patch("/api/v1/notifications/{notificationHistoryId}",0)
        .content(content)
        .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().is2xxSuccessful())
        .andDo(document("patchNotificationHistoryStatus",
        resource(
                ResourceSnippetParameters.builder()
                .summary("알림 이력 상태 수정 API")
                .tag(TAG)
                .pathParameters(
                        parameterWithName("notificationHistoryId").description("알림 이력 id").type(SimpleType.INTEGER)
                )
                .requestSchema(Schema.schema("EditStatusNotificationRequest"))
                .responseSchema(Schema.schema("NotificationHistoryResponse"))
                .requestFields(Descriptor.editStatusNotificationRequest())
                .responseFields(Descriptor.successResponse(Descriptor.notificationHistoryResponse()))
                .build()
        )))
    }
}
