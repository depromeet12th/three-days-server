package com.depromeet.threedays.front.domain.usecase.member;

import com.depromeet.threedays.front.config.security.filter.token.TokenResolver;
import com.depromeet.threedays.front.domain.model.member.Token;
import com.depromeet.threedays.front.exception.RefreshTokenInvalidException;
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

	public Token execute(String refreshToken) {
		Long memberId =
				tokenResolver.resolveToken(refreshToken).orElseThrow(RefreshTokenInvalidException::new);

		// TODO: Token 시간 확인하고 30분 이하이면 그대로 내려야함
		if (!memberRepository.existsById(memberId)) {
			throw new RefreshTokenInvalidException();
		}
		return tokenGenerator.generateToken(memberId);
	}
}
