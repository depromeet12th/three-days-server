package com.depromeet.threedays.front.domain.usecase.oauth;

import com.depromeet.threedays.data.entity.member.MemberEntity;
import com.depromeet.threedays.front.client.OAuthClient;
import com.depromeet.threedays.front.client.OAuthManager;
import com.depromeet.threedays.front.client.model.OAuthInfo;
import com.depromeet.threedays.front.client.property.OAuthProperty;
import com.depromeet.threedays.front.controller.command.oauth.OAuthCommand;
import com.depromeet.threedays.front.domain.converter.member.MemberConverter;
import com.depromeet.threedays.front.domain.model.Member;
import com.depromeet.threedays.front.domain.model.Token;
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
		OAuthClient client = oAuthManager.getOAuthClient(command.getCertificationSubject());
		OAuthProperty property = oAuthManager.getOAuthProperty(command.getCertificationSubject());
		OAuthInfo info =
				client.readOAuthUserData(
						property, new Token(command.getAccessToken(), command.getIdToken()));

		return MemberConverter.from(memberRepository.findByName(info.getName()).orElse(join(info)));
	}

	public MemberEntity join(OAuthInfo info) {
		return memberRepository.save(MemberConverter.to(info));
	}
}
