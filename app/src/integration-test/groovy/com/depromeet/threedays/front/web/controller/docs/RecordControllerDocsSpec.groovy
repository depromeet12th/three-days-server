package com.depromeet.threedays.front.web.controller.docs

import com.depromeet.threedays.front.RestDocsSpecification
import com.depromeet.threedays.front.domain.model.DatePeriod
import com.depromeet.threedays.front.domain.usecase.SearchRecordUseCase
import com.depromeet.threedays.front.web.controller.RecordController
import com.depromeet.threedays.front.web.request.SearchRecordRequest
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
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@AutoConfigureMockMvc(addFilters = false)
@WithMockUser(username = "0", roles = "USER")
@WebMvcTest(controllers = RecordController.class)
@ContextConfiguration(classes = RecordController.class)
class RecordControllerDocsSpec extends RestDocsSpecification {

    @Autowired
    private MockMvc mockMvc

    @SpringBean
    SearchRecordUseCase searchRecordUseCase = Stub() {
        execute(_ as SearchRecordRequest) >> CustomSchema.recordResponse()
    }

    static final TAG = "Record"

    def '전체 기록 조회'() {
        given:
        def request = new SearchRecordRequest(new DatePeriod())
        def content = new ObjectMapper().writeValueAsString(request)

        expect:
        mockMvc.perform(get("/api/v1/records")
                .content(content)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is2xxSuccessful())
                .andDo(
                        document("readRecords",
                                resource(
                                        ResourceSnippetParameters.builder()
                                                .summary("전체 기록 조회 API")
                                                .tag(TAG)
                                                .responseSchema(Schema.schema("RecordResponse"))
                                                .responseFields(Descriptor.successResponse(Descriptor.recordResponse()))
                                                .build()
                                )
                        )
                )
    }


}