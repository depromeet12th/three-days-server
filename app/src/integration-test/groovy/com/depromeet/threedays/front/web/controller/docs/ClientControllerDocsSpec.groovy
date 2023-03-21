package com.depromeet.threedays.front.web.controller.docs

import com.depromeet.threedays.front.RestDocsSpecification
import com.depromeet.threedays.front.domain.model.client.Client
import com.depromeet.threedays.front.domain.usecase.client.SaveClientUseCase
import com.depromeet.threedays.front.web.controller.ClientController
import com.depromeet.threedays.front.web.request.client.ClientRequest
import com.epages.restdocs.apispec.ResourceSnippetDetails
import com.epages.restdocs.apispec.ResourceSnippetParameters
import com.epages.restdocs.apispec.Schema
import com.fasterxml.jackson.databind.ObjectMapper
import org.spockframework.spring.SpringBean
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.http.MediaType
import org.springframework.restdocs.payload.JsonFieldType
import org.springframework.security.test.context.support.WithMockUser
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.web.servlet.MockMvc

import static com.epages.restdocs.apispec.MockMvcRestDocumentationWrapper.document
import static com.epages.restdocs.apispec.ResourceDocumentation.resource
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

    private static final TAG = "Client"

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
                                resource(
                                        ResourceSnippetParameters.builder()
                                                .description("클라이언트 정보 추가시 사용하는 API. fcm-token 추가 등록/갱신 시 해당 API 사용")
                                                .tag(TAG)
                                                .requestSchema(Schema.schema("SaveClientRequest"))
                                                .responseSchema(Schema.schema("Client"))
                                                .requestFields(
                                                        fieldWithPath("fcmToken").type(JsonFieldType.STRING).description("fcm token!!!!"),
                                                        fieldWithPath("identificationKey").type(JsonFieldType.STRING).description("identification key!!!"),
                                                )
                                                .responseFields(Descriptor.successResponse(Descriptor.client()))
                                                .build()
                                )
                        )
                )

    }

}