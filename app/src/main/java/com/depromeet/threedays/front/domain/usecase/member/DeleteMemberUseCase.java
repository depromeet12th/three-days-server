package com.depromeet.threedays.front.domain.usecase.member;

import com.depromeet.threedays.data.entity.member.MemberEntity;
import com.depromeet.threedays.data.enums.MemberStatus;
import com.depromeet.threedays.front.config.security.AuditorHolder;
import com.depromeet.threedays.front.domain.converter.member.MemberConverter;
import com.depromeet.threedays.front.domain.model.member.Member;
import com.depromeet.threedays.front.exception.ResourceNotFoundException;
import com.depromeet.threedays.front.persistence.repository.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@RequiredArgsConstructor
@Service
public class DeleteMemberUseCase {

	private final MemberRepository memberRepository;

	public Member execute() {
		Long memberId = AuditorHolder.get();
		return memberRepository
				.findByIdAndStatus(memberId, MemberStatus.REGULAR)
				.map(MemberEntity::withdraw)
				.map(MemberConverter::from)
				.orElseThrow(ResourceNotFoundException::new);
	}
}
