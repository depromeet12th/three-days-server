package com.depromeet.threedays.front.web.controller;

import com.depromeet.threedays.front.domain.model.member.Member;
import com.depromeet.threedays.front.domain.model.member.Token;
import com.depromeet.threedays.front.domain.usecase.member.GetTokenUseCase;
import com.depromeet.threedays.front.domain.usecase.member.SignMemberUseCaseFacade;
import com.depromeet.threedays.front.domain.usecase.member.UpdateMemberUseCase;
import com.depromeet.threedays.front.support.ApiResponse;
import com.depromeet.threedays.front.support.ApiResponseGenerator;
import com.depromeet.threedays.front.web.request.member.SignMemberRequest;
import com.depromeet.threedays.front.web.request.member.UpdateNameRequest;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/v1/members")
@RequiredArgsConstructor
@RestController
public class MemberController {
	private final SignMemberUseCaseFacade signUseCase;

	private final UpdateMemberUseCase updateUseCase;

	private final GetTokenUseCase getTokenUseCase;

	@PostMapping
	public ApiResponse<Member> add(@RequestBody @Valid SignMemberRequest request) {
		return ApiResponseGenerator.success(signUseCase.execute(request));
	}

	@PatchMapping("/{memberId}")
	public ApiResponse<Member> updateName(
			@PathVariable Long memberId, @RequestBody @Valid UpdateNameRequest request) {
		return ApiResponseGenerator.success(updateUseCase.execute(memberId, request));
	}

	@PostMapping("/tokens")
	public ApiResponse<Token> refreshToken(@RequestBody Token token) {
		return ApiResponseGenerator.success(getTokenUseCase.execute(token));
	}
}
