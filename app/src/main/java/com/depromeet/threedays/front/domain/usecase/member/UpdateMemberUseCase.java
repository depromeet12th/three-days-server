package com.depromeet.threedays.front.domain.usecase.member;

import com.depromeet.threedays.data.entity.member.MemberEntity;
import com.depromeet.threedays.front.controller.request.member.UpdateNameRequest;
import com.depromeet.threedays.front.domain.converter.member.MemberConverter;
import com.depromeet.threedays.front.domain.model.member.Member;
import com.depromeet.threedays.front.exception.ResourceNotFoundException;
import com.depromeet.threedays.front.repository.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@RequiredArgsConstructor
@Service
public class UpdateMemberUseCase {
	private final MemberRepository memberRepository;

	public Member execute(final Long memberId, final UpdateNameRequest request) {
		return MemberConverter.from(this.updateName(memberId, request));
	}

	public MemberEntity updateName(final Long memberId, final UpdateNameRequest request) {
		MemberEntity member =
				memberRepository
						.findById(memberId)
						.orElseThrow(() -> new ResourceNotFoundException("member.not.found"));
		member.updateName(request.getName());
		return member;
	}
}
