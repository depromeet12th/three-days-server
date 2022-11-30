package com.depromeet.threedays.front.web.controller;

import com.depromeet.threedays.front.domain.model.habit.HabitOverview;
import com.depromeet.threedays.front.domain.usecase.habit.SaveHabitUseCase;
import com.depromeet.threedays.front.domain.usecase.habit.SearchHabitUseCase;
import com.depromeet.threedays.front.support.ApiResponse;
import com.depromeet.threedays.front.support.ApiResponseGenerator;
import com.depromeet.threedays.front.web.request.habit.SaveHabitRequest;
import com.depromeet.threedays.front.web.response.HabitResponse;
import com.depromeet.threedays.front.web.response.converter.HabitResponseConverter;
import java.util.List;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/habits")
public class HabitController {

	private final SaveHabitUseCase saveUseCase;

	private final SearchHabitUseCase searchUseCase;

	@PostMapping
	public ApiResponse<HabitResponse> add(
			@AuthenticationPrincipal Long memberId, @RequestBody @Valid final SaveHabitRequest request) {
		return ApiResponseGenerator.success(HabitResponseConverter.from(saveUseCase.execute(request)));
	}

	@GetMapping
	public ApiResponse<List<HabitOverview>> browse() {
		return ApiResponseGenerator.success(searchUseCase.execute());
	}
}
