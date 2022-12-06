package com.depromeet.threedays.front.web.controller;

import com.depromeet.threedays.front.domain.model.member.Member;
import com.depromeet.threedays.front.domain.model.member.Token;
import com.depromeet.threedays.front.domain.usecase.member.GetTokenUseCase;
import com.depromeet.threedays.front.domain.usecase.member.SaveConsentUseCase;
import com.depromeet.threedays.front.domain.usecase.member.SaveNameUseCase;
import com.depromeet.threedays.front.domain.usecase.member.SignMemberUseCaseFacade;
import com.depromeet.threedays.front.support.ApiResponse;
import com.depromeet.threedays.front.support.ApiResponseGenerator;
import com.depromeet.threedays.front.web.request.member.MemberNameUpdateRequest;
import com.depromeet.threedays.front.web.request.member.MemberNotificationConsentUpdateRequest;
import com.depromeet.threedays.front.web.request.member.SignMemberRequest;
import javax.validation.Valid;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
		Member member = signUseCase.execute(request);
		HttpStatus status = member.getIsNew()?HttpStatus.CREATED:HttpStatus.OK;
		return ApiResponseGenerator.success(member, status);
	}

	@PatchMapping("/name")
	public ApiResponse<Member> updateName(@RequestBody @Valid MemberNameUpdateRequest request) {
		return ApiResponseGenerator.success(saveNameUseCase.execute(request), HttpStatus.OK);
	}

	@PatchMapping("/consents")
	public ApiResponse<Member> updateConsent(
			@RequestBody @Valid MemberNotificationConsentUpdateRequest request) {
		return ApiResponseGenerator.success(saveConsentUseCase.execute(request), HttpStatus.OK);
	}

	@PostMapping("/tokens")
	public ApiResponse<Token> refreshToken(@RequestBody Token token) {
		return ApiResponseGenerator.success(getTokenUseCase.execute(token), HttpStatus.CREATED);
	}
}
