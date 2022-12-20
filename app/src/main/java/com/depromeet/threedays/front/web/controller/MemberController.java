package com.depromeet.threedays.front.web.controller;

import com.depromeet.threedays.front.domain.converter.member.MemberConverter;
import com.depromeet.threedays.front.domain.model.member.Member;
import com.depromeet.threedays.front.domain.model.member.SaveMemberUseCaseResponse;
import com.depromeet.threedays.front.domain.model.member.Token;
import com.depromeet.threedays.front.domain.usecase.client.DeleteClientUseCase;
import com.depromeet.threedays.front.domain.usecase.member.*;
import com.depromeet.threedays.front.support.ApiResponse;
import com.depromeet.threedays.front.support.ApiResponseGenerator;
import com.depromeet.threedays.front.support.MessageCode;
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
	private final GetMemberUseCase getUseCase;

	@PostMapping
	public ApiResponse<ApiResponse.SuccessBody<SaveMemberResponse>> add(
			@RequestBody @Valid SignMemberRequest request) {
		SaveMemberUseCaseResponse member = signUseCase.execute(request);
		if (Boolean.TRUE.equals(member.getIsNew())) {
			return ApiResponseGenerator.success(
					MemberConverter.to(member), HttpStatus.CREATED, MessageCode.RESOURCE_CREATED);
		} else {
			return ApiResponseGenerator.success(MemberConverter.to(member), HttpStatus.OK);
		}
	}

	@PatchMapping("/name")
	public ApiResponse<ApiResponse.SuccessBody<Member>> updateName(
			@RequestBody @Valid MemberNameUpdateRequest request) {
		return ApiResponseGenerator.success(saveNameUseCase.execute(request), HttpStatus.OK);
	}

	@PatchMapping("/consents")
	public ApiResponse<ApiResponse.SuccessBody<Member>> updateConsent(
			@RequestBody @Valid MemberNotificationConsentUpdateRequest request) {
		return ApiResponseGenerator.success(saveConsentUseCase.execute(request), HttpStatus.OK);
	}

	@PatchMapping("/resources")
	public ApiResponse<ApiResponse.SuccessBody<Member>> updateResource(
			@RequestBody @Valid MemberResourceUpdateRequest request) {
		return ApiResponseGenerator.success(saveResourceUseCase.execute(request), HttpStatus.OK);
	}

	@PostMapping("/tokens")
	public ApiResponse<ApiResponse.SuccessBody<Token>> refreshToken(@RequestBody Token token) {
		return ApiResponseGenerator.success(getTokenUseCase.execute(token), HttpStatus.CREATED);
	}

	@DeleteMapping
	public ApiResponse<ApiResponse.SuccessBody<Void>> deleteMember() {
		deleteUseCase.execute();
		return ApiResponseGenerator.success(HttpStatus.OK, MessageCode.RESOURCE_DELETED);
	}

	@GetMapping
	public ApiResponse<ApiResponse.SuccessBody<Member>> readMember() {
		return ApiResponseGenerator.success(getUseCase.execute(), HttpStatus.OK);
	}

	@PostMapping("/logout")
	public ApiResponse<ApiResponse.SuccessBody<Void>> deleteClient(
			@RequestBody @Valid DeleteClientRequest request) {
		deleteClientUseCase.execute(request);
		return ApiResponseGenerator.success(HttpStatus.NO_CONTENT);
	}
}
