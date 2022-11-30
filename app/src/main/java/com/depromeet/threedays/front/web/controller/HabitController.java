package com.depromeet.threedays.front.web.controller;

import com.depromeet.threedays.front.domain.model.habit.HabitOverview;
import com.depromeet.threedays.front.domain.usecase.habit.DeleteHabitUseCase;
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
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/habits")
public class HabitController {

	private final SaveHabitUseCase saveUseCase;

	private final SearchHabitUseCase searchUseCase;

	private final DeleteHabitUseCase deleteUseCase;

	@PostMapping
	public ApiResponse<HabitResponse> add(@RequestBody @Valid final SaveHabitRequest request) {
		return ApiResponseGenerator.success(HabitResponseConverter.from(saveUseCase.execute(request)));
	}

	@GetMapping
	public ApiResponse<List<HabitOverview>> browse() {
		return ApiResponseGenerator.success(searchUseCase.execute());
	}

	@DeleteMapping("/{habitIds}")
	public void delete(@PathVariable List<Long> habitIds) {
		for (Long habitId: habitIds) {
			deleteUseCase.execute(habitId);
		}
	}
}
