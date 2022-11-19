package com.depromeet.threedays.front.domain.usecase

import com.depromeet.threedays.front.IntegrationTestSpecification
import com.depromeet.threedays.front.data.habit.HabitAchievementDataInitializer
import com.depromeet.threedays.front.data.habit.HabitDataInitializer
import com.depromeet.threedays.front.domain.usecase.habit.DeleteHabitAchievementUseCase
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

    def "사용자는 오늘 달성한 목표를 취소할 수 있다"() {
        given:
        def criterionHabit = habitDataInitializer.data.first()
        def criterionHabitAchievement = dataInitializer.data.first()

        when:
        deleteUseCase.execute(criterionHabit.id, criterionHabitAchievement.id)

        then:
        noExceptionThrown()

    }

    def "사용자는 오늘 달성한 목표가 아니면 취소할 수 없다"() {

    }
}
