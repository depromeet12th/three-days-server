package com.depromeet.threedays.front.domain.usecase.habit

import com.depromeet.threedays.data.entity.habit.HabitAchievementEntity
import com.depromeet.threedays.data.entity.habit.HabitEntity
import com.depromeet.threedays.data.enums.HabitStatus
import com.depromeet.threedays.front.IntegrationTestSpecification
import com.depromeet.threedays.front.data.habit.HabitAchievementDataInitializer
import com.depromeet.threedays.front.data.habit.HabitDataInitializer
import com.depromeet.threedays.front.data.mate.MateDataInitializer
import com.depromeet.threedays.front.persistence.repository.habit.HabitRepository
import com.depromeet.threedays.front.persistence.repository.mate.MateRepository
import org.springframework.beans.factory.annotation.Autowired
import spock.lang.Subject

class DeleteHabitUseCaseSpec extends IntegrationTestSpecification {

    @Subject
    @Autowired
    private DeleteHabitUseCase deleteUseCase

    @Autowired
    private HabitDataInitializer habitDataInitializer

    @Autowired
    private HabitAchievementDataInitializer habitAchievementDataInitializer

    @Autowired
    private MateDataInitializer mateDataInitializer

    @Autowired
    private HabitRepository repository

    @Autowired
    private MateRepository mateRepository

    def setup() {
        habitDataInitializer.initialize()
    }

    def "짝꿍과 연결되어 있는 습관에 삭제 요청이 들어왔을 때 Mate가 삭제되는지 테스트"() {
        given:
        def habitData = habitDataInitializer.data.first()
        mateDataInitializer.initialize(habitData.id)
        def mateData = mateDataInitializer.getData()

        when:
        deleteUseCase.execute(habitData.id)
        def result = mateRepository.findById(mateData.id).get()

        then:
        result.deleted == true
    }

    def "습관 달성 이력이 없는 습관에 삭제 요청이 들어왔을 때 습관이 완전 삭제 되는지 테스트"() {
        given:
        def habitData = habitDataInitializer.data.first()

        when:
        deleteUseCase.execute(habitData.id)
        def result = repository.findById(habitData.id).get()

        then:
        result.deleted == true
    }

    def "습관 달성 이력이 있는 습관에 삭제 요청이 들어왔을 때 습관이 보관함으로 이동되는지 테스트"() {
        given:
        def habitData = habitDataInitializer.data.first()
        habitAchievementDataInitializer.initialize(habitData.id)
        def habitAchievementData = habitAchievementDataInitializer.data.first()

        when:
        deleteUseCase.execute(habitData.id)
        def result = repository.findById(habitData.id).get()

        then:
        result.status == HabitStatus.ARCHIVED
    }
}
