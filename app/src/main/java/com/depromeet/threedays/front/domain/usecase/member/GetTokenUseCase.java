package com.depromeet.threedays.front.domain.usecase.member;

import com.depromeet.threedays.front.config.security.filter.token.TokenResolver;
import com.depromeet.threedays.front.domain.model.member.Token;
import com.depromeet.threedays.front.exception.PolicyViolationException;
import com.depromeet.threedays.front.persistence.repository.member.MemberRepository;
import com.depromeet.threedays.front.support.TokenGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class GetTokenUseCase {
	private final TokenResolver tokenResolver;
	private final TokenGenerator tokenGenerator;
	private final MemberRepository memberRepository;

	public Token execute(Token token) {
		Long memberId = tokenResolver.extractIdByToken(token.getRefreshToken());
		if (!memberRepository.existsById(memberId)) {
			throw new PolicyViolationException("token.not.valid");
		}
		return tokenGenerator.generateToken(memberId);
	}
}
