package com.depromeet.threedays.front.controller.member;

import com.depromeet.threedays.front.controller.request.member.ClientRequest;
import com.depromeet.threedays.front.controller.request.member.SignMemberRequest;
import com.depromeet.threedays.front.controller.request.member.UpdateNameRequest;
import com.depromeet.threedays.front.domain.model.member.Member;
import com.depromeet.threedays.front.domain.usecase.client.AddClientUseCaseFacade;
import com.depromeet.threedays.front.domain.usecase.member.SignMemberUseCaseFacade;
import com.depromeet.threedays.front.domain.usecase.member.UpdateMemberUseCase;
import com.depromeet.threedays.front.support.ApiResponse;
import com.depromeet.threedays.front.support.ApiResponseGenerator;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/v1/members")
@RequiredArgsConstructor
@RestController
public class MemberController {

	private final SignMemberUseCaseFacade signUseCase;
	private final AddClientUseCaseFacade addClientUseCase;

	private final UpdateMemberUseCase updateUseCase;

	@PostMapping
	public ApiResponse<Member> sign(@RequestBody @Valid SignMemberRequest request) {
		return ApiResponseGenerator.success(signUseCase.execute(request));
	}

	@PostMapping("/{memberId}/clients")
	public ApiResponse<Void> addClient(
			@PathVariable("memberId") Long memberId, @RequestBody @Valid ClientRequest request) {
		addClientUseCase.execute(memberId, request);
		return ApiResponseGenerator.success();
	}

	@PatchMapping("/{memberId}")
	public ApiResponse<Member> updateName(
			@PathVariable("memberId") Long memberId, @RequestBody @Valid UpdateNameRequest request) {
		return ApiResponseGenerator.success(updateUseCase.execute(memberId, request));
	}
}
