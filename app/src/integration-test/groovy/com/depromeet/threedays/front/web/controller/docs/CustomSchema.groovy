package com.depromeet.threedays.front.web.controller.docs

import com.depromeet.threedays.data.enums.*
import com.depromeet.threedays.front.domain.model.DatePeriod
import com.depromeet.threedays.front.domain.model.RewardHistory
import com.depromeet.threedays.front.domain.model.habit.Habit
import com.depromeet.threedays.front.domain.model.habit.HabitAchievement
import com.depromeet.threedays.front.domain.model.habit.HabitOverview
import com.depromeet.threedays.front.domain.model.mate.Mate
import com.depromeet.threedays.front.domain.model.member.Member
import com.depromeet.threedays.front.domain.model.member.SaveMemberUseCaseResponse
import com.depromeet.threedays.front.domain.model.member.Token
import com.depromeet.threedays.front.domain.model.notification.Notification
import com.depromeet.threedays.front.domain.model.notification.NotificationHistory
import com.depromeet.threedays.front.web.request.habit.SaveHabitAchievementRequest
import com.depromeet.threedays.front.web.request.habit.SaveHabitRequest
import com.depromeet.threedays.front.web.request.habit.SearchHabitAchievementRequest
import com.depromeet.threedays.front.web.response.*
import com.google.firebase.ErrorCode
import com.google.firebase.messaging.BatchResponse
import com.google.firebase.messaging.FirebaseMessagingException
import com.google.firebase.messaging.SendResponse
import org.json.simple.JSONObject

import java.time.DayOfWeek
import java.time.LocalDate
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
                        .dayOfWeeks(EnumSet.of(DayOfWeek.MONDAY, DayOfWeek.TUESDAY, DayOfWeek.FRIDAY))
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

    static List<HabitAchievement> habitAchievementList() {
        def list = new ArrayList<HabitAchievement>()
        list.add(
                HabitAchievement.builder()
                        .id(0L)
                        .habitId(0L)
                        .sequence(1)
                        .achievementDate(LocalDate.now())
                        .nextAchievementDate(LocalDate.now().plusDays(1))
                        .build())
        println list.first().achievementDate
        return list
    }

    static SaveHabitAchievementRequest saveHabitAchievementRequest() {
        return SaveHabitAchievementRequest.builder().achievementDate(LocalDate.now()).build()
    }

    static Habit habit() {
        return Habit.builder()
                .id(0L)
                .memberId(0L)
                .title("title")
                .imojiPath("imojiPath")
                .color("color")
                .dayOfWeeks(EnumSet.of(DayOfWeek.MONDAY, DayOfWeek.TUESDAY, DayOfWeek.FRIDAY))
                .reward(0L)
                .archiveNumberOfDate(0)
                .totalAchievementCount(0L)
                .status(HabitStatus.ACTIVE)
                .habitAchievement(this.habitAchievementList().first())
                .createAt(LocalDateTime.now())
                .archiveAt(LocalDateTime.now())
                .deleted(false)
                .mate(this.mate())
                .notification(this.notification())
                .build()
    }

    static Notification notification() {
        return Notification.builder()
                .notificationTime(LocalTime.now())
                .contents("contents")
                .build()
    }

    static SearchHabitAchievementRequest searchHabitAchievementRequest() {
        def period = new DatePeriod(LocalDate.now(), LocalDate.now().plusDays(1))
        return SearchHabitAchievementRequest.builder().datePeriod(period).build()
    }

    static List<NotificationBatchResponse> notificationBatchResponseList() {
        def messageResponse = new ArrayList<BatchResponse>()
        messageResponse.add(new BatchResponse() {
            @Override
            List<SendResponse> getResponses() {
                def sendResponses = new ArrayList<SendResponse>()
                sendResponses.add(new SendResponse("messageId", new FirebaseMessagingException(ErrorCode.ABORTED, "message")))
                return sendResponses
            }

            @Override
            int getSuccessCount() {
                return 1
            }

            @Override
            int getFailureCount() {
                return 1
            }
        })
        def list = new ArrayList<NotificationBatchResponse>()
        list.add(NotificationBatchResponse.builder().title("title").content("content").messageResponse(messageResponse).build())
        return list
    }

    static List<BatchResponse> batchResponseList() {
        def batchResponses = new ArrayList<BatchResponse>()
        batchResponses.add(new BatchResponse() {
            @Override
            List<SendResponse> getResponses() {
                def sendResponses = new ArrayList<SendResponse>()
                sendResponses.add(new SendResponse("messageId", new FirebaseMessagingException(ErrorCode.ABORTED, "message")))
                return sendResponses
            }

            @Override
            int getSuccessCount() {
                return 1
            }

            @Override
            int getFailureCount() {
                return 1
            }
        })
        return batchResponses
    }

    static List<NotificationHistory> notificationHistoryList() {
        def notificationHistories = new ArrayList<NotificationHistory>()
        notificationHistories().add(
                NotificationHistory.builder().id(0L).memberId(0L).notificationId(0L).title("title").contents("contents").status(NotificationStatus.SUCCESS).type(NotificationType.HABIT).createAt(LocalDateTime.now()).build()
        )
        return notificationHistories
    }
}
