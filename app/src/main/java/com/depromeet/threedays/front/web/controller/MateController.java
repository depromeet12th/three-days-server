package com.depromeet.threedays.front.web.controller;

import com.depromeet.threedays.front.domain.model.mate.Mate;
import com.depromeet.threedays.front.domain.usecase.mate.SaveMateUseCase;
import com.depromeet.threedays.front.support.ApiResponse;
import com.depromeet.threedays.front.support.ApiResponseGenerator;
import com.depromeet.threedays.front.web.request.mate.SaveMateRequest;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
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

	@PostMapping()
	public ApiResponse<Mate> add(
			@PathVariable Long habitId, @RequestBody @Valid final SaveMateRequest request) {
		return ApiResponseGenerator.success(saveUseCase.execute(habitId, request), HttpStatus.CREATED);
	}
}
