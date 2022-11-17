package com.depromeet.threedays.front.controller.habit;

import com.depromeet.threedays.front.controller.request.habit.SaveHabitAchievementRequest;
import com.depromeet.threedays.front.controller.request.habit.SaveHabitRequest;
import com.depromeet.threedays.front.domain.model.habit.Habit;
import com.depromeet.threedays.front.domain.model.habit.HabitOverview;
import com.depromeet.threedays.front.domain.usecase.habit.SaveHabitAchievementUseCase;
import com.depromeet.threedays.front.domain.usecase.habit.SaveHabitUseCase;
import com.depromeet.threedays.front.domain.usecase.habit.SearchHabitUseCase;
import com.depromeet.threedays.front.support.ApiResponse;
import com.depromeet.threedays.front.support.ApiResponseGenerator;

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

	private final SaveHabitAchievementUseCase saveHabitAchievementUseCase;

	@PostMapping
	public ApiResponse<Habit> add(@RequestBody @Valid final SaveHabitRequest request) {
		return ApiResponseGenerator.success(saveUseCase.execute(request));
	}

	@GetMapping
	public ApiResponse<List<HabitOverview>> browse() {
		return ApiResponseGenerator.success(searchUseCase.execute());
	}

	@PostMapping("/{habitId}/achievements")
	public ApiResponse<Habit> addAchievement(
			@PathVariable("habitId") Long habitId,
			@RequestBody @Valid final SaveHabitAchievementRequest request) {
		return ApiResponseGenerator.success(saveHabitAchievementUseCase.execute(habitId, request));
	}
}
