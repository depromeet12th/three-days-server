package com.depromeet.threedays.front.web.controller.docs

import com.depromeet.threedays.data.enums.HabitStatus
import com.depromeet.threedays.front.RestDocsSpecification
import com.depromeet.threedays.front.domain.model.habit.Habit
import com.depromeet.threedays.front.domain.model.notification.Notification
import com.depromeet.threedays.front.domain.usecase.habit.*
import com.depromeet.threedays.front.web.controller.HabitController
import com.depromeet.threedays.front.web.request.habit.SaveHabitRequest
import com.depromeet.threedays.front.web.request.habit.SearchHabitRequest
import com.depromeet.threedays.front.web.request.habit.UpdateHabitRequest
import com.depromeet.threedays.front.web.response.assembler.HabitAssembler
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

import java.time.DayOfWeek
import java.time.LocalTime

import static com.epages.restdocs.apispec.MockMvcRestDocumentationWrapper.document
import static com.epages.restdocs.apispec.ResourceDocumentation.parameterWithName
import static com.epages.restdocs.apispec.ResourceDocumentation.resource
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.*
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@AutoConfigureMockMvc(addFilters = false)
@WithMockUser(username = "0", roles = "USER")
@WebMvcTest(controllers = HabitController.class)
@ContextConfiguration(classes = HabitController.class)
class HabitControllerDocsSpec extends RestDocsSpecification {

    @Autowired
    ObjectMapper objectMapper

    @Autowired
    private MockMvc mockMvc

    @SpringBean
    SaveHabitUseCase saveUseCase = Stub() {
        execute(_ as SaveHabitRequest) >> new Habit()
    }

    @SpringBean
    GetHabitUseCase getUseCase = Stub() {
        execute(_ as Long) >> null
    }

    @SpringBean
    SearchHabitUseCase searchUseCase = Stub() {
        execute(_ as SearchHabitRequest) >> CustomSchema.habitOverviewList()
    }

    @SpringBean
    DeleteHabitUseCase deleteUseCase = Stub() {
        execute(_ as Long) >> null
    }

    @SpringBean
    UpdateHabitUseCase updateUseCase = Stub() {
        execute(_) >> new Habit()
    }

    @SpringBean
    HabitAssembler habitAssembler = Stub() {
        toHabitResponse(_ as Habit) >> CustomSchema.habitResponse()
    }

    private static final TAG = "Habit"

    def '습관 생성'() {
        given:
        def request = CustomSchema.saveHabitRequest()
        def content = objectMapper.writeValueAsString(request)

        expect:
        mockMvc.perform(post("/api/v1/habits")
                .content(content)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is2xxSuccessful())
                .andDo(
                        document("saveHabit",
                                resource(
                                        ResourceSnippetParameters.builder()
                                                .description("습관 생성시 사용되는 API")
                                                .tag(TAG)
                                                .requestSchema(Schema.schema("SaveHabitRequest"))
                                                .responseSchema(Schema.schema("HabitResponse"))
                                                .requestFields(Descriptor.saveHabitRequest())
                                                .responseFields(Descriptor.successResponse(Descriptor.habitResponse()))
                                                .build()
                                )
                        )
                )
    }

    def '습관 수정'() {
        given:
        def notification = Notification.builder()
                .notificationTime(LocalTime.now())
                .contents("알림").build()
        def request = UpdateHabitRequest.builder()
                .title("물물")
                .imojiPath("imoji")
                .color("red")
                .dayOfWeeks(EnumSet.of(DayOfWeek.MONDAY))
                .notification(notification)
                .build()
        def content = objectMapper.writeValueAsString(request)

        expect:
        mockMvc.perform(put("/api/v1/habits/{id}", 0)
                .content(content)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is2xxSuccessful())
                .andDo(
                        document("updateHabit",
                                resource(
                                        ResourceSnippetParameters.builder()
                                                .description("습관 수정시 사용되는 API")
                                                .tag(TAG)
                                                .pathParameters(
                                                        parameterWithName("id").description("습관 id")
                                                )
                                                .requestSchema(Schema.schema("UpdateHabitRequest"))
//                                                .responseSchema(Schema.schema("HabitResponse"))
                                                .requestFields(Descriptor.updateHabitRequest())
//                                                .responseFields(Descriptor.successResponse(Descriptor.habitResponse()))
                                                .build()
                                )
                        )
                )
    }

    def '습관 조회(browse)'() {
        given:
        def request = SearchHabitRequest.builder().status(HabitStatus.ACTIVE).build()
        def content = objectMapper.writeValueAsString(request)

        expect:
        mockMvc.perform(get("/api/v1/habits")
                .content(content)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is2xxSuccessful())
                .andDo(
                        document("searchHabit",
                                resource(
                                        ResourceSnippetParameters.builder()
                                                .description("습관 조회")
                                                .tag(TAG)
                                                .requestSchema(Schema.schema("SearchHabitRequest"))
//TODO field descriptor 수정
//                                                .responseSchema(Schema.schema("HabitResponse"))
                                                .requestFields(Descriptor.searchHabitRequest())
//                                                .responseFields(Descriptor.successResponse(Descriptor.habitOverview()))
                                                .build()
                                )
                        )
                )
    }


}