package com.depromeet.threedays.front.domain.usecase.member;

import com.depromeet.threedays.data.entity.client.ClientEntity;
import com.depromeet.threedays.data.entity.member.MemberEntity;
import com.depromeet.threedays.data.enums.CertificationSubject;
import com.depromeet.threedays.data.enums.MemberStatus;
import com.depromeet.threedays.front.client.AuthClient;
import com.depromeet.threedays.front.client.property.auth.AuthPropertyManager;
import com.depromeet.threedays.front.client.property.auth.AuthRequestProperty;
import com.depromeet.threedays.front.config.security.AuditorHolder;
import com.depromeet.threedays.front.domain.converter.member.MemberConverter;
import com.depromeet.threedays.front.domain.model.member.Member;
import com.depromeet.threedays.front.exception.ExternalIntegrationException;
import com.depromeet.threedays.front.exception.ResourceNotFoundException;
import com.depromeet.threedays.front.persistence.repository.client.ClientRepository;
import com.depromeet.threedays.front.persistence.repository.member.MemberRepository;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Transactional
@RequiredArgsConstructor
@Service
public class DeleteMemberUseCase {

	private final MemberRepository repository;
	private final ClientRepository clientRepository;
	private final AuthClient authClient;
	private final AuthPropertyManager propertyManager;

	public Member execute() {
		Long memberId = AuditorHolder.get();
		Member member = quit(memberId);
		deleteFcmToken(memberId);
		unlinkSocialAccount(memberId);
		return member;
	}

	/** 짝심삼일 앱에서 회원 탈퇴 */
	Member quit(Long memberId) {
		return repository
				.findByIdAndStatus(memberId, MemberStatus.REGULAR)
				.map(MemberEntity::withdraw)
				.map(MemberConverter::from)
				.orElseThrow(ResourceNotFoundException::new);
	}

	void deleteFcmToken(Long memberId) {
		ClientEntity clientEntity =
				clientRepository.findByMemberId(memberId).orElseThrow(ResourceNotFoundException::new);
		clientRepository.save(clientEntity.toBuilder().fcmToken("").build());
	}

	/** 카카오 계정 연결끊기 */
	private void unlinkSocialAccount(Long memberId) {
		MemberEntity memberEntity =
				repository.findById(memberId).orElseThrow(ResourceNotFoundException::new);
		if (CertificationSubject.KAKAO.equals(memberEntity.getCertificationSubject())) {
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
