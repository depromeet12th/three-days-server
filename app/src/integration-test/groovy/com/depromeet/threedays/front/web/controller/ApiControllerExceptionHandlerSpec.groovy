package com.depromeet.threedays.front.web.controller

import com.depromeet.threedays.front.IntegrationTestSpecification
import com.depromeet.threedays.front.config.security.filter.token.TokenGenerator
import com.depromeet.threedays.front.domain.command.SaveMemberCommand
import com.depromeet.threedays.front.domain.usecase.habit.DeleteHabitAchievementUseCase
import com.depromeet.threedays.front.domain.usecase.habit.SaveHabitUseCase
import com.depromeet.threedays.front.domain.usecase.mate.SaveMateUseCase
import com.depromeet.threedays.front.domain.usecase.member.SaveMemberUseCase
import com.depromeet.threedays.front.exception.ExternalIntegrationException
import com.depromeet.threedays.front.exception.PolicyViolationException
import com.depromeet.threedays.front.web.request.habit.SaveHabitRequest
import com.depromeet.threedays.front.web.request.mate.SaveMateRequest
import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.test.web.servlet.MockMvc

class ApiControllerExceptionHandlerSpec extends IntegrationTestSpecification {

    @Autowired
    private MockMvc mockMvc

    @Autowired
    private ObjectMapper objectMapper

    @Autowired
    private TokenGenerator tokenGenerator

    private String habit_constraints_insufficient_day_of_weeks = "실천 요일을 3개 이상 선택해 주세요."
    private String habit_achievement_constraints_date_is_in_the_past = "이전의 기록을 변경할 수 없어요."
    private String habit_achievement_constraints_invalid_achievement_date = "습관 취소는 오늘만 할 수 있어요."
    private String habit_constraints_insufficient_notification = "Push 알림을 마저 설정해 주세요."
    private String social_login_error = "로그인에 실패했어요."
    private String mate_constraints_already_exist_mate = "이미 함께하고 있는 짝꿍이 있어요."
    private String mate_constraints_invalid_habit = "생성된 습관이 없을 때는 짝꿍을 만들 수 없어요!"

    private String token

    def saveHabitUseCase = Mock(SaveHabitUseCase)
    def deleteHabitAchievementUseCase = Mock(DeleteHabitAchievementUseCase)
    def saveMemberUseCase = Mock(SaveMemberUseCase)
    def saveMateUseCase = Mock(SaveMateUseCase)

    def "habit.constraints.insufficient.day.of.weeks"() {
        given:
        saveHabitUseCase.execute(_ as SaveHabitRequest) >> {
            SaveHabitRequest request -> throw new PolicyViolationException("habit.constraints.insufficient.day.of.weeks")
        }

        when:
        saveHabitUseCase.execute(new SaveHabitRequest())

        then:
        def e = thrown(PolicyViolationException)
        e.message == habit_constraints_insufficient_day_of_weeks
    }

    def "habit.constraints.insufficient.notification"() {
        given:
        saveHabitUseCase.execute(_ as SaveHabitRequest) >> {
            SaveHabitRequest request -> throw new PolicyViolationException("habit.constraints.insufficient.notification")
        }

        when:
        saveHabitUseCase.execute(new SaveHabitRequest())

        then:
        def e = thrown(PolicyViolationException)
        e.message == habit_constraints_insufficient_notification
    }

    def "habit.achievement.constraints.date.is.in.the.past"() {
        given:
        deleteHabitAchievementUseCase.execute(_ as Long, _ as Long) >> {
            throw new PolicyViolationException("habit.achievement.constraints.date.is.in.the.past")
        }

        when:
        deleteHabitAchievementUseCase.execute(0L, 0L)

        then:
        def e = thrown(PolicyViolationException)
        e.message == habit_achievement_constraints_date_is_in_the_past
    }

    def "habit.achievement.constraints.invalid.achievement.date"() {
        given:
        deleteHabitAchievementUseCase.execute(_ as Long, _ as Long) >> {
            throw new PolicyViolationException("habit.achievement.constraints.invalid.achievement.date")
        }

        when:
        deleteHabitAchievementUseCase.execute(0L, 0L)

        then:
        def e = thrown(PolicyViolationException)
        e.message == habit_achievement_constraints_invalid_achievement_date
    }


    def "social.login.error"() {
        given:
        saveMemberUseCase.execute(_ as SaveMemberCommand) >> {
            throw new ExternalIntegrationException("social.login.error")
        }

        when:
        saveMemberUseCase.execute(new SaveMemberCommand())

        then:
        def e = thrown(ExternalIntegrationException)
        e.message == social_login_error
    }

    def "mate.constraints.already.exist.mate"() {
        given:
        saveMateUseCase.execute(_ as Long, _ as SaveMateRequest) >> {
            throw new PolicyViolationException("mate.constraints.already.exist.mate")
        }

        when:
        saveMateUseCase.execute(0L, new SaveMateRequest())

        then:
        def e = thrown(PolicyViolationException)
        e.message == mate_constraints_already_exist_mate
    }

    def "mate.constraints.invalid.habit"() {
        given:
        saveMateUseCase.execute(_ as Long, _ as SaveMateRequest) >> {
            throw new PolicyViolationException("mate.constraints.invalid.habit")
        }

        when:
        saveMateUseCase.execute(0L, new SaveMateRequest())

        then:
        def e = thrown(PolicyViolationException)
        e.message == mate_constraints_invalid_habit
    }

}
