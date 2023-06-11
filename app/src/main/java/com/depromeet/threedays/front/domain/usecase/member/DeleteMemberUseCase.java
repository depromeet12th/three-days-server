package com.depromeet.threedays.front.domain.usecase.member;

import com.depromeet.threedays.data.entity.member.MemberEntity;
import com.depromeet.threedays.data.enums.CertificationSubject;
import com.depromeet.threedays.data.enums.MemberStatus;
import com.depromeet.threedays.front.client.AuthClient;
import com.depromeet.threedays.front.client.property.auth.AppleAuthProperty;
import com.depromeet.threedays.front.client.property.auth.AuthPropertyManager;
import com.depromeet.threedays.front.client.property.auth.AuthRequestProperty;
import com.depromeet.threedays.front.config.security.AuditorHolder;
import com.depromeet.threedays.front.config.security.filter.token.TokenGenerator;
import com.depromeet.threedays.front.domain.converter.member.AppleAuthRevokeRequestConverter;
import com.depromeet.threedays.front.domain.converter.member.MemberConverter;
import com.depromeet.threedays.front.domain.model.member.Member;
import com.depromeet.threedays.front.domain.model.member.MemberEvent;
import com.depromeet.threedays.front.exception.ExternalIntegrationException;
import com.depromeet.threedays.front.exception.ResourceNotFoundException;
import com.depromeet.threedays.front.persistence.repository.member.MemberRepository;
import com.depromeet.threedays.front.support.RequestBodyGenerator;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Transactional
@RequiredArgsConstructor
@Service
public class DeleteMemberUseCase {

	private final MemberRepository repository;
	private final AuthClient authClient;
	private final AuthPropertyManager propertyManager;
	private final ApplicationEventPublisher eventPublisher;
	private final TokenGenerator tokenGenerator;

	public Member execute() {
		log.info("deleteMemberUseCase execute() thread name : " + Thread.currentThread().getName());
		Long memberId = AuditorHolder.get();
		unlinkSocialAccount(memberId);
		eventPublisher.publishEvent(new MemberEvent(this, memberId));
		return quit(memberId);
	}

	/** 짝심삼일 앱에서 회원 탈퇴 */
	Member quit(Long memberId) {
		return repository
				.findByIdAndStatus(memberId, MemberStatus.REGULAR)
				.map(MemberEntity::withdraw)
				.map(MemberConverter::from)
				.orElseThrow(ResourceNotFoundException::new);
	}

	/** 카카오 계정 연결끊기 */
	private void unlinkSocialAccount(Long memberId) {
		MemberEntity memberEntity =
				repository.findById(memberId).orElseThrow(ResourceNotFoundException::new);
		if (CertificationSubject.KAKAO.equals(memberEntity.getCertificationSubject())) {
			kakaoUnlink(memberEntity);
		} else if (CertificationSubject.APPLE.equals(memberEntity.getCertificationSubject())) {
			appleUnlik(memberEntity);
		}
	}

	private void kakaoUnlink(MemberEntity memberEntity) {
		try {
			AuthRequestProperty property =
					propertyManager.getMemberProperty(memberEntity.getCertificationSubject());

			Map<String, Object> form = new HashMap<>();
			form.put("target_id_type", "user_id");
			form.put("target_id", Long.parseLong(memberEntity.getCertificationId()));
			String adminKey = "KakaoAK " + property.getAdminKey();
			authClient.unlink(new URI(property.getHost() + property.getUnlink()), adminKey, form);
		} catch (URISyntaxException e) {
			throw new ExternalIntegrationException("social.login.error");
		}
	}

	private void appleUnlik(MemberEntity memberEntity) {
		AppleAuthProperty property =
				(AppleAuthProperty)
						propertyManager.getMemberProperty(memberEntity.getCertificationSubject());

		Map<String, String> form = getForm(property, memberEntity.getCertificationToken());

		try {
			authClient.unlink(new URI(property.getHost() + property.getUnlink()), form);
		} catch (URISyntaxException e) {
			throw new ExternalIntegrationException("social.login.error");
		}
	}

	private Map<String, String> getForm(AppleAuthProperty property, String token) {
		String clientSecret = tokenGenerator.generateClientSecret(property);
		return RequestBodyGenerator.generateAppleAuthRevokeRequestBody(
				AppleAuthRevokeRequestConverter.from(property.getServiceId(), clientSecret, token));
	}

	public Member executeCallback(CertificationSubject subject, String key, String userId) {
		AuthRequestProperty property = propertyManager.getMemberProperty(subject);
		if (key != null && key.equals(property.getAdminKey())) {
			return repository
					.findByCertificationIdAndCertificationSubjectAndStatus(
							userId, subject, MemberStatus.REGULAR)
					.map(MemberEntity::withdraw)
					.map(MemberConverter::from)
					.orElseThrow(ResourceNotFoundException::new);
		} else {
			throw new ResourceNotFoundException();
		}
	}
}
