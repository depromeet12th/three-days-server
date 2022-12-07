package com.depromeet.threedays.front.domain.usecase.member;

import com.depromeet.threedays.data.entity.member.MemberEntity;
import com.depromeet.threedays.front.config.security.AuditorHolder;
import com.depromeet.threedays.front.persistence.repository.RewardHistoryRepository;
import com.depromeet.threedays.front.persistence.repository.client.ClientRepository;
import com.depromeet.threedays.front.persistence.repository.habit.HabitAchievementRepository;
import com.depromeet.threedays.front.persistence.repository.habit.HabitRepository;
import com.depromeet.threedays.front.persistence.repository.mate.MateRepository;
import com.depromeet.threedays.front.persistence.repository.member.MemberRepository;
import com.depromeet.threedays.front.persistence.repository.notification.HabitNotificationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@RequiredArgsConstructor
@Service
public class DeleteMemberUseCase {

	private final MemberRepository memberRepository;
	private final ClientRepository clientRepository;
	private final HabitRepository habitRepository;
	private final HabitNotificationRepository habitNotificationRepository;
	private final RewardHistoryRepository rewardHistoryRepository;
	private final HabitAchievementRepository habitAchievementRepository;
	private final MateRepository mateRepository;

	public void execute() {
		Long memberId = AuditorHolder.get();

		MemberEntity source = memberRepository.findById(memberId).orElse(null);
		if (source == null) {
			return;
		}

		mateRepository.deleteAllByMemberId(source.getId());

		habitAchievementRepository.deleteAllByMemberId(source.getId());
		rewardHistoryRepository.deleteAllByMemberId(source.getId());
		habitNotificationRepository.deleteAllByMemberId(source.getId());
		habitRepository.deleteAllByMemberId(source.getId());

		clientRepository.deleteAllByMemberId(source.getId());

		memberRepository.deleteById(source.getId());
	}
}
