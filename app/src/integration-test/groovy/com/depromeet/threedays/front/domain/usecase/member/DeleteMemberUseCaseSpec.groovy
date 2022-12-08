package com.depromeet.threedays.front.domain.usecase.member


import com.depromeet.threedays.front.IntegrationTestSpecification
import com.depromeet.threedays.front.data.habit.HabitDataInitializer
import com.depromeet.threedays.front.data.member.MemberInitializer
import com.depromeet.threedays.front.persistence.repository.RewardHistoryRepository
import com.depromeet.threedays.front.persistence.repository.client.ClientRepository
import com.depromeet.threedays.front.persistence.repository.habit.HabitAchievementRepository
import com.depromeet.threedays.front.persistence.repository.habit.HabitRepository
import com.depromeet.threedays.front.persistence.repository.mate.MateRepository
import com.depromeet.threedays.front.persistence.repository.member.MemberRepository
import com.depromeet.threedays.front.persistence.repository.notification.HabitNotificationRepository
import org.springframework.beans.factory.annotation.Autowired

class DeleteMemberUseCaseSpec extends IntegrationTestSpecification {


    DeleteMemberUseCase useCase
    MemberRepository memberRepository

    @Autowired
    MemberInitializer initializer
    @Autowired
    HabitDataInitializer habitDataInitializer


    ClientRepository clientRepository
    HabitRepository habitRepository
    HabitNotificationRepository habitNotificationRepository
    RewardHistoryRepository rewardHistoryRepository
    HabitAchievementRepository habitAchievementRepository
    MateRepository mateRepository

    def setup() {
        initializer.initialize()
        habitDataInitializer.initialize()


        memberRepository = Mock(MemberRepository.class)
        clientRepository = Mock(ClientRepository.class)
        habitRepository = Mock(HabitRepository.class)
        habitNotificationRepository = Mock(HabitNotificationRepository)
        rewardHistoryRepository = Mock(RewardHistoryRepository.class)
        habitAchievementRepository = Mock(HabitAchievementRepository.class)
        mateRepository = Mock(MateRepository.class)

        useCase = new DeleteMemberUseCase(memberRepository, clientRepository,
                habitRepository, habitNotificationRepository, rewardHistoryRepository,
                habitAchievementRepository, mateRepository)

    }

    def "사용자를 삭제하면 습관이 삭제된다."() {
        setup:
        def criterionMember = initializer.data.first()
        habitDataInitializer.initialize()

        when:
        useCase.execute()
        habitRepository.findById(criterionMember.id as Long).get()

        then:
        memberRepository.findById(_ as Long) >> Optional.of(criterionMember)
        thrown(NullPointerException)
    }
}
