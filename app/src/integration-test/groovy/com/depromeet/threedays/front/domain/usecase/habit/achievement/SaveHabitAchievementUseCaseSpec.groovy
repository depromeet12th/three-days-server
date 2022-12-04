package com.depromeet.threedays.front.domain.usecase.habit.achievement

import com.depromeet.threedays.data.entity.habit.HabitAchievementEntity
import com.depromeet.threedays.front.IntegrationTestSpecification
import com.depromeet.threedays.front.data.habit.HabitAchievementDataInitializer
import com.depromeet.threedays.front.data.habit.HabitDataInitializer
import com.depromeet.threedays.front.domain.usecase.habit.SaveHabitAchievementUseCase
import com.depromeet.threedays.front.exception.PolicyViolationException
import com.depromeet.threedays.front.web.request.habit.SaveHabitAchievementRequest
import org.springframework.beans.factory.annotation.Autowired
import spock.lang.Subject

import java.time.LocalDate

class SaveHabitAchievementUseCaseSpec extends IntegrationTestSpecification {

    @Subject
    @Autowired
    private SaveHabitAchievementUseCase saveUseCase

    @Autowired
    private HabitDataInitializer habitDataInitializer

    @Autowired
    private HabitAchievementDataInitializer dataInitializer

    def setup() {
        habitDataInitializer.initialize()
        dataInitializer.initialize(habitDataInitializer.data.first().id)
    }

    def "사용자는 오늘의 습관 달성 여부를 체크할 수 있다"() {
        given:
        def criterionHabit = habitDataInitializer.data.first()

        when:
        def actual = saveUseCase.execute(criterionHabit.id,
                SaveHabitAchievementRequest.builder()
                        .achievementDate(LocalDate.now())
                        .build())

        then:
        actual.habitAchievement.achievementDate == LocalDate.now()
    }

    def "사용자는 과거의 날짜로 습관 달성 여부를 체크할 수 없다"() {
        given:
        def criterionHabit = habitDataInitializer.data.first()

        when:
        saveUseCase.execute(criterionHabit.id,
                SaveHabitAchievementRequest.builder()
                        .achievementDate(LocalDate.now().minusDays(1))
                        .build())

        then:
        thrown(PolicyViolationException)
    }

    def "습관 달성 여부 체크는 멱등하게 동작한다"() {
        given:
        def criterionHabit = habitDataInitializer.data.first()

        when:
        def first = saveUseCase.execute(criterionHabit.id,
                SaveHabitAchievementRequest.builder()
                        .achievementDate(LocalDate.now())
                        .build())
        def second = saveUseCase.execute(criterionHabit.id,
                SaveHabitAchievementRequest.builder()
                        .achievementDate(LocalDate.now())
                        .build())

        then:
        first.habitAchievement.id == second.habitAchievement.id
    }

    def "습관 달성 여부를 목표 수행일 이후에 체크하는 경우 연속일 수가 1이 된다"() {
        given:
        def criterionHabit = habitDataInitializer.data.first()
        dataInitializer.setSpecificData(List.of(HabitAchievementEntity.builder()
                .memberId(0L)
                .habitId(criterionHabit.id)
                .achievementDate(LocalDate.now().minusDays(10))
                .nextAchievementDate(LocalDate.now().minusDays(3))
                .sequence(5)
                .build()))

        when:
        def actual = saveUseCase.execute(criterionHabit.id,
                SaveHabitAchievementRequest.builder()
                        .achievementDate(LocalDate.now())
                        .build())

        then:
        actual.habitAchievement.sequence == 1
    }

    def "습관 달성 여부가 정해진 조건을 달성할 경우, 박수를 받을 수 있다."() {
        given:
        def criterionHabit = habitDataInitializer.data.first()
        def pastAchievements = List.of(HabitAchievementEntity.builder()
                .memberId(0L)
                .habitId(criterionHabit.id)
                .achievementDate(LocalDate.now().minusDays(10))
                .nextAchievementDate(LocalDate.now().minusDays(3))
                .sequence(1)
                .build(),
                HabitAchievementEntity.builder()
                        .memberId(0L)
                        .habitId(criterionHabit.id)
                        .achievementDate(LocalDate.now().minusDays(9))
                        .nextAchievementDate(LocalDate.now())
                        .sequence(2)
                        .build())

        dataInitializer.setSpecificData(pastAchievements)

        when:
        def actual = saveUseCase.execute(criterionHabit.id,
                SaveHabitAchievementRequest.builder()
                        .achievementDate(LocalDate.now())
                        .build())

        then:
        actual.mate.level == 1
        actual.reward == 1

    }


}
