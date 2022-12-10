package com.depromeet.threedays.front.domain.usecase.member;

import com.depromeet.threedays.data.entity.member.MemberEntity;
import com.depromeet.threedays.front.config.security.AuditorHolder;
import com.depromeet.threedays.front.domain.converter.member.MemberConverter;
import com.depromeet.threedays.front.domain.model.member.Member;
import com.depromeet.threedays.front.domain.model.member.SaveMemberUseCaseResponse;
import com.depromeet.threedays.front.domain.model.member.Token;
import com.depromeet.threedays.front.domain.query.GetMemberQuery;
import com.depromeet.threedays.front.persistence.repository.member.MemberRepository;
import com.depromeet.threedays.front.support.TokenGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@RequiredArgsConstructor
@Service
public class GetMemberUseCase {

	private final MemberRepository memberRepository;
	private final TokenGenerator tokenGenerator;

	public SaveMemberUseCaseResponse execute(final GetMemberQuery query) {
		MemberEntity member =
				memberRepository
						.findByCertificationIdAndCertificationSubject(
								query.getCertificationId(), query.getCertificationSubject())
						.orElse(null);
		if (member == null) {
			return null;
		}
		Token token = tokenGenerator.generateToken(member.getId());
		return MemberConverter.from(member, false, token);
	}

	public Member execute() {
		Long memberId = AuditorHolder.get();
		return MemberConverter.from(memberRepository.findById(memberId).orElse(null));
	}
}
