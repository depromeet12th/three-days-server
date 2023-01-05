package com.depromeet.threedays.front.domain.usecase.member;

import com.depromeet.threedays.data.entity.member.MemberEntity;
import com.depromeet.threedays.data.enums.MemberStatus;
import com.depromeet.threedays.front.config.security.AuditorHolder;
import com.depromeet.threedays.front.domain.converter.member.MemberConverter;
import com.depromeet.threedays.front.domain.model.member.Member;
import com.depromeet.threedays.front.exception.ResourceNotFoundException;
import com.depromeet.threedays.front.persistence.repository.member.MemberRepository;
import com.depromeet.threedays.front.support.converter.MemberInfoJsonConverter;
import com.depromeet.threedays.front.web.request.member.MemberUpdateRequest;
import com.google.gson.JsonElement;
import java.util.Map;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UpdateMemberUseCase {

	private final MemberRepository repository;

	public Member execute(final MemberUpdateRequest request) {

		return MemberConverter.from(this.update(AuditorHolder.get(), request));
	}

	private MemberEntity update(final Long memberId, final MemberUpdateRequest request) {
		return repository
				.findByIdAndStatus(memberId, MemberStatus.REGULAR)
				.map(
						it ->
								it.update(
										request.getName(),
										request.isNotificationConsent(),
										this.parseSource(it.getResource(), request.getResource())))
				.orElseThrow(() -> new ResourceNotFoundException("member.not.found"));
	}

	private String parseSource(final String resource, final JSONObject newResource) {
		JSONObject asis = MemberInfoJsonConverter.from(resource);

		Set<Map.Entry<String, JsonElement>> element = newResource.entrySet();
		for (Map.Entry<String, JsonElement> entry : element) {
			asis.put(entry.getKey(), entry.getValue());
		}

		return asis.toJSONString();
	}
}
