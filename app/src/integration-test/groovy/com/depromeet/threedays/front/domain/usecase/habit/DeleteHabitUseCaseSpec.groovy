package com.depromeet.threedays.front.domain.usecase.habit

import com.depromeet.threedays.data.entity.member.MemberEntity
import com.depromeet.threedays.data.enums.HabitStatus
import com.depromeet.threedays.data.enums.MateStatus
import com.depromeet.threedays.data.enums.MemberStatus
import com.depromeet.threedays.front.IntegrationTestSpecification
import com.depromeet.threedays.front.data.habit.HabitAchievementDataInitializer
import com.depromeet.threedays.front.data.habit.HabitDataInitializer
import com.depromeet.threedays.front.data.mate.MateDataInitializer
import com.depromeet.threedays.front.persistence.repository.habit.HabitRepository
import com.depromeet.threedays.front.persistence.repository.mate.MateRepository
import com.depromeet.threedays.front.persistence.repository.member.MemberRepository
import org.mockito.Mockito
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.mock.mockito.MockBean
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

    @MockBean
    private MemberRepository memberRepository

    def setup() {
        habitDataInitializer.initialize()
        Mockito.when(
                memberRepository.findByIdAndStatus(Mockito.any(Long.class), Mockito.any(MemberStatus.class))
        ).thenReturn(
                Optional.of(MemberEntity.builder().build())
        )
    }

    def "사용자가 짝꿍과 연결된 습관에 삭제를 요청한 경우, 습관과 짝꿍이 보관함으로 이동된다."() {
        given:
        def criterionHabit = habitDataInitializer.data.first()

        mateDataInitializer.initialize(criterionHabit.id)
        def criterion = mateDataInitializer.getData()

        when:
        deleteUseCase.execute(criterionHabit.id)
        def actual = repository.findById(criterionHabit.id).get()
        def actualMate = mateRepository.findById(criterion.id).get()

        then:
        actual.status == HabitStatus.ARCHIVED
        actualMate.status == MateStatus.ARCHIVED
    }

    def "사용자가 습관 달성 이력이 없는 습관에 삭제를 요청한 경우, 습관은 완전 삭제 된다."() {
        given:
        def habitData = habitDataInitializer.data[2]

        when:
        deleteUseCase.execute(habitData.id)
        def result = repository.findById(habitData.id).get()

        then:
        result.deleted
        result.status == HabitStatus.ACTIVE
    }

    def "사용자가 습관 달성 이력이 있고 활성화 상태에 있는 습관에 삭제를 요청을 한 경우, 습관은 보관함으로 이동한다."() {
        given:
        def habitData = habitDataInitializer.data.first()
        habitAchievementDataInitializer.initialize(habitData.id)

        when:
        deleteUseCase.execute(habitData.id)
        def result = repository.findById(habitData.id).get()

        then:
        result.status == HabitStatus.ARCHIVED
    }

    def "사용자가 이미 보관함에 있는 습관에 삭제를 요청한 경우, 습관은 완전 삭제 된다."() {
        given:
        def habitData = habitDataInitializer.data.first()
        repository.save(habitData.toBuilder().status(HabitStatus.ARCHIVED).build())

        when:
        deleteUseCase.execute(habitData.id)
        def result = repository.findById(habitData.id).get()

        then:
        result.deleted
    }
}
