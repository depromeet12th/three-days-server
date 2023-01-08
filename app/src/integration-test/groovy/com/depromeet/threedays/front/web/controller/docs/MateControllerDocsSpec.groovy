package com.depromeet.threedays.front.web.controller.docs

import com.depromeet.threedays.data.enums.MateType
import com.depromeet.threedays.front.RestDocsSpecification
import com.depromeet.threedays.front.domain.usecase.mate.DeleteMateUseCase
import com.depromeet.threedays.front.domain.usecase.mate.GetMateUseCase
import com.depromeet.threedays.front.domain.usecase.mate.SaveMateUseCase
import com.depromeet.threedays.front.web.controller.MateController
import com.depromeet.threedays.front.web.request.mate.SaveMateRequest
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
import static com.epages.restdocs.apispec.ResourceDocumentation.parameterWithName
import static com.epages.restdocs.apispec.ResourceDocumentation.resource
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.*
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@AutoConfigureMockMvc(addFilters = false)
@WithMockUser(username = "0", roles = "USER")
@WebMvcTest(controllers = MateController.class)
@ContextConfiguration(classes = MateController.class)
class MateControllerDocsSpec extends RestDocsSpecification {

    @Autowired
    private MockMvc mockMvc

    @SpringBean
    SaveMateUseCase saveUseCase = Stub() {
        execute(_ as Long, _ as SaveMateRequest) >> CustomSchema.mate()
    }
    @SpringBean
    DeleteMateUseCase deleteUseCase = Stub() {
        execute() >> null
    }
    @SpringBean
    GetMateUseCase getUseCase = Stub() {
        execute(_ as Long, _ as Long) >> CustomSchema.mateResponse()
    }

    static final TAG = "Mate"

    def '짝꿍 추가'() {
        given:
        def request = SaveMateRequest.builder()
                .characterType(MateType.CARROT)
                .title("짝꿍이")
                .build()
        def content = new ObjectMapper().writeValueAsString(request)


        expect:
        mockMvc.perform(post("/api/v1/habits/{habitId}/mates", 0)
                .content(content)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is2xxSuccessful())
                .andDo(
                        document("saveMate",
                                resource(
                                        ResourceSnippetParameters.builder()
                                                .description("mate 추가")
                                                .tag(TAG)
                                                .pathParameters(
                                                        parameterWithName("habitId").description("연결된 습관 id"))
//
                                                .requestSchema(Schema.schema("SaveMateRequest"))
                                                .responseSchema(Schema.schema("Mate"))
                                                .requestFields(
                                                        fieldWithPath("title").description("data"),
                                                        fieldWithPath("characterType").type(JsonFieldType.STRING).description("code"),

                                                )
                                        //FIXME openapi3 failed
//                                        .responseFields(Descriptor.successResponse(Descriptor.mate()))
                                                .build()
                                )
                        )
                )
    }

    def '짝꿍 삭제'() {
        given:


        expect:
        mockMvc.perform(delete("/api/v1/habits/{habitId}/mates/{id}", 0, 0)
                .content()
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is2xxSuccessful())
                .andDo(
                        document("deleteMate",
                                resource(
                                        ResourceSnippetParameters.builder()
                                                .description("mate 삭제")
                                                .tag(TAG)
                                                .pathParameters(
                                                        parameterWithName("habitId").description("연결된 습관 id"),
                                                        parameterWithName("id").description("짝꿍 id")
                                                )
                                                .build()
                                )
                        )
                )
    }

    def '짝꿍 조회'() {
        given:

        expect:
        mockMvc.perform(get("/api/v1/habits/{habitId}/mates/{id}", 0, 0)
                .content()
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is2xxSuccessful())
                .andDo(
                        document("readMate",
                                resource(
                                        ResourceSnippetParameters.builder()
                                                .description("mate 조회")
                                                .tag(TAG)
                                                .pathParameters(
                                                        parameterWithName("habitId").description("연결된 습관 id"),
                                                        parameterWithName("id").description("짝꿍 id")
                                                )
                                                .build()
                                )
                        )
                )
    }


}