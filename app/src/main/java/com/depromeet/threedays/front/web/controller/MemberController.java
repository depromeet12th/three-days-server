package com.depromeet.threedays.front.web.controller;

import com.depromeet.threedays.front.domain.model.member.Member;
import com.depromeet.threedays.front.domain.model.member.Token;
import com.depromeet.threedays.front.domain.usecase.member.GetTokenUseCase;
import com.depromeet.threedays.front.domain.usecase.member.SaveConsentUseCase;
import com.depromeet.threedays.front.domain.usecase.member.SaveNameUseCase;
import com.depromeet.threedays.front.domain.usecase.member.SignMemberUseCaseFacade;
import com.depromeet.threedays.front.support.ApiResponse;
import com.depromeet.threedays.front.support.ApiResponseGenerator;
import com.depromeet.threedays.front.web.request.member.SignMemberRequest;
import com.depromeet.threedays.front.web.request.member.MemberNameUpdateRequest;
import com.depromeet.threedays.front.web.request.member.MemberNotificationConsentUpdateRequest;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/v1/members")
@RequiredArgsConstructor
@RestController
public class MemberController {
	private final SignMemberUseCaseFacade signUseCase;

	private final SaveNameUseCase saveNameUseCase;

	private final SaveConsentUseCase saveConsentUseCase;
	private final GetTokenUseCase getTokenUseCase;

	@PostMapping
	public ApiResponse<Member> add(@RequestBody @Valid SignMemberRequest request) {
		return ApiResponseGenerator.success(signUseCase.execute(request));
	}

	@PatchMapping("/name")
	public ApiResponse<Member> updateName(@RequestBody @Valid MemberNameUpdateRequest request) {
		return ApiResponseGenerator.success(saveNameUseCase.execute(request));
	}

	@PatchMapping("/consents")
	public ApiResponse<Member> updateConsent(
			@RequestBody @Valid MemberNotificationConsentUpdateRequest request) {
		return ApiResponseGenerator.success(saveConsentUseCase.execute(request));
	}

	@PostMapping("/tokens")
	public ApiResponse<Token> refreshToken(@RequestBody Token token) {
		return ApiResponseGenerator.success(getTokenUseCase.execute(token));
	}
}
