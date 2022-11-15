package com.depromeet.threedays.front.domain.usecase

import com.depromeet.threedays.front.IntegrationTestSpecification
import com.depromeet.threedays.front.data.objectvie.ObjectiveDataInitializer
import com.depromeet.threedays.front.domain.usecase.objective.SearchObjectiveUseCase
import org.springframework.beans.factory.annotation.Autowired
import spock.lang.Subject

class SearchObjectiveUseCaseSpec extends IntegrationTestSpecification {

    @Subject
    @Autowired
    private SearchObjectiveUseCase searchUseCase

    @Autowired
    private ObjectiveDataInitializer dataInitialize

    def setup() {
        dataInitialize.initialize()
    }

    def "사용자는 자신의 목표 목록을 조회할 수 있다."() {
        when:
        def actual = searchUseCase.execuete()

        then:
        actual.size() == 10
    }


}
