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
		// TODO: 토큰에서 추출할 필요 없고 AuditHolder에서 뽑기
		Long memberId = tokenResolver.extractIdByToken(token.getRefreshToken());

		// TODO: validator로 리팩터, 토큰
		// TODO: Token 시간 확인하고 30분 이하이면 그대로 내려야함
		if (!memberRepository.existsById(memberId)) {
			// TODO: Policy가 아니고 401을 내려야함
			throw new PolicyViolationException("token.not.valid");
		}
		return tokenGenerator.generateToken(memberId);
	}
}
