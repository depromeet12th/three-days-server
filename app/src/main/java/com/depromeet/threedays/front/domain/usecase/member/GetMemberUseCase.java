package com.depromeet.threedays.front.domain.usecase.member;

import com.depromeet.threedays.front.domain.converter.member.MemberConverter;
import com.depromeet.threedays.front.domain.model.Member;
import com.depromeet.threedays.front.exception.ResourceNotFoundException;
import com.depromeet.threedays.front.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class GetMemberUseCase {

	private final MemberRepository memberRepository;

	public Member execute(Long id) {
		return MemberConverter.from(
				memberRepository.findById(id).orElseThrow(ResourceNotFoundException::new));
	}
}
