package com.depromeet.threedays.front.web.controller.docs

import com.depromeet.threedays.front.RestDocsSpecification
import com.depromeet.threedays.front.domain.model.client.Client
import com.depromeet.threedays.front.domain.usecase.client.SaveClientUseCase
import com.depromeet.threedays.front.web.controller.ClientController
import com.depromeet.threedays.front.web.request.client.ClientRequest
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
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post
import static org.springframework.restdocs.operation.preprocess.Preprocessors.*
import static org.springframework.restdocs.payload.PayloadDocumentation.*
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@AutoConfigureMockMvc(addFilters = false)
@WithMockUser(username = "0", roles = "USER")
@WebMvcTest(controllers = ClientController.class)
@ContextConfiguration(classes = ClientController.class)
class ClientControllerDocsSpec extends RestDocsSpecification {

    @Autowired
    private MockMvc mockMvc

    @SpringBean
    SaveClientUseCase saveClientUseCase = Stub() {
        execute(_ as ClientRequest) >> Client.builder()
                .memberId(1L)
                .id(1L)
                .identificationKey("identificationKey")
                .fcmToken('fcmToken').build()
    }

    def 'generate postClients'() {
        given:
        def request = new ClientRequest("fcmToken here", "identificationKey here")
        def content = new ObjectMapper().writeValueAsString(request)

        expect:
        mockMvc.perform(post("/api/v1/clients")
                .content(content)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is2xxSuccessful())
                .andDo(
                        document("postClients",
                                "save client",
                                "클라이언트 정보 추가시 사용하는 API. fcm-token 추가 등록/갱신 시 해당 API 사용",
                                false,
                                false,
                                preprocessRequest(prettyPrint()),
                                preprocessResponse(prettyPrint()),
                                requestFields(
                                        fieldWithPath('fcmToken').description('device fcmToken'),
                                        fieldWithPath("identificationKey").description("device identifier")
                                ),
                                responseFields(Descriptor.client()) & Descriptor.successResponse()
                        )
                )

    }

}