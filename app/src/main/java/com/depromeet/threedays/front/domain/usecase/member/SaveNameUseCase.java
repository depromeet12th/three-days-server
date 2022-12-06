package com.depromeet.threedays.front.domain.usecase.member;

import com.depromeet.threedays.data.entity.member.MemberEntity;
import com.depromeet.threedays.front.config.security.AuditorHolder;
import com.depromeet.threedays.front.domain.converter.member.MemberConverter;
import com.depromeet.threedays.front.domain.model.member.Member;
import com.depromeet.threedays.front.exception.ResourceNotFoundException;
import com.depromeet.threedays.front.persistence.repository.member.MemberRepository;
import com.depromeet.threedays.front.web.request.member.MemberNameUpdateRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@RequiredArgsConstructor
@Service
public class SaveNameUseCase {

	private final MemberRepository memberRepository;

	public Member execute(final MemberNameUpdateRequest request) {
		Long memberId = AuditorHolder.get();
		return MemberConverter.from(this.updateName(memberId, request), false, null);
	}

	public MemberEntity updateName(final Long memberId, final MemberNameUpdateRequest request) {
		MemberEntity member =
				memberRepository
						.findById(memberId)
						.orElseThrow(() -> new ResourceNotFoundException("member.not.found"));
		member.updateName(request.getName());
		return member;
	}
}
