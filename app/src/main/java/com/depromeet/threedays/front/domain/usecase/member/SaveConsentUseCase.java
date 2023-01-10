package com.depromeet.threedays.front.domain.usecase.member;

import com.depromeet.threedays.data.entity.member.MemberEntity;
import com.depromeet.threedays.data.enums.MemberStatus;
import com.depromeet.threedays.front.config.security.AuditorHolder;
import com.depromeet.threedays.front.domain.converter.member.MemberConverter;
import com.depromeet.threedays.front.domain.model.member.Member;
import com.depromeet.threedays.front.exception.MemberNotFoundException;
import com.depromeet.threedays.front.persistence.repository.member.MemberRepository;
import com.depromeet.threedays.front.web.request.member.MemberNotificationConsentUpdateRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@RequiredArgsConstructor
@Service
public class SaveConsentUseCase {

	private final MemberRepository memberRepository;

	public Member execute(final MemberNotificationConsentUpdateRequest request) {
		Long memberId = AuditorHolder.get();
		memberRepository
				.findByIdAndStatus(memberId, MemberStatus.REGULAR)
				.orElseThrow(() -> new MemberNotFoundException(memberId));
		return MemberConverter.from(updateNotificationConsent(memberId, request));
	}

	private MemberEntity updateNotificationConsent(
			final Long memberId, final MemberNotificationConsentUpdateRequest request) {
		return memberRepository
				.findByIdAndStatus(memberId, MemberStatus.REGULAR)
				.map(it -> it.updateNotificationConsent(request.isNotificationConsent()))
				.orElseThrow(() -> new MemberNotFoundException(memberId));
	}
}
