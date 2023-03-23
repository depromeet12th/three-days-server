package com.depromeet.threedays.front.domain.usecase.member;

import com.depromeet.threedays.data.enums.CertificationSubject;
import com.depromeet.threedays.front.client.AuthClient;
import com.depromeet.threedays.front.client.model.AppleTokenInfo;
import com.depromeet.threedays.front.client.model.MemberInfo;
import com.depromeet.threedays.front.client.property.auth.AppleAuthProperty;
import com.depromeet.threedays.front.client.property.auth.AuthRequestProperty;
import com.depromeet.threedays.front.config.security.filter.token.TokenResolver;
import com.depromeet.threedays.front.domain.converter.member.MemberCommandConverter;
import com.depromeet.threedays.front.domain.converter.member.MemberQueryConverter;
import com.depromeet.threedays.front.domain.model.member.SaveMemberUseCaseResponse;
import com.depromeet.threedays.front.exception.ExternalIntegrationException;
import com.depromeet.threedays.front.support.RequestBodyGenerator;
import com.depromeet.threedays.front.support.TokenGenerator;
import com.depromeet.threedays.front.support.TokenValidator;
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
	private final TokenValidator tokenValidator;
	private final TokenGenerator tokenGenerator;
	private final TokenResolver tokenResolver;

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

		AppleAuthProperty property = getAppleProperty(request.getCertificationSubject());
		if (property == null) {
			return null;
		}

		MemberInfo info = getInfo(property, request);

		SaveMemberUseCaseResponse member =
			getUseCase.execute(MemberQueryConverter.from(info.getId(), request));

		if (member == null) {
			return saveUseCase.execute(MemberCommandConverter.from(info, request));
		}

		return member;
	}

	private AppleAuthProperty getAppleProperty(CertificationSubject subject) {
		if (subject != CertificationSubject.APPLE) {
			return null;
		}
		return (AppleAuthProperty) getMemberProperty(subject);
	}

	private MemberInfo getInfo(AppleAuthProperty property, AppleSignMemberRequest request) {
		tokenValidator.validateIdToken(property, request);
		AppleTokenInfo tokenInfo = getToken(property, request.getCode());
		String certificationId = tokenResolver.extractSubByToken(tokenInfo.getIdToken());
		return MemberInfo.of(certificationId, request.getName());
	}

	private AppleTokenInfo getToken(AppleAuthProperty property, String code) {
		String clientSecret = tokenGenerator.generateClientSecret(property);

		Map<String, String> body =
			RequestBodyGenerator.generateAppleAuthRequestBody(
				property.getClientId(), code, clientSecret);
		try {
			return authClient.getAppleTokenInfo(new URI(property.getHost() + property.getUri()), body);
		} catch (URISyntaxException e) {
			throw new ExternalIntegrationException("social.login.error");
		}
	}
}