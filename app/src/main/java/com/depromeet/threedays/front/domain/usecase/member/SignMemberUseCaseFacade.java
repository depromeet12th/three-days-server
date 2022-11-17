package com.depromeet.threedays.front.domain.usecase.member;

import com.depromeet.threedays.data.enums.CertificationSubject;
import com.depromeet.threedays.front.client.AuthClient;
import com.depromeet.threedays.front.client.model.MemberInfo;
import com.depromeet.threedays.front.client.property.AuthRequestProperty;
import com.depromeet.threedays.front.controller.request.member.SignMemberRequest;
import com.depromeet.threedays.front.domain.converter.member.MemberCommandConverter;
import com.depromeet.threedays.front.domain.converter.member.MemberQueryConverter;
import com.depromeet.threedays.front.domain.model.member.Member;
import com.depromeet.threedays.front.exception.ExternalIntegrationException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Collection;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class SignMemberUseCaseFacade {

	private final Map<String, AuthRequestProperty> authRequestPropertyMap;

	private final GetMemberUseCase getUseCase;
	private final SaveMemberUseCase saveUseCase;
	private final AuthClient authClient;

	public Member execute(SignMemberRequest request) {
		if (request == null) {
			return null;
		}

		MemberInfo info = getInfo(request.getCertificationSubject(), request.getAccessToken());

		Member member =
				getUseCase.execute(MemberQueryConverter.from(Long.parseLong(info.getId()), request));

		if (member == null) {
			return saveUseCase.execute(MemberCommandConverter.from(info, request));
		}

		return member;
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