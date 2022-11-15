package com.depromeet.threedays.front.domain.usecase.member;

import com.depromeet.threedays.front.domain.converter.member.MemberConverter;
import com.depromeet.threedays.front.domain.model.member.Member;
import com.depromeet.threedays.front.domain.query.GetMemberQuery;
import com.depromeet.threedays.front.repository.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@RequiredArgsConstructor
@Service
public class GetMemberUseCase {

	private final MemberRepository memberRepository;

	public Member execute(GetMemberQuery query) {
		return MemberConverter.from(
				memberRepository
						.findByCertificationIdAndCertificationSubject(
								query.getCertificationId(), query.getCertificationSubject())
						.orElse(null),
				false);
	}
}
