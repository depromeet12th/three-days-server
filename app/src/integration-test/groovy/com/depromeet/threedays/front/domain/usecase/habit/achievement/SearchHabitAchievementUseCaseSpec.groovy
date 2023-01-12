package com.depromeet.threedays.front.domain.usecase.habit.achievement

import com.depromeet.threedays.data.entity.member.MemberEntity
import com.depromeet.threedays.data.enums.MemberStatus
import com.depromeet.threedays.front.IntegrationTestSpecification
import com.depromeet.threedays.front.data.habit.HabitAchievementDataInitializer
import com.depromeet.threedays.front.data.habit.HabitDataInitializer
import com.depromeet.threedays.front.domain.model.DatePeriod
import com.depromeet.threedays.front.domain.usecase.habit.SearchHabitAchievementUseCase
import com.depromeet.threedays.front.persistence.repository.member.MemberRepository
import com.depromeet.threedays.front.web.request.habit.SearchHabitAchievementRequest
import org.mockito.Mockito
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.mock.mockito.MockBean
import spock.lang.Subject

import java.time.LocalDate

class SearchHabitAchievementUseCaseSpec extends IntegrationTestSpecification {

    @Subject
    @Autowired
    private SearchHabitAchievementUseCase searchUseCase

    @Autowired
    HabitDataInitializer habitDataInitializer

    @Autowired
    HabitAchievementDataInitializer dataInitializer

    @MockBean
    private MemberRepository memberRepository

    def setup() {
        habitDataInitializer.initialize()
        dataInitializer.initialize(habitDataInitializer.data.first().id)
        Mockito.when(
                memberRepository.findByIdAndStatus(Mockito.any(Long.class), Mockito.any(MemberStatus.class))
        ).thenReturn(
                Optional.of(MemberEntity.builder().build())
        )
    }

    def "사용자는 자신의 전체 습관 성취 기록을 조회할 수 있다"() {
        given:
        def criterionHabit = habitDataInitializer.data.first()

        when:
        def actual = searchUseCase.execute(criterionHabit.id, SearchHabitAchievementRequest.builder().build())

        then:
        actual.size() > 0
    }

    def "사용자는 날짜 조건으로 자신의 습관 성취 기록을 조회할 수 있다"() {
        given:
        def criterionHabit = habitDataInitializer.data.first()
        def criterionDatePeriod = new DatePeriod(LocalDate.now().minusDays(8), LocalDate.now())


        when:
        def actual = searchUseCase.execute(criterionHabit.id, SearchHabitAchievementRequest.builder()
                .datePeriod(criterionDatePeriod)
                .build())

        then:
        actual.size() > 0
        actual.get(0).achievementDate.isEqual(criterionDatePeriod.getTo()) || actual.get(0).achievementDate.isAfter(criterionDatePeriod.getFrom())
        actual.get(0).achievementDate.isEqual(criterionDatePeriod.getTo()) || actual.get(0).achievementDate.isBefore(criterionDatePeriod.getTo())
    }
}
