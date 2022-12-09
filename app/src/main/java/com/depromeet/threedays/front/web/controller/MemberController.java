package com.depromeet.threedays.front.web.controller;

import com.depromeet.threedays.front.domain.converter.member.MemberConverter;
import com.depromeet.threedays.front.domain.model.member.Member;
import com.depromeet.threedays.front.domain.model.member.SaveMemberUseCaseResponse;
import com.depromeet.threedays.front.domain.model.member.Token;
import com.depromeet.threedays.front.domain.usecase.client.DeleteClientUseCase;
import com.depromeet.threedays.front.domain.usecase.member.*;
import com.depromeet.threedays.front.support.ApiResponse;
import com.depromeet.threedays.front.support.ApiResponseGenerator;
import com.depromeet.threedays.front.web.request.client.DeleteClientRequest;
import com.depromeet.threedays.front.web.request.member.MemberNameUpdateRequest;
import com.depromeet.threedays.front.web.request.member.MemberNotificationConsentUpdateRequest;
import com.depromeet.threedays.front.web.request.member.MemberResourceUpdateRequest;
import com.depromeet.threedays.front.web.request.member.SignMemberRequest;
import com.depromeet.threedays.front.web.response.SaveMemberResponse;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/v1/members")
@RequiredArgsConstructor
@RestController
public class MemberController {

	private final SignMemberUseCaseFacade signUseCase;

	private final SaveNameUseCase saveNameUseCase;

	private final SaveConsentUseCase saveConsentUseCase;
	private final SaveResourceUseCase saveResourceUseCase;
	private final GetTokenUseCase getTokenUseCase;
	private final DeleteMemberUseCase deleteUseCase;

	private final DeleteClientUseCase deleteClientUseCase;

	@PostMapping
	public ApiResponse<SaveMemberResponse> add(@RequestBody @Valid SignMemberRequest request) {
		SaveMemberUseCaseResponse member = signUseCase.execute(request);
		HttpStatus status = Boolean.TRUE.equals(member.getIsNew()) ? HttpStatus.CREATED : HttpStatus.OK;
		return ApiResponseGenerator.success(MemberConverter.to(member), status);
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

	@PatchMapping("/resources")
	public ApiResponse<Member> updateResource(
			@RequestBody @Valid MemberResourceUpdateRequest request) {
		return ApiResponseGenerator.success(saveResourceUseCase.execute(request), HttpStatus.OK);
	}

	@PostMapping("/tokens")
	public ApiResponse<Token> refreshToken(@RequestBody Token token) {
		return ApiResponseGenerator.success(getTokenUseCase.execute(token), HttpStatus.CREATED);
	}

	@DeleteMapping
	public ApiResponse<Void> deleteMember() {
		deleteUseCase.execute();
		return ApiResponseGenerator.success(HttpStatus.NO_CONTENT);
	}

	@PostMapping("/logout")
	public ApiResponse<Void> deleteClient(@RequestBody @Valid DeleteClientRequest request) {
		deleteClientUseCase.execute(request);
		return ApiResponseGenerator.success(HttpStatus.NO_CONTENT);
	}
}
