package com.depromeet.threedays.front.domain.usecase.member;

import com.depromeet.threedays.data.enums.CertificationSubject;
import com.depromeet.threedays.front.client.AuthClient;
import com.depromeet.threedays.front.client.model.AppleMemberInfo;
import com.depromeet.threedays.front.client.model.AppleTokenInfo;
import com.depromeet.threedays.front.client.model.KeyProperties;
import com.depromeet.threedays.front.client.model.MemberInfo;
import com.depromeet.threedays.front.client.property.auth.AppleAuthProperty;
import com.depromeet.threedays.front.client.property.auth.AuthRequestProperty;
import com.depromeet.threedays.front.config.security.filter.token.TokenGenerator;
import com.depromeet.threedays.front.config.security.filter.token.TokenResolver;
import com.depromeet.threedays.front.domain.converter.member.AppleAuthRequestWithCodeConverter;
import com.depromeet.threedays.front.domain.converter.member.MemberCommandConverter;
import com.depromeet.threedays.front.domain.converter.member.MemberQueryConverter;
import com.depromeet.threedays.front.domain.model.member.SaveMemberUseCaseResponse;
import com.depromeet.threedays.front.domain.validation.TokenAuthenticator;
import com.depromeet.threedays.front.exception.ExternalIntegrationException;
import com.depromeet.threedays.front.exception.PolicyViolationException;
import com.depromeet.threedays.front.support.RequestBodyGenerator;
import com.depromeet.threedays.front.web.request.member.AppleSignMemberRequest;
import com.depromeet.threedays.front.web.request.member.SignMemberRequest;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Collection;
import java.util.Map;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class SignMemberUseCaseFacade {

	private final Map<String, AuthRequestProperty> authRequestPropertyMap;

	private final GetMemberUseCase getUseCase;
	private final SaveMemberUseCase saveUseCase;
	private final AuthClient authClient;
	private final TokenAuthenticator tokenAuthenticator;
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

		AppleMemberInfo info = getInfo(request);

		SaveMemberUseCaseResponse member =
				getUseCase.execute(MemberQueryConverter.from(info.getId(), request));

		if (member == null) {
			return saveUseCase.execute(MemberCommandConverter.from(info, request));
		}

		return member;
	}

	private AppleMemberInfo getInfo(AppleSignMemberRequest request) {
		AppleMemberInfoProperty property = getAppleMemberInfoProperty(request);

		return AppleMemberInfo.builder()
				.id(property.getId())
				.name(property.getName())
				.refreshToken(property.getRefreshToken())
				.build();
	}

	@Getter
	@NoArgsConstructor
	@AllArgsConstructor
	@Builder(toBuilder = true)
	private static class AppleMemberInfoProperty {
		private String id;
		private String name;
		private String refreshToken;
	}

	private AppleMemberInfoProperty getAppleMemberInfoProperty(AppleSignMemberRequest request) {
		AppleAuthProperty property = getAppleProperty(request.getCertificationSubject());
		KeyProperties keyProperties = getKeyProperties(property);
		tokenAuthenticator.authenticateIdToken(property, keyProperties, request);

		AppleTokenInfo token = getToken(property, request.getCode());

		return AppleMemberInfoProperty.builder()
				.id(tokenResolver.extractSubByToken(token.getIdToken()))
				.name(request.getName())
				.refreshToken(token.getRefreshToken())
				.build();
	}

	private AppleAuthProperty getAppleProperty(CertificationSubject subject) {
		if (subject != CertificationSubject.APPLE) {
			throw new PolicyViolationException("social.login.error");
		}
		return (AppleAuthProperty) getMemberProperty(subject);
	}

	private KeyProperties getKeyProperties(AppleAuthProperty property) {
		try {
			return authClient.getKeyProperties(new URI(property.getHost() + property.getKeyURI()));
		} catch (URISyntaxException e) {
			throw new ExternalIntegrationException("social.login.error");
		}
	}

	private AppleTokenInfo getToken(AppleAuthProperty property, String code) {
		String clientSecret = tokenGenerator.generateClientSecret(property);

		Map<String, String> body =
				RequestBodyGenerator.generateAppleAuthRequestBody(
						AppleAuthRequestWithCodeConverter.from(property.getServiceId(), clientSecret, code));
		try {
			return authClient.getAppleTokenInfo(new URI(property.getHost() + property.getUri()), body);
		} catch (URISyntaxException e) {
			throw new ExternalIntegrationException("social.login.error");
		}
	}
}
