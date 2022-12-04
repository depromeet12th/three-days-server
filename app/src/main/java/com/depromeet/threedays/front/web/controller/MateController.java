package com.depromeet.threedays.front.web.controller;

import com.depromeet.threedays.front.domain.model.mate.Mate;
import com.depromeet.threedays.front.domain.usecase.mate.DeleteMateUseCase;
import com.depromeet.threedays.front.domain.usecase.mate.GetMateUseCase;
import com.depromeet.threedays.front.domain.usecase.mate.SaveMateUseCase;
import com.depromeet.threedays.front.support.ApiResponse;
import com.depromeet.threedays.front.support.ApiResponseGenerator;
import com.depromeet.threedays.front.web.request.mate.SaveMateRequest;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/habits/{habitId}/mates")
public class MateController {

	private final SaveMateUseCase saveUseCase;
	private final DeleteMateUseCase deleteUseCase;

	private final GetMateUseCase getUseCase;

	@PostMapping()
	public ApiResponse<Mate> add(
			@PathVariable Long habitId, @RequestBody @Valid final SaveMateRequest request) {
		return ApiResponseGenerator.success(saveUseCase.execute(habitId, request), HttpStatus.CREATED);
	}

	@DeleteMapping("/{id}")
	public ApiResponse<Void> delete(@PathVariable Long habitId, @PathVariable Long id) {
		deleteUseCase.execute(habitId, id);
		return ApiResponseGenerator.success(HttpStatus.NO_CONTENT);
	}

	@GetMapping("/{id}")
	public ApiResponse<Mate> read(@PathVariable Long habitId, @PathVariable final Long id) {
		return ApiResponseGenerator.success(getUseCase.execute(habitId, id), HttpStatus.OK);
	}
}
