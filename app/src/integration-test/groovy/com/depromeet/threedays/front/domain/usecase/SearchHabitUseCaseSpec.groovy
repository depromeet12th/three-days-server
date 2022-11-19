package com.depromeet.threedays.front.domain.usecase

import com.depromeet.threedays.front.IntegrationTestSpecification
import com.depromeet.threedays.front.data.habit.HabitDataInitializer
import com.depromeet.threedays.front.domain.usecase.habit.SearchHabitUseCase
import org.springframework.beans.factory.annotation.Autowired
import spock.lang.Subject

class SearchHabitUseCaseSpec extends IntegrationTestSpecification {

    @Subject
    @Autowired
    private SearchHabitUseCase searchUseCase

    @Autowired
    private HabitDataInitializer dataInitialize

    def setup() {
        dataInitialize.initialize()
    }

    def "사용자는 자신의 습관 목록을 조회할 수 있다."() {
        when:
        def actual = searchUseCase.execute()

        then:
        actual.size() == 10
    }


}
