package com.depromeet.threedays.front.domain.usecase.habit

import com.depromeet.threedays.data.enums.HabitStatus
import com.depromeet.threedays.front.IntegrationTestSpecification
import com.depromeet.threedays.front.data.habit.HabitDataInitializer
import com.depromeet.threedays.front.web.request.habit.SearchHabitRequest
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
}
