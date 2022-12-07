package com.depromeet.threedays.front.domain.usecase.member;

import com.depromeet.threedays.data.entity.member.MemberEntity;
import com.depromeet.threedays.front.config.security.AuditorHolder;
import com.depromeet.threedays.front.domain.converter.member.MemberConverter;
import com.depromeet.threedays.front.domain.model.member.Member;
import com.depromeet.threedays.front.exception.ResourceNotFoundException;
import com.depromeet.threedays.front.persistence.repository.member.MemberRepository;
import com.depromeet.threedays.front.support.converter.MemberInfoJsonConverter;
import com.depromeet.threedays.front.web.request.member.MemberResourceUpdateRequest;
import com.google.gson.JsonElement;
import java.util.Map;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@RequiredArgsConstructor
@Service
public class SaveResourceUseCase {

	private final MemberRepository memberRepository;

	public Member execute(final MemberResourceUpdateRequest request) {
		Long memberId = AuditorHolder.get();
		return MemberConverter.from(this.updateResource(memberId, request));
	}

	private MemberEntity updateResource(
			final Long memberId, final MemberResourceUpdateRequest request) {
		MemberEntity member =
				memberRepository
						.findById(memberId)
						.orElseThrow(() -> new ResourceNotFoundException("member.not.found"));
		member.updateResource(updateResource(member.getResource(), request.getResource()));
		return member;
	}

	private String updateResource(String resource, JSONObject newResource) {
		JSONObject asis = MemberInfoJsonConverter.from(resource);

		Set<Map.Entry<String, JsonElement>> element = newResource.entrySet();
		for (Map.Entry<String, JsonElement> entry : element) {
			asis.put(entry.getKey(), entry.getValue());
		}

		return asis.toJSONString();
	}
}
