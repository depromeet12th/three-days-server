package com.depromeet.threedays.front.controller.client;

import com.depromeet.threedays.front.controller.request.member.ClientRequest;
import com.depromeet.threedays.front.domain.usecase.client.AddClientUseCaseFacade;
import com.depromeet.threedays.front.support.ApiResponse;
import com.depromeet.threedays.front.support.ApiResponseGenerator;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/v1/clients")
@RequiredArgsConstructor
@RestController
public class ClientController {

	private final AddClientUseCaseFacade addClientUseCase;

	@PostMapping("/{memberId}")
	public ApiResponse<Void> addClient(
			@PathVariable("memberId") Long memberId, @RequestBody @Valid ClientRequest request) {
		addClientUseCase.execute(memberId, request);
		return ApiResponseGenerator.success();
	}
}
