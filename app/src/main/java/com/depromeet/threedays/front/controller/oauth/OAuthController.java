package com.depromeet.threedays.front.controller.oauth;

import com.depromeet.threedays.front.controller.command.oauth.OAuthCommand;
import com.depromeet.threedays.front.domain.model.Member;
import com.depromeet.threedays.front.domain.usecase.oauth.SaveOAuthUseCase;
import com.fasterxml.jackson.core.JsonProcessingException;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/v1/login/oauth2/google")
@RequiredArgsConstructor
@RestController
public class OAuthController {

	private final SaveOAuthUseCase getUseCase;

	@PostMapping
	public ResponseEntity<Member> sign(@RequestBody @Valid OAuthCommand command)
			throws JsonProcessingException {
		return ResponseEntity.ok(getUseCase.execute(command));
	}
}
