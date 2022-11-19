package com.depromeet.threedays.front.domain.usecase

import com.depromeet.threedays.front.IntegrationTestSpecification
import com.depromeet.threedays.front.data.habit.HabitAchievementDataInitializer
import com.depromeet.threedays.front.data.habit.HabitDataInitializer
import com.depromeet.threedays.front.domain.usecase.habit.DeleteHabitAchievementUseCase
import com.depromeet.threedays.front.exception.PolicyViolationException
import org.springframework.beans.factory.annotation.Autowired
import spock.lang.Subject

class DeleteHabitAchievementUseCaseSpec extends IntegrationTestSpecification {

    @Subject
    @Autowired
    private DeleteHabitAchievementUseCase deleteUseCase

    @Autowired
    private HabitDataInitializer habitDataInitializer

    @Autowired
    private HabitAchievementDataInitializer dataInitializer

    def setup() {
        habitDataInitializer.initialize()
        dataInitializer.initialize(habitDataInitializer.data.first().id)
    }

    def "사용자는 오늘의 습관 달성 여부를 취소할 수 있다"() {
        given:
        def criterionHabit = habitDataInitializer.data.first()
        def criterionHabitAchievement = dataInitializer.data.first()

        when:
        def actual = deleteUseCase.execute(criterionHabit.id, criterionHabitAchievement.id)

        then:
        noExceptionThrown()
        actual.reward == dataInitializer.DEFAULT_DATA_SIZE / 3 - 1

    }

    def "사용자는 오늘의 습관 달성 여부 취소는 멱등하게 동작한다"() {
        given:
        def criterionHabit = habitDataInitializer.data.first()
        def criterionHabitAchievement = dataInitializer.data.first()

        when:
        deleteUseCase.execute(criterionHabit.id, criterionHabitAchievement.id)
        deleteUseCase.execute(criterionHabit.id, criterionHabitAchievement.id)

        then:
        noExceptionThrown()

    }

    def "사용자는 오늘의 습관 달성 여부가 아니면 취소할 수 없다"() {
        given:
        def criterionHabit = habitDataInitializer.data.first()
        def criterionHabitAchievement = dataInitializer.data[2]

        when:
        deleteUseCase.execute(criterionHabit.id, criterionHabitAchievement.id)

        then:
        thrown(PolicyViolationException.class)
    }
}
