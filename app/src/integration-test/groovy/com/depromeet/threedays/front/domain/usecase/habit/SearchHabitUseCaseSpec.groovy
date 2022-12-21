package com.depromeet.threedays.front.domain.usecase.habit

import com.depromeet.threedays.data.entity.habit.HabitEntity
import com.depromeet.threedays.data.entity.mate.MateEntity
import com.depromeet.threedays.data.enums.HabitStatus
import com.depromeet.threedays.data.enums.MateStatus
import com.depromeet.threedays.data.enums.MateType
import com.depromeet.threedays.front.IntegrationTestSpecification
import com.depromeet.threedays.front.data.habit.HabitDataInitializer
import com.depromeet.threedays.front.persistence.repository.habit.HabitRepository
import com.depromeet.threedays.front.persistence.repository.mate.MateRepository
import com.depromeet.threedays.front.web.request.habit.SearchHabitRequest
import net.bytebuddy.utility.RandomString
import org.springframework.beans.factory.annotation.Autowired
import spock.lang.Subject

import java.time.DayOfWeek
import java.time.LocalDateTime

class SearchHabitUseCaseSpec extends IntegrationTestSpecification {

    @Subject
    @Autowired
    private SearchHabitUseCase searchUseCase

    @Autowired
    private HabitDataInitializer dataInitializer

    @Autowired
    private HabitRepository repository

    @Autowired
    private MateRepository mateRepository

    def setup() {
        dataInitializer.initialize()
    }

    def "사용자는 자신의 습관 목록을 조회할 수 있다."() {
        when:
        def actual = searchUseCase.execute(SearchHabitRequest.builder()
                .status(HabitStatus.ACTIVE)
                .build())

        then:
        actual.size() == 10
    }

    def "습관 상태를 제외하고 요청을 보냈을때 완전삭제되지 않은 전체 습관 목록을 조회한다."() {
        when:
        def actual = searchUseCase.execute(SearchHabitRequest.builder()
                .build())

        then:
        actual.size() == 10
    }

    def "사용자가 현재 진행중인 습관 목록을 조회할 때, 현재 연결되어있는 짝꿍을 조회한다."() {
        when:
        def actual = searchUseCase.execute(SearchHabitRequest.builder().status(HabitStatus.ACTIVE).build())

        then:
        actual.first().mate.status == MateStatus.ACTIVE
    }

    def "사용자가 현재 보관함에 있는 습관 목록을 조회할 때, 현재 보관함에 있는 짝꿍을 조회한다."() {
        given:
        repository.deleteAll()
        mateRepository.deleteAll()

        EnumSet<DayOfWeek> dayOfWeeks = EnumSet.of(DayOfWeek.MONDAY, DayOfWeek.TUESDAY,
                DayOfWeek.SUNDAY);

        def criterionHabit = repository.save(HabitEntity.builder()
                .imojiPath(RandomString.make())
                .memberId(0L)
                .title(RandomString.make())
                .dayOfWeeks(dayOfWeeks)
                .archiveNumberOfDate(0)
                .color(RandomString.make())
                .status(HabitStatus.ARCHIVED)
                .createAt(LocalDateTime.now())
                .updateAt(LocalDateTime.now())
                .deleted(false)
                .build())

        mateRepository.save(MateEntity.builder()
                .memberId(0L)
                .title(RandomString.make())
                .habitId(criterionHabit.id)
                .level(3)
                .levelUpAt(LocalDateTime.now())
                .characterType(MateType.CARROT)
                .createAt(LocalDateTime.now())
                .updateAt(LocalDateTime.now())
                .deleted(false)
                .status(MateStatus.ARCHIVED)
                .build())

        when:
        def actual = searchUseCase.execute(SearchHabitRequest.builder().status(HabitStatus.ARCHIVED).build())

        then:
        actual.first().mate.status == MateStatus.ARCHIVED
    }
}
