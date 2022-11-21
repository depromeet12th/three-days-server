package com.depromeet.threedays.front.web.controller;

import com.depromeet.threedays.front.domain.usecase.client.AddClientUseCaseFacade;
import com.depromeet.threedays.front.support.ApiResponse;
import com.depromeet.threedays.front.support.ApiResponseGenerator;
import com.depromeet.threedays.front.web.request.client.ClientRequest;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/v1/clients")
@RequiredArgsConstructor
@RestController
public class ClientController {

	private final AddClientUseCaseFacade addClientUseCase;

	@PostMapping("members/{memberId}")
	public ApiResponse<Void> add(
			@PathVariable Long memberId, @RequestBody @Valid ClientRequest request) {
		addClientUseCase.execute(memberId, request);
		return ApiResponseGenerator.success();
	}
}
