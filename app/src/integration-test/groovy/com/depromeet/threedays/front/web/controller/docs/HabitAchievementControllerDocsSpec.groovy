package com.depromeet.threedays.front.web.controller.docs

import com.depromeet.threedays.front.RestDocsSpecification
import com.depromeet.threedays.front.domain.model.habit.Habit
import com.depromeet.threedays.front.domain.usecase.habit.DeleteHabitAchievementUseCase
import com.depromeet.threedays.front.domain.usecase.habit.SaveHabitAchievementUseCase
import com.depromeet.threedays.front.domain.usecase.habit.SearchHabitAchievementUseCase
import com.depromeet.threedays.front.web.controller.HabitAchievementController
import com.depromeet.threedays.front.web.request.habit.SaveHabitAchievementRequest
import com.depromeet.threedays.front.web.request.habit.SearchHabitAchievementRequest
import com.depromeet.threedays.front.web.response.assembler.HabitAssembler
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

import java.time.LocalDate

import static com.epages.restdocs.apispec.MockMvcRestDocumentationWrapper.document
import static com.epages.restdocs.apispec.ResourceDocumentation.parameterWithName
import static com.epages.restdocs.apispec.ResourceDocumentation.resource
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.*
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@AutoConfigureMockMvc(addFilters = false)
@WithMockUser(username = "0", roles = "USER")
@WebMvcTest(controllers = HabitAchievementController.class)
@ContextConfiguration(classes = HabitAchievementController.class)
class HabitAchievementControllerDocsSpec extends RestDocsSpecification {

    @Autowired
    private ObjectMapper objectMapper

    @Autowired
    private MockMvc mockMvc

    @SpringBean
    SaveHabitAchievementUseCase saveUseCase = Stub() {
        execute(_ as Long, _ as SaveHabitAchievementRequest) >> CustomSchema.habit()
    }

    @SpringBean
    SearchHabitAchievementUseCase searchUseCase = Stub() {
        execute(_ as Long, _ as SearchHabitAchievementRequest) >> CustomSchema.habitAchievementList()
    }

    @SpringBean
    DeleteHabitAchievementUseCase deleteUseCase = Stub() {
        execute(_ as Long, _ as Long) >> CustomSchema.habit()
    }

    @SpringBean
    HabitAssembler habitAssembler = Stub() {
        toHabitResponse(_ as Habit) >> CustomSchema.habitResponse()
    }

    private static final TAG = "HabitAchievement"

    def '습관 달성'() {
        given:
        def request = CustomSchema.saveHabitAchievementRequest()
        def content = objectMapper.writeValueAsString(request)

        expect:
        mockMvc.perform(post("/api/v1/habits/{habitId}/achievements", 0)
                .content(content)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is2xxSuccessful())
                .andDo(document("saveHabitAchievement",
                        resource(
                                ResourceSnippetParameters.builder()
                                        .summary("습관 달성시 사용되는 API")
                                        .tag(TAG)
                                        .pathParameters(
                                                parameterWithName("habitId").description("습관 id").type(SimpleType.INTEGER)
                                        )
                                        .requestSchema(Schema.schema("SaveHabitAchievementRequest"))
                                        .responseSchema(Schema.schema("HabitResponse"))
                                        .requestFields(Descriptor.saveHabitAchievementRequest())
                                        .responseFields(Descriptor.successResponse(Descriptor.habitResponse()))
                                        .build()
                        )
                )
                )
    }

    def '습관 달성 조회(browse)'() {
        given:
        def request = CustomSchema.searchHabitAchievementRequest()
        def content = objectMapper.writeValueAsString(request)
        expect:
        mockMvc.perform(get("/api/v1/habits/{habitId}/achievements", 0)
                .param("from", LocalDate.now() as String)
                .param("to", LocalDate.now() as String)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is2xxSuccessful())
                .andDo(document("browseHabitAchievements",
                        resource(
                                ResourceSnippetParameters.builder()
                                        .summary("습관 다건 조회 API - 캘린더에서 보여주기 위한 일정 기간 동안의 습관 달성 데이터를 반환합니다.")
                                        .tag(TAG)
                                        .pathParameters(
                                                parameterWithName("habitId").description("습관 id").type(SimpleType.INTEGER),
                                        )
                                        .requestParameters(
                                                parameterWithName("from").description("습관 달성 조회 시작일"),
                                                parameterWithName("to").description("습관 달성 조회 마지막일")
                                        )
                                        .responseSchema(Schema.schema("HabitAchievement"))
                                        .responseFields(Descriptor.habitAchievementList())
                                        .build())))
    }

    def '습관 달성 삭제'() {
        given:
        expect:
        mockMvc.perform(delete("/api/v1/habits/{habitId}/achievements/{habitAchievementId}", 0, 1)
                .content()
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is2xxSuccessful())
                .andDo(document("deleteHabitAchievement",
                        resource(
                                ResourceSnippetParameters.builder()
                                        .summary("습관 달성 취소 API")
                                        .tag(TAG)
                                        .pathParameters(
                                                parameterWithName("habitId").description("습관 id").type(SimpleType.INTEGER),
                                                parameterWithName("habitAchievementId").description("습관 달성 id").type(SimpleType.INTEGER)
                                        )
                                        .responseSchema(Schema.schema("HabitResponse"))
                                        .responseFields(Descriptor.successResponse(Descriptor.habitResponse()))
                                        .build()
                        )))
    }
}
