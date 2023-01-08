package com.depromeet.threedays.front.web.controller.docs

import org.apache.commons.lang.ArrayUtils
import org.springframework.restdocs.payload.FieldDescriptor
import org.springframework.restdocs.payload.JsonFieldType

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
                fieldWithPath("data.id").type(JsonFieldType.STRING).description("습관 제목"),
                fieldWithPath("data.memberId").type(JsonFieldType.STRING).description("습관 제목"),
                fieldWithPath("data.title").type(JsonFieldType.STRING).description("습관 제목"),
                fieldWithPath("data.imojiPath").type(JsonFieldType.STRING).description("이모지"),
                fieldWithPath("data.color").type(JsonFieldType.STRING).description("습관 컬러"),
                fieldWithPath("data.dayOfWeeks").type(JsonFieldType.ARRAY).description("수행요일 목록"),
                fieldWithPath("data.reward").type(JsonFieldType.STRING).description("습관 제목"),
                fieldWithPath("data.sequence").type(JsonFieldType.STRING).description("습관 컬러"),
                fieldWithPath("data.status").type(JsonFieldType.STRING).description("습관 컬러"),
                fieldWithPath("data.totalAchievementCount").type(JsonFieldType.STRING).description("습관 컬러"),
                fieldWithPath("data.todayHabitAchievementId").type(JsonFieldType.STRING).description("습관 컬러"),
                mateResponse(),
                notification(),
                fieldWithPath("data.createAt").type(JsonFieldType.STRING).description("습관 컬러"),
                fieldWithPath("data.archiveAt").type(JsonFieldType.STRING).description("습관 컬러"),
        }
    }

    static FieldDescriptor[] mateResponse() {
        return new FieldDescriptor[]{
                fieldWithPath("data.mate").type(JsonFieldType.STRING).description("습관 컬러"),
                fieldWithPath("data.mate.id").type(JsonFieldType.STRING).description("습관 컬러"),
                fieldWithPath("data.mate.habitId").type(JsonFieldType.STRING).description("습관 컬러"),
                fieldWithPath("data.mate.memberId").type(JsonFieldType.STRING).description("습관 컬러"),
                fieldWithPath("data.mate.title").type(JsonFieldType.STRING).description("습관 컬러"),
                fieldWithPath("color").type(JsonFieldType.STRING).description("습관 컬러"),
                fieldWithPath("data.mate.createAt").type(JsonFieldType.STRING).description("습관 컬러"),
                fieldWithPath("data.mate.reward").type(JsonFieldType.STRING).description("습관 컬러"),
                fieldWithPath("data.mate.rewardHistory").type(JsonFieldType.STRING).description("습관 컬러"),
                fieldWithPath("data.mate.levelUpAt").type(JsonFieldType.STRING).description("습관 컬러"),
                fieldWithPath("data.mate.characterType").type(JsonFieldType.STRING).description("습관 컬러"),
                fieldWithPath("data.mate.levelUpSection").type(JsonFieldType.STRING).description("습관 컬러"),
                fieldWithPath("data.mate.bubble").type(JsonFieldType.STRING).description("습관 컬러"),
                fieldWithPath("data.mate.status").type(JsonFieldType.STRING).description("습관 컬러"),
        }
    }

    static FieldDescriptor[] mate() {
        return new FieldDescriptor[]{
                fieldWithPath("data.id").type(JsonFieldType.NUMBER).description("습관 컬러"),
                fieldWithPath("data.habitId").type(JsonFieldType.NUMBER).description("습관 컬러"),
                fieldWithPath("data.memberId").type(JsonFieldType.NUMBER).description("습관 컬러"),
                fieldWithPath("data.title").type(JsonFieldType.STRING).description("습관 컬러"),
                fieldWithPath("data.createAt").type(JsonFieldType.STRING).description("습관 컬러"),
                fieldWithPath("data.level").type(JsonFieldType.NUMBER).description("습관 컬러"),
                fieldWithPath("data.reward").type(JsonFieldType.NUMBER).description("습관 컬러"),
//                fieldWithPath("data.rewardHistory").type(JsonFieldType.ARRAY).description("습관 컬러"),
                fieldWithPath("data.rewardHistory[]").type(JsonFieldType.ARRAY).description("습관 컬러"),
                fieldWithPath("data.rewardHistory[].createAt").type(JsonFieldType.STRING).description("습관 컬러"),
                fieldWithPath("data.levelUpAt").type(JsonFieldType.STRING).description("습관 컬러"),
                fieldWithPath("data.characterType").type(JsonFieldType.STRING).description("습관 컬러"),
                fieldWithPath("data.levelUpSection").type(JsonFieldType.ARRAY).description("습관 컬러"),
                fieldWithPath("data.bubble").type(JsonFieldType.STRING).description("습관 컬러"),
                fieldWithPath("data.status").type(JsonFieldType.STRING).description("습관 컬러"),
                fieldWithPath("data.deleted").type(JsonFieldType.BOOLEAN).description("습관 컬러"),

        }
    }

    static FieldDescriptor[] mateResponseList() {
        return new FieldDescriptor[]{
                fieldWithPath("code").type(JsonFieldType.STRING).description("code"),
                fieldWithPath("message").description("message"),
                fieldWithPath("data[]").type(JsonFieldType.ARRAY).description("습관 컬러"),
                fieldWithPath("data[].id").type(JsonFieldType.NUMBER).description("습관 컬러"),
                fieldWithPath("data[].habitId").type(JsonFieldType.NUMBER).description("습관 컬러"),
                fieldWithPath("data[].memberId").type(JsonFieldType.NUMBER).description("습관 컬러"),
                fieldWithPath("data[].title").type(JsonFieldType.STRING).description("습관 컬러"),
                fieldWithPath("data[].createAt").type(JsonFieldType.STRING).description("습관 컬러"),
                fieldWithPath("data[].level").type(JsonFieldType.NUMBER).description("습관 컬러"),
                fieldWithPath("data[].reward").type(JsonFieldType.NUMBER).description("습관 컬러"),
                fieldWithPath("data[].rewardHistory[]").type(JsonFieldType.ARRAY).description("습관 컬러"),
                fieldWithPath("data[].rewardHistory[].createAt").type(JsonFieldType.STRING).description("습관 컬러"),
                fieldWithPath("data[].levelUpAt").type(JsonFieldType.STRING).description("습관 컬러"),
                fieldWithPath("data[].characterType").type(JsonFieldType.STRING).description("습관 컬러"),
                fieldWithPath("data[].levelUpSection").type(JsonFieldType.ARRAY).description("습관 컬러"),
                fieldWithPath("data[].bubble").type(JsonFieldType.STRING).description("습관 컬러"),
                fieldWithPath("data[].status").type(JsonFieldType.STRING).description("습관 컬러"),
        }
    }

    static FieldDescriptor[] habitResponse() {
        def arr = new FieldDescriptor[]{
                fieldWithPath("data.id").type(JsonFieldType.NUMBER).description("습관 제목"),
                fieldWithPath("data.memberId").type(JsonFieldType.NUMBER).description("습관 제목"),
                fieldWithPath("data.title").type(JsonFieldType.STRING).description("습관 제목"),
                fieldWithPath("data.imojiPath").type(JsonFieldType.STRING).description("이모지"),
                fieldWithPath("data.color").type(JsonFieldType.STRING).description("습관 컬러"),
                fieldWithPath("data.dayOfWeeks").type(JsonFieldType.ARRAY).description("수행요일 목록"),
                fieldWithPath("data.reward").type(JsonFieldType.NUMBER).description("습관 제목"),
                fieldWithPath("data.sequence").type(JsonFieldType.NUMBER).description("습관 컬러"),
                fieldWithPath("data.status").type(JsonFieldType.STRING).description("습관 컬러"),
                fieldWithPath("data.totalAchievementCount").type(JsonFieldType.NUMBER).description("습관 컬러"),
                fieldWithPath("data.todayHabitAchievementId").type(JsonFieldType.NUMBER).description("습관 컬러"),
                fieldWithPath("data.createAt").type(JsonFieldType.STRING).description("습관 컬러"),
                fieldWithPath("data.archiveAt").type(JsonFieldType.STRING).description("습관 컬러"),

                fieldWithPath("data.mate").type(JsonFieldType.OBJECT).description("습관 컬러"),
                fieldWithPath("data.mate.id").type(JsonFieldType.NUMBER).description("습관 컬러"),
                fieldWithPath("data.mate.habitId").type(JsonFieldType.NUMBER).description("습관 컬러"),
                fieldWithPath("data.mate.memberId").type(JsonFieldType.NUMBER).description("습관 컬러"),
                fieldWithPath("data.mate.title").type(JsonFieldType.STRING).description("습관 컬러"),
                fieldWithPath("data.mate.createAt").type(JsonFieldType.STRING).description("습관 컬러"),
                fieldWithPath("data.mate.reward").type(JsonFieldType.NUMBER).description("습관 컬러"),
                fieldWithPath("data.mate.level").type(JsonFieldType.NUMBER).description("습관 컬러"),
                fieldWithPath("data.mate.rewardHistory[]").type(JsonFieldType.ARRAY).description("습관 컬러"),
                fieldWithPath("data.mate.rewardHistory[].createAt").type(JsonFieldType.STRING).description("습관 컬러"),

                fieldWithPath("data.mate.levelUpAt").type(JsonFieldType.STRING).description("습관 컬러"),
                fieldWithPath("data.mate.characterType").type(JsonFieldType.STRING).description("습관 컬러"),
                fieldWithPath("data.mate.levelUpSection").type(JsonFieldType.ARRAY).description("습관 컬러"),
                fieldWithPath("data.mate.bubble").type(JsonFieldType.STRING).description("습관 컬러"),
                fieldWithPath("data.mate.status").type(JsonFieldType.STRING).description("습관 컬러"),
//                fieldWithPath("data.mate.deleted").type(JsonFieldType.STRING).description("습관 컬러"),

                fieldWithPath("data.notification").type(JsonFieldType.OBJECT).description("습관 컬러"),
                fieldWithPath("data.notification.notificationTime").type(JsonFieldType.STRING).description("습관 컬러"),
                fieldWithPath("data.notification.contents").type(JsonFieldType.STRING).description("습관 컬러"),

        }
        return arr
    }

    static FieldDescriptor[] habitOverview() {
        def arr = new FieldDescriptor[]{
                fieldWithPath("data.id").type(JsonFieldType.NUMBER).description("습관 제목"),
                fieldWithPath("data.memberId").type(JsonFieldType.NUMBER).description("습관 제목"),
                fieldWithPath("data.title").type(JsonFieldType.STRING).description("습관 제목"),
                fieldWithPath("data.imojiPath").type(JsonFieldType.STRING).description("이모지"),
                fieldWithPath("data.color").type(JsonFieldType.STRING).description("습관 컬러"),
                fieldWithPath("data.dayOfWeeks").type(JsonFieldType.ARRAY).description("수행요일 목록"),
                fieldWithPath("data.reward").type(JsonFieldType.NUMBER).description("습관 제목"),
                fieldWithPath("data.sequence").type(JsonFieldType.NUMBER).description("습관 컬러"),
                fieldWithPath("data.status").type(JsonFieldType.STRING).description("습관 컬러"),
                fieldWithPath("data.totalAchievementCount").type(JsonFieldType.NUMBER).description("습관 컬러"),
                fieldWithPath("data.todayHabitAchievementId").type(JsonFieldType.NUMBER).description("습관 컬러"),
                fieldWithPath("data.createAt").type(JsonFieldType.STRING).description("습관 컬러"),
                fieldWithPath("data.archiveAt").type(JsonFieldType.STRING).description("습관 컬러"),

                fieldWithPath("data.mate").type(JsonFieldType.OBJECT).description("습관 컬러"),
                fieldWithPath("data.mate.id").type(JsonFieldType.NUMBER).description("습관 컬러"),
                fieldWithPath("data.mate.habitId").type(JsonFieldType.NUMBER).description("습관 컬러"),
                fieldWithPath("data.mate.memberId").type(JsonFieldType.NUMBER).description("습관 컬러"),
                fieldWithPath("data.mate.title").type(JsonFieldType.STRING).description("습관 컬러"),
                fieldWithPath("data.mate.createAt").type(JsonFieldType.STRING).description("습관 컬러"),
                fieldWithPath("data.mate.reward").type(JsonFieldType.NUMBER).description("습관 컬러"),
                fieldWithPath("data.mate.level").type(JsonFieldType.NUMBER).description("습관 컬러"),
                fieldWithPath("data.mate.rewardHistory[]").type(JsonFieldType.ARRAY).description("습관 컬러"),
                fieldWithPath("data.mate.rewardHistory[].createAt").type(JsonFieldType.STRING).description("습관 컬러"),

                fieldWithPath("data.mate.levelUpAt").type(JsonFieldType.STRING).description("습관 컬러"),
                fieldWithPath("data.mate.characterType").type(JsonFieldType.STRING).description("습관 컬러"),
                fieldWithPath("data.mate.levelUpSection").type(JsonFieldType.ARRAY).description("습관 컬러"),
                fieldWithPath("data.mate.bubble").type(JsonFieldType.STRING).description("습관 컬러"),
                fieldWithPath("data.mate.status").type(JsonFieldType.STRING).description("습관 컬러"),

                fieldWithPath("data.notification").type(JsonFieldType.OBJECT).description("알림").optional(),
                fieldWithPath("data.notification.notificationTime").type(JsonFieldType.STRING).description("알림 시간").optional(),
                fieldWithPath("data.notification.contents").type(JsonFieldType.STRING).description("내용").optional()
        }
        return arr
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

    static FieldDescriptor[] token() {
        return new FieldDescriptor[]{
                fieldWithPath("data.accessToken").type(JsonFieldType.STRING).description("accessToken"),
                fieldWithPath("data.refreshToken").type(JsonFieldType.STRING).description("refreshToken"),
        }
    }

    static FieldDescriptor[] recordResponse() {
        return new FieldDescriptor[]{
                fieldWithPath("data.rewardCount").type(JsonFieldType.NUMBER).description("accessToken"),
                fieldWithPath("data.achievementCount").type(JsonFieldType.NUMBER).description("refreshToken"),
                fieldWithPath("data.frequentHabit").type(JsonFieldType.OBJECT).description("refreshToken"),
                fieldWithPath("data.frequentHabit.id").type(JsonFieldType.NUMBER).description("refreshToken"),
                fieldWithPath("data.frequentHabit.memberId").type(JsonFieldType.NUMBER).description("refreshToken"),
                fieldWithPath("data.frequentHabit.title").type(JsonFieldType.STRING).description("refreshToken"),
                fieldWithPath("data.frequentHabit.imojiPath").type(JsonFieldType.STRING).description("refreshToken"),
                fieldWithPath("data.frequentHabit.color").type(JsonFieldType.STRING).description("refreshToken"),
                fieldWithPath("data.frequentHabit.dayOfWeeks").type(JsonFieldType.ARRAY).description("refreshToken"),
                fieldWithPath("data.frequentHabit.status").type(JsonFieldType.STRING).description("refreshToken"),
                fieldWithPath("data.frequentHabit.createAt").type(JsonFieldType.STRING).description("refreshToken"),

        }
    }

    static FieldDescriptor[] updateHabitRequest() {
        return new FieldDescriptor[]{
                fieldWithPath("title").type(JsonFieldType.STRING).description("accessToken"),
                fieldWithPath("imojiPath").type(JsonFieldType.STRING).description("refreshToken"),
                fieldWithPath("color").type(JsonFieldType.STRING).description("refreshToken"),
                fieldWithPath("dayOfWeeks").type(JsonFieldType.ARRAY).description("refreshToken"),
                fieldWithPath("notification").type(JsonFieldType.OBJECT).description("refreshToken"),
                fieldWithPath("notification.notificationTime").type(JsonFieldType.STRING).description("refreshToken"),
                fieldWithPath("notification.contents").type(JsonFieldType.STRING).description("refreshToken"),

        }
    }

    static FieldDescriptor[] searchHabitRequest() {
        return new FieldDescriptor[]{
                fieldWithPath("status").type(JsonFieldType.STRING).description("habit status").optional(),

        }
    }

}
