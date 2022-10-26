package com.depromeet.threedays.front.client;

import com.depromeet.threedays.front.client.model.GoogleOAuthInfo;
import com.depromeet.threedays.front.client.model.OAuthInfo;
import com.depromeet.threedays.front.client.property.OAuthProperty;
import com.depromeet.threedays.front.domain.model.Token;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
public class GoogleOAuthClient extends OAuthClient {

	public GoogleOAuthClient(WebClient webClient) {
		super(webClient);
	}

	@Override
	Token getToken(OAuthProperty oAuthProperty, String code) {
		MultiValueMap<String, String> body = writeBodyDataForToken(oAuthProperty, code);
		return webClient
				.post()
				.uri(oAuthProperty.getAccessTokenUri())
				.contentType(MediaType.APPLICATION_FORM_URLENCODED)
				.bodyValue(body)
				.retrieve()
				.onStatus(
						HttpStatus::is5xxServerError,
						error -> Mono.error(() -> new RuntimeException("서버가 이상해요")))
				.onStatus(
						HttpStatus::is4xxClientError,
						error -> Mono.error(() -> new RuntimeException("요청이 바르지 않아요")))
				.bodyToMono(Token.class)
				.blockOptional()
				.orElseThrow(() -> new RuntimeException("토큰이 이상헤요"));
	}

	@Override
	OAuthInfo getOAuthInfo(OAuthProperty oAuthProperty, Token token) {

		return webClient
				.get()
				.uri(oAuthProperty.getUserUri())
				.header(HttpHeaders.AUTHORIZATION, "Bearer " + token.getAccessToken())
				.retrieve()
				.onStatus(
						HttpStatus::is5xxServerError,
						error -> Mono.error(() -> new RuntimeException("서버가 이상해요")))
				.onStatus(
						HttpStatus::is4xxClientError,
						error -> Mono.error(() -> new RuntimeException("요청이 바르지 않아요")))
				.bodyToMono(GoogleOAuthInfo.class)
				.blockOptional()
				.orElseThrow(() -> new RuntimeException("토큰이 이상헤요"));
	}
}
