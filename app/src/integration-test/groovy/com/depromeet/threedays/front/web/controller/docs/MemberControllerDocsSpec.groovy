package com.depromeet.threedays.front.web.controller.docs

import com.depromeet.threedays.data.enums.CertificationSubject
import com.depromeet.threedays.data.enums.MemberStatus
import com.depromeet.threedays.front.RestDocsSpecification
import com.depromeet.threedays.front.client.model.KakaoReferrerType
import com.depromeet.threedays.front.domain.model.member.Member
import com.depromeet.threedays.front.domain.model.member.Token
import com.depromeet.threedays.front.domain.usecase.client.DeleteClientUseCase
import com.depromeet.threedays.front.domain.usecase.member.*
import com.depromeet.threedays.front.web.controller.MemberController
import com.depromeet.threedays.front.web.request.client.DeleteClientRequest
import com.depromeet.threedays.front.web.request.member.MemberNameUpdateRequest
import com.depromeet.threedays.front.web.request.member.MemberNotificationConsentUpdateRequest
import com.depromeet.threedays.front.web.request.member.MemberResourceUpdateRequest
import com.depromeet.threedays.front.web.request.member.SignMemberRequest
import com.epages.restdocs.apispec.ResourceSnippetParameters
import com.epages.restdocs.apispec.Schema
import com.fasterxml.jackson.databind.ObjectMapper
import net.sf.json.xml.JSONTypes
import org.json.simple.JSONObject
import org.spockframework.spring.SpringBean
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.http.MediaType
import org.springframework.security.test.context.support.WithMockUser
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.web.servlet.MockMvc
import org.springframework.http.*

import static com.epages.restdocs.apispec.MockMvcRestDocumentationWrapper.document
import static com.epages.restdocs.apispec.ResourceDocumentation.headerWithName
import static com.epages.restdocs.apispec.ResourceDocumentation.resource
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.*
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@AutoConfigureMockMvc(addFilters = false)
@WithMockUser(username = "0", roles = "USER")
@WebMvcTest(controllers = MemberController.class)
@ContextConfiguration(classes = MemberController.class)
class MemberControllerDocsSpec extends RestDocsSpecification {

    @Autowired
    private MockMvc mockMvc

    @SpringBean
    SignMemberUseCaseFacade signMemberUseCaseFacade = Stub() {
        execute(_ as SignMemberRequest) >> CustomSchema.saveMemberUseCaseResponse()
    }
    @SpringBean
    SaveNameUseCase saveNameUseCase = Stub() {
        execute(_) >> CustomSchema.memberNameNew()
    }
    @SpringBean
    SaveConsentUseCase saveConsentUseCase = Stub() {
        execute(_ as MemberNotificationConsentUpdateRequest) >> CustomSchema.memberConsentFalse()
    }
    @SpringBean
    SaveResourceUseCase saveResourceUseCase = Stub() {
        execute(_) >> Member.builder()
                .id(0L)
                .name("admin")
                .certificationSubject(CertificationSubject.KAKAO)
                .status(MemberStatus.REGULAR)
                .notificationConsent(true)
                .resource(new JSONObject())
                .build()
    }
    @SpringBean
    GetTokenUseCase getTokenUseCase = Stub() {
        execute(_) >> Token.builder()
                .accessToken("accessToken")
                .refreshToken("refreshToken")
                .build()
    }
    @SpringBean
    DeleteMemberUseCase deleteUseCase = Stub() {
        execute(_) >> null
    }
    @SpringBean
    DeleteMemberUseCase deleteUseCaseForKakaoUnlink = Stub() {
        executeCallback(_) >> null
    }

    @SpringBean
    DeleteClientUseCase deleteClientUseCase = Stub() {
        execute(_) >> null
    }
    @SpringBean
    GetMemberUseCase getUseCase = Stub() {
        execute() >> CustomSchema.memberConsentFalse()
    }


    static final TAG = "Member"

    def '회원가입/로그인'() {
        given:
        def request = new SignMemberRequest(CertificationSubject.KAKAO, "social token")
        def content = new ObjectMapper().writeValueAsString(request)
        expect:
        mockMvc.perform(post("/api/v1/members")
                .content(content)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is2xxSuccessful())
                .andDo(
                        document("saveMember",
                                resource(
                                        ResourceSnippetParameters.builder()
                                                .description("멤버 추가")
                                                .tag(TAG)
                                                .responseFields(
                                                        Descriptor.successResponse(Descriptor.saveMemberResponse())
                                                )
                                                .requestFields(Descriptor.signMemberRequest())
                                                .responseSchema(Schema.schema("Member"))
                                                .requestSchema(Schema.schema("signMemberRequest"))
                                                .build()
                                )
                        )
                )

    }

    def '닉네임변경'() {
        given:
        def request = new MemberNameUpdateRequest("new name")
        def content = new ObjectMapper().writeValueAsString(request)

        expect:
        mockMvc.perform(patch("/api/v1/members/name")
                .content(content)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is2xxSuccessful())
                .andDo(
                        document("patchName",
                                resource(
                                        ResourceSnippetParameters.builder()
                                                .description("닉네임 변경")
                                                .tag(TAG)
                                                .responseFields(
                                                        Descriptor.successResponse(Descriptor.member())
                                                )
                                                .requestFields(
                                                        fieldWithPath('name').type(JSONTypes.STRING).description('변경할 알림설정'),
                                                )
                                                .responseSchema(Schema.schema("Member"))
                                                .requestSchema(Schema.schema("MemberNameUpdateRequest"))
                                                .build()
                                )
                        )
                )
    }

    def '알림수신변경'() {
        given:
        def request = new MemberNotificationConsentUpdateRequest(false)
        def content = new ObjectMapper().writeValueAsString(request)

        expect:
        mockMvc.perform(patch("/api/v1/members/consents")
                .content(content)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is2xxSuccessful())
                .andDo(
                        document("patchConsents",
                                resource(
                                        ResourceSnippetParameters.builder()
                                                .description("알림설정 변경")
                                                .tag(TAG)
                                                .responseFields(
                                                        Descriptor.successResponse(Descriptor.member())
                                                )
                                                .requestFields(
                                                        fieldWithPath('notificationConsent').description('변경할 알림설정'),
                                                )
                                                .responseSchema(Schema.schema("Member"))
                                                .requestSchema(Schema.schema("MemberNotificationConsentUpdateRequest"))
                                                .build()
                                )
                        )
                )
    }

    def '사용자 정보 업데이트'() {
        given:
        def request = new MemberResourceUpdateRequest(new JSONObject())
        def content = new ObjectMapper().writeValueAsString(request)

        expect:
        mockMvc.perform(patch("/api/v1/members/resources")
                .content(content)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is2xxSuccessful())
                .andDo(
                        document("patchResources",
                                resource(
                                        ResourceSnippetParameters.builder()
                                                .description("사용자 정보 업데이트")
                                                .tag(TAG)
                                                .responseFields(
                                                        Descriptor.successResponse(Descriptor.member())
                                                )
                                                .requestFields(
                                                        fieldWithPath('resource').description('변경할 resource'),
                                                )
                                                .responseSchema(Schema.schema("Member"))
                                                .requestSchema(Schema.schema("MemberResourceUpdateRequest"))
                                                .build()
                                )
                        )
                )
    }

    def '토큰 발급'() {
        given:

        expect:
        mockMvc.perform(post("/api/v1/members/tokens")
                .header("X-THREE-DAYS-REFRESH-TOKEN", "refreshToken here")
                .content()
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is2xxSuccessful())
                .andDo(
                        document("get Tokens",
                                resource(
                                        ResourceSnippetParameters.builder()
                                                .description("토큰 갱신")
                                                .tag(TAG)
                                                .requestHeaders(headerWithName("X-THREE-DAYS-REFRESH-TOKEN").description("put refreshToken here"))
                                                .responseFields(
                                                        Descriptor.successResponse(Descriptor.token())
                                                )
                                                .responseSchema(Schema.schema("Token"))
                                                .build()
                                )
                        )
                )
    }

    def '회원 탈퇴'() {
        given:

        expect:
        mockMvc.perform(delete("/api/v1/members")
                .content()
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is2xxSuccessful())
                .andDo(
                        document("deleteMember",
                                resource(
                                        ResourceSnippetParameters.builder()
                                                .description("회원 탈퇴")
                                                .tag(TAG)
                                                .build()
                                )
                        )
                )
    }

    def '내 정보 조회'() {
        given:

        expect:
        mockMvc.perform(get("/api/v1/members/me")
                .content()
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is2xxSuccessful())
                .andDo(
                        document("GetMember",
                                resource(
                                        ResourceSnippetParameters.builder()
                                                .description("내 정보 조회")
                                                .tag(TAG)
                                                .responseFields(
                                                        Descriptor.successResponse(Descriptor.member())
                                                )
                                                .responseSchema(Schema.schema("Member"))
                                                .build()
                                )
                        )
                )
    }

    def '로그아웃'() {
        given:
        def request = new DeleteClientRequest("identificationKey")
        def content = new ObjectMapper().writeValueAsString(request)

        expect:
        mockMvc.perform(post("/api/v1/members/logout")
                .content(content)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is2xxSuccessful())
                .andDo(
                        document("logout",
                                resource(
                                        ResourceSnippetParameters.builder()
                                                .description("로그아웃")
                                                .tag(TAG)
                                                .requestFields(fieldWithPath('identificationKey').description('변경할 resource'))
                                                .requestSchema(Schema.schema("DeleteClientSchema"))
                                                .build()
                                )

                        )
                )
    }

}