package com.depromeet.threedays.front.web.controller;

import com.depromeet.threedays.front.domain.usecase.mate.GetMateCheckUseCase;
import com.depromeet.threedays.front.support.ApiResponse;
import com.depromeet.threedays.front.support.ApiResponseGenerator;
import com.depromeet.threedays.front.web.response.MateResponse;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/mates")
public class MateCheckController {

	private final GetMateCheckUseCase getUseCase;

	@GetMapping
	public ApiResponse<ApiResponse.SuccessBody<List<MateResponse>>> read() {
		return ApiResponseGenerator.success(getUseCase.execute(), HttpStatus.OK);
	}
}
