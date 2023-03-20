package com.depromeet.threedays.front.web.controller.docs

import com.depromeet.threedays.front.RestDocsSpecification
import com.depromeet.threedays.front.domain.usecase.mate.GetMateCheckUseCase
import com.depromeet.threedays.front.web.controller.MateCheckController
import com.epages.restdocs.apispec.ResourceSnippetParameters
import com.epages.restdocs.apispec.Schema
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
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@AutoConfigureMockMvc(addFilters = false)
@WithMockUser(username = "0", roles = "USER")
@WebMvcTest(controllers = MateCheckController.class)
@ContextConfiguration(classes = MateCheckController.class)
class MateCheckControllerDocsSpec extends RestDocsSpecification {

    @Autowired
    private MockMvc mockMvc

    @SpringBean
    GetMateCheckUseCase getUseCase = Stub() {
        execute() >> CustomSchema.mateResponseList()
    }

    private final static TAG = "Mate"

    def '짝꿍 간편 조회'() {
        given:

        expect:
        mockMvc.perform(get("/api/v1/mates")
                .content()
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is2xxSuccessful())
                .andDo(
                        document("checkMate",
                                resource(
                                        ResourceSnippetParameters.builder()
                                                .description("모든 짝꿍 간편 조회")
                                                .tag(TAG)
                                                .responseSchema(Schema.schema("MateResponse"))
                                                .responseFields(Descriptor.mateResponseList())
                                                .build()
                                )
                        )
                )
    }

}