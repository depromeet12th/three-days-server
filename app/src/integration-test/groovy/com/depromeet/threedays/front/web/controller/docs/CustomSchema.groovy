package com.depromeet.threedays.front.web.controller.docs

import com.depromeet.threedays.data.enums.*
import com.depromeet.threedays.front.domain.model.RewardHistory
import com.depromeet.threedays.front.domain.model.habit.HabitOverview
import com.depromeet.threedays.front.domain.model.mate.Mate
import com.depromeet.threedays.front.domain.model.member.Member
import com.depromeet.threedays.front.domain.model.member.SaveMemberUseCaseResponse
import com.depromeet.threedays.front.domain.model.member.Token
import com.depromeet.threedays.front.domain.model.notification.Notification
import com.depromeet.threedays.front.web.request.habit.SaveHabitRequest
import com.depromeet.threedays.front.web.response.*
import org.json.simple.JSONObject

import java.time.DayOfWeek
import java.time.LocalDateTime
import java.time.LocalTime

class CustomSchema {
    static NotificationResponse notificationResponse() {
        return new NotificationResponse(LocalTime.now(), "물마셔어어어어억")
    }

    static SaveHabitRequest saveHabitRequest() {
        return SaveHabitRequest.builder()
                .title("물마셔..")
                .imojiPath("imoji")
                .color("pink")
                .dayOfWeeks(EnumSet.of(DayOfWeek.MONDAY, DayOfWeek.TUESDAY, DayOfWeek.FRIDAY))
                .notification(Notification.builder()
                        .contents("알림짝짝")
                        .notificationTime(LocalTime.now())
                        .build())
                .build()
    }

    static HabitResponse habitResponse() {
        def mateResponse = mateResponse()
        def notificationResponse = notificationResponse()
        return HabitResponse.builder()
                .id(0L)
                .memberId(0L)
                .title("물마셔...")
                .imojiPath("imoji")
                .color("pink")
                .dayOfWeeks(EnumSet.of(DayOfWeek.MONDAY, DayOfWeek.TUESDAY, DayOfWeek.FRIDAY))
                .reward(1)
                .sequence(0)
                .status(HabitStatus.ACTIVE)
                .totalAchievementCount(0)
                .todayHabitAchievementId(0)
                .mateResponse(mateResponse)
                .notificationResponse(notificationResponse)
                .createAt(LocalDateTime.now())
                .archiveAt(LocalDateTime.now())
                .build()
    }

    static MateResponse mateResponse() {
        return MateResponse.builder()
                .id(0L)
                .habitId(0L)
                .memberId(0L).
                title("짝꿍이").
                createAt(LocalDateTime.now())
                .level(0)
                .reward(0)
                .rewardHistoryResponse(rewardHistoryResponse())
                .levelUpAt(LocalDateTime.now())
                .characterType(MateType.CARROT)
                .levelUpSection([1, 4, 8, 14, 22])
                .bubble("물안마시면클나")
                .status(MateStatus.ACTIVE)
                .build()
    }

    static List<MateResponse> mateResponseList() {
        def list = new ArrayList<MateResponse>()
        list.add(mateResponse())
        return list
    }

    static List<RewardHistoryResponse> rewardHistoryResponse() {
        List<RewardHistoryResponse> responses = new ArrayList<>()
        responses.add(RewardHistoryResponse.builder().createAt(LocalDateTime.now()).build())
        return responses
    }

    static Member memberConsentFalse() {
        return Member.builder()
                .id(0L)
                .name("admin")
                .certificationSubject(CertificationSubject.KAKAO)
                .status(MemberStatus.REGULAR)
                .notificationConsent(false)
                .resource(new JSONObject())
                .build()
    }

    static Member memberNameNew() {
        return Member.builder()
                .id(0L)
                .name("new name")
                .certificationSubject(CertificationSubject.KAKAO)
                .status(MemberStatus.REGULAR)
                .notificationConsent(false)
                .resource(new JSONObject())
                .build()
    }

    static SaveMemberUseCaseResponse saveMemberUseCaseResponse() {
        return SaveMemberUseCaseResponse.builder()
                .id(0L)
                .notificationConsent(true)
                .resource("{}")
                .isNew(true)
                .name("admin")
                .certificationSubject(CertificationSubject.KAKAO)
                .token(Token.builder().refreshToken("asd").accessToken("asd").build())
                .build()
    }

    static Mate mate() {
        def rewardHistories = new ArrayList<RewardHistory>()
        rewardHistories.add(new RewardHistory(LocalDateTime.now()))
        return Mate.builder()
                .id(0L)
                .habitId(0L)
                .memberId(0L)
                .title("짝꿍이")
                .createAt(LocalDateTime.now())
                .level(0)
                .reward(0)
                .rewardHistory(rewardHistories)
                .levelUpAt(LocalDateTime.now())
                .characterType(MateType.CARROT)
                .levelUpSection([1, 4, 8, 14, 22])
                .bubble("쉬지뭬")
                .status(MateStatus.ACTIVE)
                .deleted(false)
                .build()
    }

    static RecordHabitResponse recordHabitResponse() {
        return RecordHabitResponse.builder()
                .id(0L)
                .memberId(0L)
                .title("물을 마시쟈")
                .imojiPath("imoji")
                .color("pink")
                .dayOfWeeks(EnumSet.of(DayOfWeek.MONDAY, DayOfWeek.TUESDAY, DayOfWeek.FRIDAY))
                .status(HabitStatus.ACTIVE)
                .createAt(LocalDateTime.now())
                .build()
    }

    static RecordResponse recordResponse() {
        return RecordResponse.builder()
                .rewardCount(3)
                .achievementCount(9)
                .frequentHabit(recordHabitResponse())
                .build()
    }

    static List<HabitOverview> habitOverviewList() {
        def list = new ArrayList<HabitOverview>()
        list.add(
                HabitOverview.builder()
                        .id(0L)
                        .memberId(0L)
                        .title("물무")
                        .imojiPath("imoji")
                        .dayOfWeeks(EnumSet.of(DayOfWeek.MONDAY))
                        .reward(0)
                        .color("red")
                        .status(HabitStatus.ACTIVE)
                        .createAt(LocalDateTime.now())
                        .archiveAt(LocalDateTime.now())
                        .totalAchievementCount(0)
                        .todayHabitAchievementId(0)
                        .sequence(0)
                        .mate(mate())
                        .build())
        return list
    }
}
