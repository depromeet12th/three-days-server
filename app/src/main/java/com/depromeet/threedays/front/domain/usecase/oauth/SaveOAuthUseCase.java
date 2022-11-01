package com.depromeet.threedays.front.domain.usecase.oauth;

import com.depromeet.threedays.data.entity.member.MemberEntity;
import com.depromeet.threedays.data.entity.member.certification.CertificationSubject;
import com.depromeet.threedays.front.client.OAuthFeignClient;
import com.depromeet.threedays.front.client.model.OAuthInfo;
import com.depromeet.threedays.front.client.property.OAuthProperty;
import com.depromeet.threedays.front.controller.command.oauth.OAuthCommand;
import com.depromeet.threedays.front.domain.converter.member.MemberConverter;
import com.depromeet.threedays.front.domain.model.Member;
import com.depromeet.threedays.front.exception.PolicyViolationException;
import com.depromeet.threedays.front.repository.MemberRepository;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Collection;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@RequiredArgsConstructor
@Service
public class SaveOAuthUseCase {

	private final Map<String, OAuthProperty> authPropertyMap;
	private final MemberRepository memberRepository;
	private final OAuthFeignClient oAuthFeignClient;

	private static final String TOKEN_TYPE = "Bearer ";

	public Member execute(OAuthCommand command) {
		OAuthInfo info = getInfo(command.getCertificationSubject(), command.getAccessToken());
		return MemberConverter.from(
				memberRepository
						.findByCertification(info.getId(), command.getCertificationSubject())
						.orElseGet(() -> join(info, command)));
	}

	public MemberEntity join(OAuthInfo info, OAuthCommand command) {
		return memberRepository.save(MemberConverter.to(info, command));
	}

	public OAuthInfo getInfo(CertificationSubject subject, String accessToken) {
		OAuthProperty property = getOAuthProperty(subject);
		try {
			String token = TOKEN_TYPE + accessToken;
			OAuthInfo info = oAuthFeignClient.getInfo(new URI(property.getUserUri()), token);
			return info;
		} catch (URISyntaxException e) {
			throw new PolicyViolationException("4000", e.getCause());
		}
	}

	public OAuthProperty getOAuthProperty(CertificationSubject subject) {
		Collection<String> oAuthProperties = authPropertyMap.keySet();
		String clientName =
				oAuthProperties.stream()
						.filter((k) -> k.contains(subject.name().toLowerCase()))
						.findFirst()
						.orElseThrow(() -> new NoSuchFieldError());
		return authPropertyMap.get(clientName);
	}
}
