package com.depromeet.threedays.front.web.controller.docs

import org.apache.commons.lang.ArrayUtils
import org.springframework.restdocs.payload.FieldDescriptor
import org.springframework.restdocs.payload.JsonFieldType
import org.springframework.restdocs.request.ParameterDescriptor

import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath

class Descriptor {

    /*ApiResponse.SuccessBody*/

    static FieldDescriptor[] successResponse() {
        return new FieldDescriptor[]{
                fieldWithPath("data").type(JsonFieldType.OBJECT).description("data"),
                fieldWithPath("code").type(JsonFieldType.STRING).description("code"),
                fieldWithPath("message").type(JsonFieldType.STRING).description("message"),
        }
    }

    /*SuccessBody에 Data 세팅*/

    static FieldDescriptor[] successResponse(FieldDescriptor[] data) {
        return ArrayUtils.addAll(successResponse(), data) as FieldDescriptor[]
    }

    static FieldDescriptor[] client() {
        return new FieldDescriptor[]{
                fieldWithPath("data.id").type(JsonFieldType.NUMBER).description("client id(Long)"),
                fieldWithPath("data.memberId").type(JsonFieldType.NUMBER).description("member id(Long)"),
                fieldWithPath("data.fcmToken").type(JsonFieldType.STRING).description("firebase fcm token"),
                fieldWithPath("data.identificationKey").type(JsonFieldType.STRING).description("device identification key")
        }
    }

    static FieldDescriptor[] saveHabitRequest() {
        return new FieldDescriptor[]{
                fieldWithPath("title").type(JsonFieldType.STRING).description("습관 제목"),
                fieldWithPath("imojiPath").type(JsonFieldType.STRING).description("이모지"),
                fieldWithPath("dayOfWeeks").type(JsonFieldType.ARRAY).description("수행요일 목록"),
                fieldWithPath("color").type(JsonFieldType.STRING).description("습관 컬러"),
                fieldWithPath("notification").type(JsonFieldType.OBJECT).description("알림").optional(),
                fieldWithPath("notification.notificationTime").type(JsonFieldType.STRING).description("알림 시간").optional(),
                fieldWithPath("notification.contents").type(JsonFieldType.STRING).description("내용").optional()

        }
    }

    static FieldDescriptor[] notification() {
        return new FieldDescriptor[]{
                fieldWithPath("data.notification").type(JsonFieldType.OBJECT).description("알림").optional(),
                fieldWithPath("data.notification.notificationTime").type(JsonFieldType.STRING).description("알림 시간").optional(),
                fieldWithPath("data.notification.contents").type(JsonFieldType.STRING).description("내용").optional()
        }
    }

    static FieldDescriptor[] habit() {
        return new FieldDescriptor[]{
                fieldWithPath("data.id").type(JsonFieldType.STRING).description("습관 id"),
                fieldWithPath("data.memberId").type(JsonFieldType.STRING).description("사용자 id"),
                fieldWithPath("data.title").type(JsonFieldType.STRING).description("습관 제목"),
                fieldWithPath("data.imojiPath").type(JsonFieldType.STRING).description("이모지"),
                fieldWithPath("data.color").type(JsonFieldType.STRING).description("습관 컬러"),
                fieldWithPath("data.dayOfWeeks").type(JsonFieldType.ARRAY).description("수행요일 목록"),
                fieldWithPath("data.reward").type(JsonFieldType.STRING).description("박수 갯수"),
                fieldWithPath("data.sequence").type(JsonFieldType.STRING).description("연속 횟수"),
                fieldWithPath("data.status").type(JsonFieldType.STRING).description("습관 상태"),
                fieldWithPath("data.totalAchievementCount").type(JsonFieldType.STRING).description("총 달성 횟수"),
                fieldWithPath("data.todayHabitAchievementId").type(JsonFieldType.STRING).description("오늘 습관 달성 id"),
                mateResponse(),
                notification(),
                fieldWithPath("data.createAt").type(JsonFieldType.STRING).description("습관 생성일"),
                fieldWithPath("data.archiveAt").type(JsonFieldType.STRING).description("습관 수정일"),
        }
    }

    static FieldDescriptor[] mateResponse() {
        return new FieldDescriptor[]{
                fieldWithPath("data.id").type(JsonFieldType.NUMBER).description("짝꿍 id"),
                fieldWithPath("data.habitId").type(JsonFieldType.NUMBER).description("습관 id"),
                fieldWithPath("data.memberId").type(JsonFieldType.NUMBER).description("사용자 id"),
                fieldWithPath("data.title").type(JsonFieldType.STRING).description("짝꿍 이름"),
                fieldWithPath("data.createAt").type(JsonFieldType.STRING).description("짝꿍 생성일"),
                fieldWithPath("data.level").type(JsonFieldType.NUMBER).description("짝꿍 생성일"),
                fieldWithPath("data.reward").type(JsonFieldType.NUMBER).description("짝꿍 박수 갯수"),
                fieldWithPath("data.rewardHistory[]").type(JsonFieldType.ARRAY).description("짝꿍 박수 이력"),
                fieldWithPath("data.rewardHistory[].createAt").type(JsonFieldType.STRING).description("짝꿍 박수 이력 생성일"),
                fieldWithPath("data.levelUpAt").type(JsonFieldType.STRING).description("짝꿍 레벨업 시점"),
                fieldWithPath("data.characterType").type(JsonFieldType.STRING).description("짝꿍 타입"),
                fieldWithPath("data.levelUpSection[]").type(JsonFieldType.ARRAY).description("짝꿍 레벨업 섹션"),
                fieldWithPath("data.bubble").type(JsonFieldType.STRING).description("짝꿍 말풍선"),
                fieldWithPath("data.status").type(JsonFieldType.STRING).description("짝꿍 상태"),
        }
    }

    static FieldDescriptor[] mate() {
        return new FieldDescriptor[]{
                fieldWithPath("data.id").type(JsonFieldType.NUMBER).description("짝꿍 id"),
                fieldWithPath("data.habitId").type(JsonFieldType.NUMBER).description("습관 id"),
                fieldWithPath("data.memberId").type(JsonFieldType.NUMBER).description("사용자 id"),
                fieldWithPath("data.title").type(JsonFieldType.STRING).description("짝꿍 이름"),
                fieldWithPath("data.createAt").type(JsonFieldType.STRING).description("짝꿍 생성일"),
                fieldWithPath("data.level").type(JsonFieldType.NUMBER).description("짝꿍 레벨"),
                fieldWithPath("data.reward").type(JsonFieldType.NUMBER).description("짝꿍 박수 개수"),
                fieldWithPath("data.rewardHistory[]").type(JsonFieldType.ARRAY).description("짝꿍 박수 이력"),
                fieldWithPath("data.rewardHistory[].createAt").type(JsonFieldType.STRING).description("짝꿍 박수 이력 생성일"),
                fieldWithPath("data.levelUpAt").type(JsonFieldType.STRING).description("짝꿍 레벨업 시점"),
                fieldWithPath("data.characterType").type(JsonFieldType.STRING).description("짝꿍 타입"),
                fieldWithPath("data.levelUpSection").type(JsonFieldType.ARRAY).description("짝꿍 레벨업 섹션"),
                fieldWithPath("data.bubble").type(JsonFieldType.STRING).description("짝꿍 말풍선"),
                fieldWithPath("data.status").type(JsonFieldType.STRING).description("짝꿍 상태"),
                fieldWithPath("data.deleted").type(JsonFieldType.BOOLEAN).description("짝꿍 삭제여부"),

        }
    }

    static FieldDescriptor[] mateResponseList() {
        return new FieldDescriptor[]{
                fieldWithPath("code").type(JsonFieldType.STRING).description("code"),
                fieldWithPath("message").description("message"),
                fieldWithPath("data[]").type(JsonFieldType.ARRAY).description("짝꿍 리스트"),
                fieldWithPath("data[].id").type(JsonFieldType.NUMBER).description("짝꿍 id"),
                fieldWithPath("data[].habitId").type(JsonFieldType.NUMBER).description("습관 id"),
                fieldWithPath("data[].memberId").type(JsonFieldType.NUMBER).description("사용자 id"),
                fieldWithPath("data[].title").type(JsonFieldType.STRING).description("짝꿍 이름"),
                fieldWithPath("data[].createAt").type(JsonFieldType.STRING).description("짝꿍 생성일"),
                fieldWithPath("data[].level").type(JsonFieldType.NUMBER).description("짝꿍 레벨"),
                fieldWithPath("data[].reward").type(JsonFieldType.NUMBER).description("짝꿍 박수 갯수"),
                fieldWithPath("data[].rewardHistory[]").type(JsonFieldType.ARRAY).description("짝꿍 박수 이력"),
                fieldWithPath("data[].rewardHistory[].createAt").type(JsonFieldType.STRING).description("짝꿍 박수 이력 생성일"),
                fieldWithPath("data[].levelUpAt").type(JsonFieldType.STRING).description("짝꿍 레벨업 일자"),
                fieldWithPath("data[].characterType").type(JsonFieldType.STRING).description("짝꿍 타입"),
                fieldWithPath("data[].levelUpSection").type(JsonFieldType.ARRAY).description("짝꿍 레벨업 섹션"),
                fieldWithPath("data[].bubble").type(JsonFieldType.STRING).description("짝꿍 말풍선"),
                fieldWithPath("data[].status").type(JsonFieldType.STRING).description("짝꿍 상태"),
        }
    }

    static FieldDescriptor[] habitResponse() {
        def arr = new FieldDescriptor[]{
                fieldWithPath("data.id").type(JsonFieldType.NUMBER).description("습관 id"),
                fieldWithPath("data.memberId").type(JsonFieldType.NUMBER).description("사용자 id"),
                fieldWithPath("data.title").type(JsonFieldType.STRING).description("습관 제목"),
                fieldWithPath("data.imojiPath").type(JsonFieldType.STRING).description("이모지"),
                fieldWithPath("data.color").type(JsonFieldType.STRING).description("습관 컬러"),
                fieldWithPath("data.dayOfWeeks").type(JsonFieldType.ARRAY).description("수행요일 목록"),
                fieldWithPath("data.reward").type(JsonFieldType.NUMBER).description("박수 갯수"),
                fieldWithPath("data.sequence").type(JsonFieldType.NUMBER).description("연속 횟수"),
                fieldWithPath("data.status").type(JsonFieldType.STRING).description("습관 상태"),
                fieldWithPath("data.totalAchievementCount").type(JsonFieldType.NUMBER).description("총 달성 횟수"),
                fieldWithPath("data.todayHabitAchievementId").type(JsonFieldType.NUMBER).description("오늘 습관 달성 id"),
                fieldWithPath("data.createAt").type(JsonFieldType.STRING).description("습관 생성일"),
                fieldWithPath("data.archiveAt").type(JsonFieldType.STRING).description("습관 수정일"),

                fieldWithPath("data.mate").type(JsonFieldType.OBJECT).description("짝꿍"),
                fieldWithPath("data.mate.id").type(JsonFieldType.NUMBER).description("짝꿍 id"),
                fieldWithPath("data.mate.habitId").type(JsonFieldType.NUMBER).description("습관 id"),
                fieldWithPath("data.mate.memberId").type(JsonFieldType.NUMBER).description("사용자 id"),
                fieldWithPath("data.mate.title").type(JsonFieldType.STRING).description("짝꿍 이름"),
                fieldWithPath("data.mate.createAt").type(JsonFieldType.STRING).description("짝꿍 생성일"),
                fieldWithPath("data.mate.reward").type(JsonFieldType.NUMBER).description("짝꿍 박수 갯수"),
                fieldWithPath("data.mate.level").type(JsonFieldType.NUMBER).description("짝꿍 레벨"),
                fieldWithPath("data.mate.rewardHistory[]").type(JsonFieldType.ARRAY).description("짝꿍 보상 이력"),
                fieldWithPath("data.mate.rewardHistory[].createAt").type(JsonFieldType.STRING).description("짝꿍 보상 이력 생성일"),

                fieldWithPath("data.mate.levelUpAt").type(JsonFieldType.STRING).description("짝꿍 레벨업 일자"),
                fieldWithPath("data.mate.characterType").type(JsonFieldType.STRING).description("짝꿍 타입"),
                fieldWithPath("data.mate.levelUpSection").type(JsonFieldType.ARRAY).description("레벨업 섹션"),
                fieldWithPath("data.mate.bubble").type(JsonFieldType.STRING).description("짝꿍 말풍선"),
                fieldWithPath("data.mate.status").type(JsonFieldType.STRING).description("짝꿍 상태"),

                fieldWithPath("data.notification").type(JsonFieldType.OBJECT).description("알림").optional(),
                fieldWithPath("data.notification.notificationTime").type(JsonFieldType.STRING).description("알림 시간").optional(),
                fieldWithPath("data.notification.contents").type(JsonFieldType.STRING).description("내용").optional()
        }
        return arr
    }

    static FieldDescriptor[] habitOverviewList() {
        return new FieldDescriptor[]{
                fieldWithPath("code").type(JsonFieldType.STRING).description("code"),
                fieldWithPath("message").description("message"),
                fieldWithPath("data[]").type(JsonFieldType.ARRAY).description("습관 개요 리스트"),
                fieldWithPath("data[].id").type(JsonFieldType.NUMBER).description("습관 id"),
                fieldWithPath("data[].memberId").type(JsonFieldType.NUMBER).description("사용자 id"),
                fieldWithPath("data[].title").type(JsonFieldType.STRING).description("습관 제목"),
                fieldWithPath("data[].imojiPath").type(JsonFieldType.STRING).description("이모지"),
                fieldWithPath("data[].color").type(JsonFieldType.STRING).description("습관 컬러"),
                fieldWithPath("data[].dayOfWeeks").type(JsonFieldType.ARRAY).description("수행요일 목록"),
                fieldWithPath("data[].reward").type(JsonFieldType.NUMBER).description("박수 갯수"),
                fieldWithPath("data[].sequence").type(JsonFieldType.NUMBER).description("연속 횟수"),
                fieldWithPath("data[].status").type(JsonFieldType.STRING).description("습관 상태"),
                fieldWithPath("data[].totalAchievementCount").type(JsonFieldType.NUMBER).description("총 달성 횟수"),
                fieldWithPath("data[].todayHabitAchievementId").type(JsonFieldType.NUMBER).description("오늘 습관 달성 id"),
                fieldWithPath("data[].createAt").type(JsonFieldType.STRING).description("습관 생성일"),
                fieldWithPath("data[].archiveAt").type(JsonFieldType.STRING).description("습관 수정일"),

                fieldWithPath("data[].mate").type(JsonFieldType.OBJECT).description("짝꿍"),
                fieldWithPath("data[].mate.id").type(JsonFieldType.NUMBER).description("짝꿍 id"),
                fieldWithPath("data[].mate.habitId").type(JsonFieldType.NUMBER).description("습관 id"),
                fieldWithPath("data[].mate.memberId").type(JsonFieldType.NUMBER).description("사용자 id"),
                fieldWithPath("data[].mate.title").type(JsonFieldType.STRING).description("짝꿍 이름"),
                fieldWithPath("data[].mate.createAt").type(JsonFieldType.STRING).description("짝꿍 생성일"),
                fieldWithPath("data[].mate.reward").type(JsonFieldType.NUMBER).description("짝꿍 박수 갯수"),
                fieldWithPath("data[].mate.level").type(JsonFieldType.NUMBER).description("짝꿍 레벨"),
                fieldWithPath("data[].mate.rewardHistory[]").type(JsonFieldType.ARRAY).description("짝꿍 보상 이력"),
                fieldWithPath("data[].mate.rewardHistory[].createAt").type(JsonFieldType.STRING).description("짝꿍 보상 이력 생성일"),

                fieldWithPath("data[].mate.levelUpAt").type(JsonFieldType.STRING).description("짝꿍 레벨업 일자"),
                fieldWithPath("data[].mate.characterType").type(JsonFieldType.STRING).description("짝꿍 타입"),
                fieldWithPath("data[].mate.levelUpSection").type(JsonFieldType.ARRAY).description("레벨업 섹션"),
                fieldWithPath("data[].mate.bubble").type(JsonFieldType.STRING).description("짝꿍 말풍선"),
                fieldWithPath("data[].mate.status").type(JsonFieldType.STRING).description("짝꿍 상태"),
                fieldWithPath("data[].mate.deleted").type(JsonFieldType.BOOLEAN).description("짝꿍 삭제 여부"),

        }
    }

    static FieldDescriptor[] habitOverview() {
        return new FieldDescriptor[]{
                fieldWithPath("data.id").type(JsonFieldType.NUMBER).description("습관 id"),
                fieldWithPath("data.memberId").type(JsonFieldType.NUMBER).description("사용자 id"),
                fieldWithPath("data.title").type(JsonFieldType.STRING).description("습관 제목"),
                fieldWithPath("data.imojiPath").type(JsonFieldType.STRING).description("이모지"),
                fieldWithPath("data.color").type(JsonFieldType.STRING).description("습관 컬러"),
                fieldWithPath("data.dayOfWeeks").type(JsonFieldType.ARRAY).description("수행요일 목록"),
                fieldWithPath("data.reward").type(JsonFieldType.NUMBER).description("박수 갯수"),
                fieldWithPath("data.sequence").type(JsonFieldType.NUMBER).description("연속 횟수"),
                fieldWithPath("data.status").type(JsonFieldType.STRING).description("습관 상태"),
                fieldWithPath("data.totalAchievementCount").type(JsonFieldType.NUMBER).description("총 달성 횟수"),
                fieldWithPath("data.todayHabitAchievementId").type(JsonFieldType.NUMBER).description("오늘 습관 달성 id"),
                fieldWithPath("data.createAt").type(JsonFieldType.STRING).description("습관 생성일"),
                fieldWithPath("data.archiveAt").type(JsonFieldType.STRING).description("습관 수정일"),

                fieldWithPath("data.mate").type(JsonFieldType.OBJECT).description("짝꿍"),
                fieldWithPath("data.mate.id").type(JsonFieldType.NUMBER).description("짝꿍 id"),
                fieldWithPath("data.mate.habitId").type(JsonFieldType.NUMBER).description("습관 id"),
                fieldWithPath("data.mate.memberId").type(JsonFieldType.NUMBER).description("사용자 id"),
                fieldWithPath("data.mate.title").type(JsonFieldType.STRING).description("짝꿍 이름"),
                fieldWithPath("data.mate.createAt").type(JsonFieldType.STRING).description("짝꿍 생성일"),
                fieldWithPath("data.mate.reward").type(JsonFieldType.NUMBER).description("짝꿍 박수 갯수"),
                fieldWithPath("data.mate.level").type(JsonFieldType.NUMBER).description("짝꿍 레벨"),
                fieldWithPath("data.mate.rewardHistory[]").type(JsonFieldType.ARRAY).description("짝꿍 보상 이력"),
                fieldWithPath("data.mate.rewardHistory[].createAt").type(JsonFieldType.STRING).description("짝꿍 보상 이력 생성일"),

                fieldWithPath("data.mate.levelUpAt").type(JsonFieldType.STRING).description("짝꿍 레벨업 일자"),
                fieldWithPath("data.mate.characterType").type(JsonFieldType.STRING).description("짝꿍 타입"),
                fieldWithPath("data.mate.levelUpSection").type(JsonFieldType.ARRAY).description("레벨업 섹션"),
                fieldWithPath("data.mate.bubble").type(JsonFieldType.STRING).description("짝꿍 말풍선"),
                fieldWithPath("data.mate.status").type(JsonFieldType.STRING).description("짝꿍 상태"),
                fieldWithPath("data.mate.deleted").type(JsonFieldType.STRING).description("짝꿍 삭제 여부"),

        }
    }

    static FieldDescriptor[] saveMemberResponse() {
        return new FieldDescriptor[]{
                fieldWithPath("data.id").type(JsonFieldType.NUMBER).description("회원 id"),
                fieldWithPath("data.name").type(JsonFieldType.STRING).description("회원명"),
                fieldWithPath("data.resource").type(JsonFieldType.STRING).description("resource"),
                fieldWithPath("data.notificationConsent").type(JsonFieldType.BOOLEAN).description("알림수신여부"),
                fieldWithPath("data.certificationSubject").type(JsonFieldType.STRING).description("인증서버 종류"),

                fieldWithPath("data.token").type(JsonFieldType.OBJECT).description("jwt토큰"),
                fieldWithPath("data.token.accessToken").type(JsonFieldType.STRING).description("accessToken"),
                fieldWithPath("data.token.refreshToken").type(JsonFieldType.STRING).description("refreshToken"),
        }
    }

    static FieldDescriptor[] member() {
        return new FieldDescriptor[]{
                fieldWithPath("data.id").type(JsonFieldType.NUMBER).description("회원 id"),
                fieldWithPath("data.name").type(JsonFieldType.STRING).description("회원명"),
                fieldWithPath("data.resource").type(JsonFieldType.OBJECT).description("resource"),
                fieldWithPath("data.notificationConsent").type(JsonFieldType.BOOLEAN).description("알림수신여부"),
                fieldWithPath("data.certificationSubject").type(JsonFieldType.STRING).description("인증서버 종류"),
                fieldWithPath("data.status").type(JsonFieldType.STRING).description("회원 status"),
        }
    }

    static FieldDescriptor[] signMemberRequest() {
        return new FieldDescriptor[]{
                fieldWithPath('certificationSubject').description('device fcmToken'),
                fieldWithPath("socialToken").description("device identifier")
        }
    }

    static FieldDescriptor[] appleSignMemberRequest() {
        return new FieldDescriptor[]{
                fieldWithPath("nonce").type(JsonFieldType.STRING).description("애플 로그인 서버에 요청한 nonce"),
                fieldWithPath('certificationSubject').type(JsonFieldType.STRING).description('소셜 로그인 종류 APPLE'),
                fieldWithPath("socialToken").type(JsonFieldType.STRING).description("애플 로그인 서버에서 받은 id_token"),
                fieldWithPath("code").type(JsonFieldType.STRING).description("애플 로그인 서버에서 받은 grant_code"),
                fieldWithPath("user").type(JsonFieldType.OBJECT).description("user"),
                fieldWithPath("user.email").type(JsonFieldType.STRING).description("애플 프록시 email"),
                fieldWithPath("user.name").type(JsonFieldType.OBJECT).description("애플 사용자 name"),
                fieldWithPath("user.name.firstName").type(JsonFieldType.STRING).description("애플 사용자 이름"),
                fieldWithPath("user.name.lastName").type(JsonFieldType.STRING).description("애플 사용자 성")
        }
    }

    static FieldDescriptor[] token() {
        return new FieldDescriptor[]{
                fieldWithPath("data.accessToken").type(JsonFieldType.STRING).description("accessToken"),
                fieldWithPath("data.refreshToken").type(JsonFieldType.STRING).description("refreshToken"),
        }
    }

    static FieldDescriptor[] recordResponse() {
        return new FieldDescriptor[]{
                fieldWithPath("data.rewardCount").type(JsonFieldType.NUMBER).description("박수 갯수"),
                fieldWithPath("data.achievementCount").type(JsonFieldType.NUMBER).description("달성 횟수"),
                fieldWithPath("data.frequentHabit").type(JsonFieldType.OBJECT).description("refreshToken"),
                fieldWithPath("data.frequentHabit.id").type(JsonFieldType.NUMBER).description("습관 id"),
                fieldWithPath("data.frequentHabit.memberId").type(JsonFieldType.NUMBER).description("사용자 id"),
                fieldWithPath("data.frequentHabit.title").type(JsonFieldType.STRING).description("습관 제목"),
                fieldWithPath("data.frequentHabit.imojiPath").type(JsonFieldType.STRING).description("이모지경로"),
                fieldWithPath("data.frequentHabit.color").type(JsonFieldType.STRING).description("색깔"),
                fieldWithPath("data.frequentHabit.dayOfWeeks").type(JsonFieldType.ARRAY).description("수행요일 목록"),
                fieldWithPath("data.frequentHabit.status").type(JsonFieldType.STRING).description("습관 상태"),
                fieldWithPath("data.frequentHabit.createAt").type(JsonFieldType.STRING).description("습관 생성일"),
        }
    }

    static FieldDescriptor[] updateHabitRequest() {
        return new FieldDescriptor[]{
                fieldWithPath("title").type(JsonFieldType.STRING).description("습관 제목"),
                fieldWithPath("imojiPath").type(JsonFieldType.STRING).description("이모지경로"),
                fieldWithPath("color").type(JsonFieldType.STRING).description("색깔"),
                fieldWithPath("dayOfWeeks").type(JsonFieldType.ARRAY).description("수행요일 목록"),
                fieldWithPath("notification").type(JsonFieldType.OBJECT).description("알림"),
                fieldWithPath("notification.notificationTime").type(JsonFieldType.STRING).description("알림 시간"),
                fieldWithPath("notification.contents").type(JsonFieldType.STRING).description("알림 내용"),

        }
    }

    static FieldDescriptor[] searchHabitRequest() {
        return new FieldDescriptor[]{
                fieldWithPath("status").type(JsonFieldType.STRING).description("습관 상태").optional(),
        }
    }

    static FieldDescriptor[] saveHabitAchievementRequest() {
        return new FieldDescriptor[]{
                fieldWithPath("achievementDate").type(JsonFieldType.STRING).description("습관 달성 일자")
        }
    }

    static FieldDescriptor[] habitAchievementList() {
        return new FieldDescriptor[]{
                fieldWithPath("code").type(JsonFieldType.STRING).description("code"),
                fieldWithPath("message").description("message"),
                fieldWithPath("data[]").type(JsonFieldType.ARRAY).description("습관 달성 리스트"),
                fieldWithPath("data[].id").type(JsonFieldType.NUMBER).description("습관 달성 id"),
                fieldWithPath("data[].habitId").type(JsonFieldType.NUMBER).description("습관 id"),
                fieldWithPath("data[].sequence").type(JsonFieldType.NUMBER).description("연속 달성 횟수"),
                fieldWithPath("data[].achievementDate").type(JsonFieldType.STRING).description("습관 달성일"),
                fieldWithPath("data[].nextAchievementDate").type(JsonFieldType.STRING).description("다음 습관 달성 예정일")
        }
    }

    static ParameterDescriptor[] searchHabitAchievementRequest() {
        return new ParameterDescriptor[]{
                fieldWithPath("datePeriod").type(JsonFieldType.OBJECT).description("기간 범위"),
                fieldWithPath("datePeriod.from").type(JsonFieldType.STRING).description("시작 일자"),
                fieldWithPath("datePeriod.to").type(JsonFieldType.STRING).description("끝 일자")
        }
    }

    static FieldDescriptor[] notificationBatchResponseList() {
        return new FieldDescriptor[]{
                fieldWithPath("code").type(JsonFieldType.STRING).description("code"),
                fieldWithPath("message").description("message"),
                fieldWithPath("data[]").type(JsonFieldType.ARRAY).description("전체 알림 리스트"),
                fieldWithPath("data[].title").type(JsonFieldType.STRING).description("알림 제목"),
                fieldWithPath("data[].content").type(JsonFieldType.STRING).description("알림 내용"),
                fieldWithPath("data[].messageResponse[]").type(JsonFieldType.ARRAY).description("메시지 응답").optional(),
                fieldWithPath("data[].messageResponse[].successCount").type(JsonFieldType.NUMBER).description("메시지 성공횟수").optional(),
                fieldWithPath("data[].messageResponse[].responses[]").type(JsonFieldType.ARRAY).description("발송 응답").optional(),
                fieldWithPath("data[].messageResponse[].responses[].successful").type(JsonFieldType.BOOLEAN).description("").optional(),
                fieldWithPath("data[].messageResponse[].responses[].messageId").type(JsonFieldType.STRING).description("발송 메시지 id").optional(),
                fieldWithPath("data[].messageResponse[].responses[].exception").type(JsonFieldType.OBJECT).description("예외").optional(),
                fieldWithPath("data[].messageResponse[].responses[].exception.cause").type(JsonFieldType.STRING).description("에러코드").optional(),
                fieldWithPath("data[].messageResponse[].responses[].exception.stackTrace[]").type(JsonFieldType.ARRAY).description("에러코드").optional(),
                fieldWithPath("data[].messageResponse[].responses[].exception.stackTrace[].classLoaderName").type(JsonFieldType.STRING).description("에러코드").optional(),
                fieldWithPath("data[].messageResponse[].responses[].exception.stackTrace[].moduleName").type(JsonFieldType.STRING).description("에러코드").optional(),
                fieldWithPath("data[].messageResponse[].responses[].exception.stackTrace[].moduleVersion").type(JsonFieldType.STRING).description("에러코드").optional(),
                fieldWithPath("data[].messageResponse[].responses[].exception.stackTrace[].methodName").type(JsonFieldType.STRING).description("에러코드").optional(),
                fieldWithPath("data[].messageResponse[].responses[].exception.stackTrace[].fileName").type(JsonFieldType.STRING).description("에러코드").optional(),
                fieldWithPath("data[].messageResponse[].responses[].exception.stackTrace[].lineNumber").type(JsonFieldType.NUMBER).description("에러코드").optional(),
                fieldWithPath("data[].messageResponse[].responses[].exception.stackTrace[].nativeMethod").type(JsonFieldType.BOOLEAN).description("에러코드").optional(),
                fieldWithPath("data[].messageResponse[].responses[].exception.stackTrace[].className").type(JsonFieldType.STRING).description("에러코드").optional(),
                fieldWithPath("data[].messageResponse[].failureCount").type(JsonFieldType.NUMBER).description("메시지 성공횟수").optional(),
                fieldWithPath("data[].messageResponse[].responses[]").type(JsonFieldType.ARRAY).description("").optional(),
                fieldWithPath("data[].messageResponse[].responses[].exception").type(JsonFieldType.OBJECT).description("").optional(),
                fieldWithPath("data[].messageResponse[].responses[].exception.errorCode").type(JsonFieldType.STRING).description("").optional(),
                fieldWithPath("data[].messageResponse[].responses[].exception.httpResponse").type(JsonFieldType.STRING).description("").optional(),
                fieldWithPath("data[].messageResponse[].responses[].exception.messagingErrorCode").type(JsonFieldType.STRING).description("").optional(),
                fieldWithPath("data[].messageResponse[].responses[].exception.message").type(JsonFieldType.STRING).description("").optional(),
                fieldWithPath("data[].messageResponse[].responses[].exception.suppressed[]").type(JsonFieldType.ARRAY).description("").optional(),
                fieldWithPath("data[].messageResponse[].responses[].exception.localizedMessage").type(JsonFieldType.STRING).description("").optional(),
                fieldWithPath("data[].messageResponse[].successful").type(JsonFieldType.BOOLEAN).description("").optional(),
        }
    }


    static FieldDescriptor[] batchResponseList() {
        return new FieldDescriptor[]{
                fieldWithPath("code").type(JsonFieldType.STRING).description("code"),
                fieldWithPath("message").description("message"),
                fieldWithPath("data[].successCount").type(JsonFieldType.NUMBER).description("메시지 성공횟수").optional(),
                fieldWithPath("data[].responses[]").type(JsonFieldType.ARRAY).description("발송 응답").optional(),
                fieldWithPath("data[].responses[].successful").type(JsonFieldType.BOOLEAN).description("").optional(),
                fieldWithPath("data[].responses[].messageId").type(JsonFieldType.STRING).description("발송 메시지 id").optional(),
                fieldWithPath("data[].responses[].exception").type(JsonFieldType.OBJECT).description("예외").optional(),
                fieldWithPath("data[].responses[].exception.cause").type(JsonFieldType.STRING).description("").optional(),
                fieldWithPath("data[].responses[].exception.stackTrace[]").type(JsonFieldType.ARRAY).description("").optional(),
                fieldWithPath("data[].responses[].exception.stackTrace[].classLoaderName").type(JsonFieldType.STRING).description("").optional(),
                fieldWithPath("data[].responses[].exception.stackTrace[].moduleName").type(JsonFieldType.STRING).description("").optional(),
                fieldWithPath("data[].responses[].exception.stackTrace[].moduleVersion").type(JsonFieldType.STRING).description("").optional(),
                fieldWithPath("data[].responses[].exception.stackTrace[].methodName").type(JsonFieldType.STRING).description("").optional(),
                fieldWithPath("data[].responses[].exception.stackTrace[].fileName").type(JsonFieldType.STRING).description("").optional(),
                fieldWithPath("data[].responses[].exception.stackTrace[].lineNumber").type(JsonFieldType.NUMBER).description("").optional(),
                fieldWithPath("data[].responses[].exception.stackTrace[].nativeMethod").type(JsonFieldType.BOOLEAN).description("").optional(),
                fieldWithPath("data[].responses[].exception.stackTrace[].className").type(JsonFieldType.STRING).description("").optional(),
                fieldWithPath("data[].failureCount").type(JsonFieldType.NUMBER).description("").optional(),
                fieldWithPath("data[].responses[]").type(JsonFieldType.ARRAY).description("").optional(),
                fieldWithPath("data[].responses[].exception").type(JsonFieldType.OBJECT).description("").optional(),
                fieldWithPath("data[].responses[].exception.errorCode").type(JsonFieldType.STRING).description("").optional(),
                fieldWithPath("data[].responses[].exception.httpResponse").type(JsonFieldType.STRING).description("").optional(),
                fieldWithPath("data[].responses[].exception.messagingErrorCode").type(JsonFieldType.STRING).description("").optional(),
                fieldWithPath("data[].responses[].exception.message").type(JsonFieldType.STRING).description("").optional(),
                fieldWithPath("data[].responses[].exception.suppressed[]").type(JsonFieldType.ARRAY).description("").optional(),
                fieldWithPath("data[].responses[].exception.localizedMessage").type(JsonFieldType.STRING).description("").optional(),
                fieldWithPath("data[].successful").type(JsonFieldType.BOOLEAN).description("").optional()
        }
    }

    static FieldDescriptor[] notificationHistoryResponseList() {
        return new FieldDescriptor[]{
                fieldWithPath("code").type(JsonFieldType.STRING).description("code"),
                fieldWithPath("message").description("message"),
                fieldWithPath("data[].id").type(JsonFieldType.NUMBER).description("알림 이력 id"),
                fieldWithPath("data[].memberId").type(JsonFieldType.NUMBER).description("사용자 id"),
                fieldWithPath("data[].notificationId").type(JsonFieldType.NUMBER).description("알림 id"),
                fieldWithPath("data[].title").type(JsonFieldType.STRING).description("알림 제목"),
                fieldWithPath("data[].contents").type(JsonFieldType.STRING).description("알림 내용"),
                fieldWithPath("data[].status").type(JsonFieldType.STRING).description("알림 성공 여부"),
                fieldWithPath("data[].type").type(JsonFieldType.STRING).description("알림 타입"),
                fieldWithPath("data[].createAt").type(JsonFieldType.STRING).description("알림 이력 생성 시간")
        }
    }

    static FieldDescriptor[] editStatusNotificationRequest() {
        return new FieldDescriptor[]{
                fieldWithPath("status").type(JsonFieldType.STRING).description("알림 이력 상태")
        }
    }

    static FieldDescriptor[] notificationHistoryResponse() {
        return new FieldDescriptor[]{
                fieldWithPath("data.id").type(JsonFieldType.NUMBER).description("알림 이력 id"),
                fieldWithPath("data.memberId").type(JsonFieldType.NUMBER).description("사용자 id"),
                fieldWithPath("data.notificationId").type(JsonFieldType.NUMBER).description("알림 id"),
                fieldWithPath("data.title").type(JsonFieldType.STRING).description("알림 제목"),
                fieldWithPath("data.contents").type(JsonFieldType.STRING).description("알림 내용"),
                fieldWithPath("data.status").type(JsonFieldType.STRING).description("알림 성공 여부"),
                fieldWithPath("data.type").type(JsonFieldType.STRING).description("알림 타입"),
                fieldWithPath("data.createAt").type(JsonFieldType.STRING).description("알림 이력 생성 시간")
        }
    }
}
