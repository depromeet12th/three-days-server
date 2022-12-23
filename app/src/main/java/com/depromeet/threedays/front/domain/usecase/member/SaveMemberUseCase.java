package com.depromeet.threedays.front.domain.usecase.member;

import com.depromeet.threedays.data.entity.member.MemberEntity;
import com.depromeet.threedays.front.domain.command.SaveMemberCommand;
import com.depromeet.threedays.front.domain.converter.member.MemberConverter;
import com.depromeet.threedays.front.domain.model.member.SaveMemberUseCaseResponse;
import com.depromeet.threedays.front.domain.model.member.Token;
import com.depromeet.threedays.front.persistence.repository.member.MemberRepository;
import com.depromeet.threedays.front.support.TokenGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@RequiredArgsConstructor
@Service
public class SaveMemberUseCase {

	private final MemberRepository memberRepository;
	private final TokenGenerator tokenGenerator;

	public SaveMemberUseCaseResponse execute(SaveMemberCommand command) {
		MemberEntity memberEntity = memberRepository.save(MemberConverter.to(command));
		Token token = tokenGenerator.generateToken(memberEntity.getId());
		return MemberConverter.from(memberEntity, true, token);
	}
}
