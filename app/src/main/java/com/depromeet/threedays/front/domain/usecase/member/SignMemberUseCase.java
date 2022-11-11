package com.depromeet.threedays.front.domain.usecase.member;

import com.depromeet.threedays.data.entity.member.MemberEntity;
import com.depromeet.threedays.data.enums.CertificationSubject;
import com.depromeet.threedays.front.client.AuthClient;
import com.depromeet.threedays.front.client.model.MemberInfo;
import com.depromeet.threedays.front.client.property.AuthRequestProperty;
import com.depromeet.threedays.front.controller.command.oauth.SignMemberRequest;
import com.depromeet.threedays.front.domain.converter.member.MemberConverter;
import com.depromeet.threedays.front.domain.model.Member;
import com.depromeet.threedays.front.exception.ExternalIntegrationException;
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
public class SignMemberUseCase {

	private final Map<String, AuthRequestProperty> authRequestPropertyMap;
	private final MemberRepository memberRepository;
	private final AuthClient authClient;

	public Member execute(SignMemberRequest request) {
		if (request == null) {
			return null;
		}

		MemberInfo info = getInfo(request.getCertificationSubject(), request.getAccessToken());

		MemberEntity entity =
				memberRepository
						.findByIdAndAndCertificationSubject(
								Long.parseLong(info.getId()), request.getCertificationSubject())
						.orElse(null);

		if (entity == null) {
			// TODO: fcmToken 저장 로직 추가
			return MemberConverter.from(memberRepository.save(MemberConverter.to(info, request)), true);
		}

		// TODO: fcmToken 조회 로직 추가

		return MemberConverter.from(entity, false);
	}

	public MemberInfo getInfo(CertificationSubject subject, String accessToken) {
		try {
			AuthRequestProperty property = getMemberProperty(subject);
			final String bearerToken = "Bearer " + accessToken;

			return authClient.getInfo(new URI(property.getUri()), bearerToken);
		} catch (URISyntaxException e) {
			throw new ExternalIntegrationException("social.login.error");
		}
	}

	public AuthRequestProperty getMemberProperty(CertificationSubject subject) {
		Collection<String> authRequestProperties = authRequestPropertyMap.keySet();
		String clientName =
				authRequestProperties.stream()
						.filter(property -> property.contains(subject.name().toLowerCase()))
						.findFirst()
						.orElseThrow(NoSuchFieldError::new);
		return authRequestPropertyMap.get(clientName);
	}
}
