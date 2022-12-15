package com.depromeet.threedays.front.web.controller;

import com.depromeet.threedays.front.domain.usecase.SearchRecordUseCase;
import com.depromeet.threedays.front.support.ApiResponse;
import com.depromeet.threedays.front.support.ApiResponseGenerator;
import com.depromeet.threedays.front.web.request.SearchRecordRequest;
import com.depromeet.threedays.front.web.response.RecordResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/v1/records")
@RequiredArgsConstructor
@RestController
public class RecordController {

	private final SearchRecordUseCase searchUseCase;

	@GetMapping
	public ApiResponse<ApiResponse.SuccessBody<RecordResponse>> browse(
			final SearchRecordRequest request) {
		return ApiResponseGenerator.success(searchUseCase.execute(request), HttpStatus.OK);
	}
}
