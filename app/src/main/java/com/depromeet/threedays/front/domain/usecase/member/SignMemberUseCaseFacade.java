package com.depromeet.threedays.front.domain.usecase.member;

import com.depromeet.threedays.data.enums.CertificationSubject;
import com.depromeet.threedays.front.client.AuthClient;
import com.depromeet.threedays.front.client.model.MemberInfo;
import com.depromeet.threedays.front.client.property.auth.AuthRequestProperty;
import com.depromeet.threedays.front.domain.converter.member.MemberCommandConverter;
import com.depromeet.threedays.front.domain.converter.member.MemberQueryConverter;
import com.depromeet.threedays.front.domain.model.member.SaveMemberUseCaseResponse;
import com.depromeet.threedays.front.exception.ExternalIntegrationException;
import com.depromeet.threedays.front.web.request.member.AppleSignMemberRequest;
import com.depromeet.threedays.front.web.request.member.SignMemberRequest;
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

	public SaveMemberUseCaseResponse execute(final SignMemberRequest request) {
		if (request == null) {
			return null;
		}

		MemberInfo info = getInfo(request.getCertificationSubject(), request.getSocialToken());

		SaveMemberUseCaseResponse member =
				getUseCase.execute(MemberQueryConverter.from(info.getId(), request));

		if (member == null) {
			return saveUseCase.execute(MemberCommandConverter.from(info, request));
		}

		return member;
	}

	public MemberInfo getInfo(CertificationSubject subject, String oAuthToken) {
		try {
			AuthRequestProperty property = getMemberProperty(subject);
			final String bearerToken = "Bearer " + oAuthToken;
			return authClient.getInfo(new URI(property.getHost() + property.getUri()), bearerToken);
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

	public SaveMemberUseCaseResponse execute(final AppleSignMemberRequest request) {
		if (request == null) {
			return null;
		}

		return null;
	}
}
