package com.depromeet.threedays.front.support;

import com.depromeet.threedays.front.config.security.filter.token.TokenResolver;
import java.util.Optional;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.test.util.ReflectionTestUtils;

class TokenGeneratorTest {

	@Test
	void testGenerateAccessToken() {
		Long memberId = 1L;
		String secretKey = "ShVmYq3t6w9z$C&F)H@McQfTjWnZr4u7"; // 256bit
		Long accessTokenValidTime = 1000 * 60 * 60 * 24 * 7 * 52L; // 1년

		// create
		TokenGenerator tokenGenerator = new TokenGenerator();
		ReflectionTestUtils.setField(tokenGenerator, "secretKey", secretKey);
		ReflectionTestUtils.setField(tokenGenerator, "accessTokenValidTime", accessTokenValidTime);
		String actual = tokenGenerator.generateAccessToken(memberId);
		Assertions.assertThat(actual).isNotBlank();

		// read
		TokenResolver tokenResolver = new TokenResolver();
		ReflectionTestUtils.setField(tokenResolver, "secretKey", secretKey);
		Optional<Long> aLong = tokenResolver.resolveToken(actual);
		Assertions.assertThat(aLong).isPresent().get().isEqualTo(memberId);
	}
}
