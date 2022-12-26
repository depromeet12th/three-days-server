package com.depromeet.threedays.front.domain.usecase.member;

import com.depromeet.threedays.data.entity.member.MemberEntity;
import com.depromeet.threedays.data.enums.CertificationSubject;
import com.depromeet.threedays.data.enums.MemberStatus;
import com.depromeet.threedays.front.client.AuthClient;
import com.depromeet.threedays.front.client.property.auth.AuthRequestProperty;
import com.depromeet.threedays.front.config.security.AuditorHolder;
import com.depromeet.threedays.front.domain.converter.member.MemberConverter;
import com.depromeet.threedays.front.domain.model.member.Member;
import com.depromeet.threedays.front.exception.ExternalIntegrationException;
import com.depromeet.threedays.front.exception.ResourceNotFoundException;
import com.depromeet.threedays.front.persistence.repository.member.MemberRepository;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@RequiredArgsConstructor
@Service
public class DeleteMemberUseCase {

	private final MemberRepository memberRepository;
	private final AuthClient authClient;
	private final Map<String, AuthRequestProperty> authRequestPropertyMap;

	public Member execute() {
		Long memberId = AuditorHolder.get();
		Member member = quit(memberId);
		unlinkSocialAccount(memberId);
		return member;
	}

	public Member quit(Long memberId) {
		return memberRepository
				.findByIdAndStatus(memberId, MemberStatus.REGULAR)
				.map(MemberEntity::withdraw)
				.map(MemberConverter::from)
				.orElseThrow(ResourceNotFoundException::new);
	}

	public void unlinkSocialAccount(Long memberId) {
		MemberEntity member =
				memberRepository.findById(memberId).orElseThrow(ResourceNotFoundException::new);
		if (CertificationSubject.KAKAO.equals(member.getCertificationSubject())) {
			try {
				AuthRequestProperty property = getMemberProperty(member.getCertificationSubject());

				Map<String, Object> form = new HashMap<>();
				form.put("target_id_type", "user_id");
				form.put("target_id", Long.parseLong(member.getCertificationId()));
				String adminKey = "KakaoAK " + property.getAdminKey();
				authClient.unlink(
						new URI(property.getHost() + property.getUnlink()), adminKey, form);
			} catch (URISyntaxException e) {
				throw new ExternalIntegrationException("social.login.error");
			}
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
