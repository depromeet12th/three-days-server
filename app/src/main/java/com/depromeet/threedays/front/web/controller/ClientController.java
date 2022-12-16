package com.depromeet.threedays.front.web.controller;

import com.depromeet.threedays.front.domain.model.client.Client;
import com.depromeet.threedays.front.domain.usecase.client.SaveClientUseCase;
import com.depromeet.threedays.front.support.ApiResponse;
import com.depromeet.threedays.front.support.ApiResponseGenerator;
import com.depromeet.threedays.front.web.request.client.ClientRequest;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/v1/clients")
@RequiredArgsConstructor
@RestController
public class ClientController {

	private final SaveClientUseCase saveUseCase;

	@PostMapping()
	public ApiResponse<ApiResponse.SuccessBody<Client>> add(
			@RequestBody @Valid ClientRequest request) {
		return ApiResponseGenerator.success(saveUseCase.execute(request), HttpStatus.OK);
	}
}
