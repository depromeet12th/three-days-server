package com.depromeet.threedays.front.web.controller;

import com.depromeet.threedays.front.domain.model.habit.Habit;
import com.depromeet.threedays.front.domain.usecase.habit.DeleteHabitAchievementUseCase;
import com.depromeet.threedays.front.domain.usecase.habit.SaveHabitAchievementUseCase;
import com.depromeet.threedays.front.support.ApiResponse;
import com.depromeet.threedays.front.support.ApiResponseGenerator;
import com.depromeet.threedays.front.web.request.habit.SaveHabitAchievementRequest;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/habits/{habitId}/achievements")
public class HabitAchievementController {

	private final SaveHabitAchievementUseCase saveUseCase;

	private final DeleteHabitAchievementUseCase deleteUseCase;

	@PostMapping
	public ApiResponse<Habit> addAchievement(
			@PathVariable Long habitId, @RequestBody @Valid final SaveHabitAchievementRequest request) {
		return ApiResponseGenerator.success(saveUseCase.execute(habitId, request));
	}

	@DeleteMapping("/{habitAchievementId}")
	public ApiResponse<Habit> cancelAchievement(
			@PathVariable Long habitId, @PathVariable Long habitAchievementId) {
		return ApiResponseGenerator.success(deleteUseCase.execute(habitId, habitAchievementId));
	}
}
