package com.depromeet.threedays.front.domain.usecase.member;

import com.depromeet.threedays.data.DomainTransactional;
import com.depromeet.threedays.front.controller.command.member.SaveMemberCommand;
import com.depromeet.threedays.front.domain.converter.member.MemberConverter;
import com.depromeet.threedays.front.domain.model.Member;
import com.depromeet.threedays.front.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@DomainTransactional
@RequiredArgsConstructor
@Service
public class SaveMemberUseCase {

	private final MemberRepository memberRepository;

	public Member execute(SaveMemberCommand command) {
		return MemberConverter.from(memberRepository.save(MemberConverter.to(command)));
	}
}
