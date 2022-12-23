package com.depromeet.threedays.front.web.controller;

import com.depromeet.threedays.front.domain.model.habit.HabitOverview;
import com.depromeet.threedays.front.domain.usecase.habit.*;
import com.depromeet.threedays.front.support.ApiResponse;
import com.depromeet.threedays.front.support.ApiResponseGenerator;
import com.depromeet.threedays.front.support.MessageCode;
import com.depromeet.threedays.front.web.request.habit.SaveHabitRequest;
import com.depromeet.threedays.front.web.request.habit.SearchHabitRequest;
import com.depromeet.threedays.front.web.request.habit.UpdateHabitRequest;
import com.depromeet.threedays.front.web.response.HabitResponse;
import com.depromeet.threedays.front.web.response.assembler.HabitAssembler;
import java.util.List;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/habits")
public class HabitController {

	private final SaveHabitUseCase saveUseCase;
	private final GetHabitUseCase getUseCase;

	private final SearchHabitUseCase searchUseCase;

	private final DeleteHabitUseCase deleteUseCase;

	private final UpdateHabitUseCase updateUseCase;

	private final HabitAssembler habitAssembler;

	@PostMapping
	public ApiResponse<ApiResponse.SuccessBody<HabitResponse>> add(
			@RequestBody @Valid final SaveHabitRequest request) {
		return ApiResponseGenerator.success(
				habitAssembler.toHabitResponse(saveUseCase.execute(request)),
				HttpStatus.CREATED,
				MessageCode.RESOURCE_CREATED);
	}

	@PutMapping("/{id}")
	public ApiResponse<ApiResponse.SuccessBody<HabitResponse>> edit(
			@PathVariable final Long id, @RequestBody @Valid final UpdateHabitRequest request) {
		return ApiResponseGenerator.success(
				habitAssembler.toHabitResponse(updateUseCase.execute(id, request)), HttpStatus.OK);
	}

	@GetMapping
	public ApiResponse<ApiResponse.SuccessBody<List<HabitOverview>>> browse(
			final SearchHabitRequest request) {
		return ApiResponseGenerator.success(searchUseCase.execute(request), HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ApiResponse<ApiResponse.SuccessBody<HabitResponse>> read(@PathVariable final Long id) {
		return ApiResponseGenerator.success(
				habitAssembler.toHabitResponse(getUseCase.execute(id)), HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ApiResponse<ApiResponse.SuccessBody<Void>> delete(@PathVariable final Long id) {
		deleteUseCase.execute(id);
		return ApiResponseGenerator.success(HttpStatus.OK, MessageCode.RESOURCE_DELETED);
	}
}
