package com.depromeet.threedays.front.domain.usecase.member;

import com.depromeet.threedays.data.entity.member.MemberEntity;
import com.depromeet.threedays.front.config.security.AuditorHolder;
import com.depromeet.threedays.front.domain.converter.member.MemberConverter;
import com.depromeet.threedays.front.domain.model.member.Member;
import com.depromeet.threedays.front.exception.ResourceNotFoundException;
import com.depromeet.threedays.front.persistence.repository.member.MemberRepository;
import com.depromeet.threedays.front.web.request.member.UpdateNotificationConsentRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@RequiredArgsConstructor
@Service
public class SaveConsentUseCase {

	private final MemberRepository memberRepository;

	public Member execute(final UpdateNotificationConsentRequest request) {
		Long memberId = AuditorHolder.get();
		return MemberConverter.from(this.updateNotificationConsent(memberId, request));
	}

	public MemberEntity updateNotificationConsent(
			final Long memberId, final UpdateNotificationConsentRequest request) {
		MemberEntity member =
				memberRepository
						.findById(memberId)
						.orElseThrow(() -> new ResourceNotFoundException("member.not.found"));
		member.updateNotificationConsent(request.isOn());
		return member;
	}
}
