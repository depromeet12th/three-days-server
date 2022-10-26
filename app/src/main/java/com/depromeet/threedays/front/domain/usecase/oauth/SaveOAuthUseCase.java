package com.depromeet.threedays.front.domain.usecase.oauth;

import com.depromeet.threedays.data.entity.member.MemberEntity;
import com.depromeet.threedays.front.client.OAuthManager;
import com.depromeet.threedays.front.client.model.OAuthInfo;
import com.depromeet.threedays.front.controller.command.oauth.OAuthCommand;
import com.depromeet.threedays.front.domain.converter.member.MemberConverter;
import com.depromeet.threedays.front.domain.model.Member;
import com.depromeet.threedays.front.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@RequiredArgsConstructor
@Service
public class SaveOAuthUseCase {

	private final OAuthManager oAuthManager;
	private final MemberRepository memberRepository;

	public Member execute(OAuthCommand command) {
		OAuthInfo info = oAuthManager.getOAuthInfo(command);

		return MemberConverter.from(
				memberRepository
						.findByCertification(info.getId(), command.getCertificationSubject())
						.orElseGet(() -> join(info, command)));
	}

	public MemberEntity join(OAuthInfo info, OAuthCommand command) {
		return memberRepository.save(MemberConverter.to(info, command));
	}
}
