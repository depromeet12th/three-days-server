package com.depromeet.threedays.front.web.controller;

import com.depromeet.threedays.front.domain.model.habit.HabitAchievement;
import com.depromeet.threedays.front.domain.usecase.habit.DeleteHabitAchievementUseCase;
import com.depromeet.threedays.front.domain.usecase.habit.SaveHabitAchievementUseCase;
import com.depromeet.threedays.front.domain.usecase.habit.SearchHabitAchievementUseCase;
import com.depromeet.threedays.front.support.ApiResponse;
import com.depromeet.threedays.front.support.ApiResponseGenerator;
import com.depromeet.threedays.front.support.MessageCode;
import com.depromeet.threedays.front.web.request.habit.SaveHabitAchievementRequest;
import com.depromeet.threedays.front.web.request.habit.SearchHabitAchievementRequest;
import com.depromeet.threedays.front.web.response.HabitResponse;
import com.depromeet.threedays.front.web.response.converter.HabitResponseConverter;
import java.util.List;
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
@RequestMapping("/api/v1/habits/{habitId}/achievements")
public class HabitAchievementController {

	private final SaveHabitAchievementUseCase saveUseCase;

	private final SearchHabitAchievementUseCase searchUseCase;

	private final DeleteHabitAchievementUseCase deleteUseCase;

	@PostMapping
	public ApiResponse<ApiResponse.SuccessBody<HabitResponse>> add(
			@PathVariable Long habitId, @RequestBody @Valid final SaveHabitAchievementRequest request) {
		return ApiResponseGenerator.success(
				HabitResponseConverter.from(saveUseCase.execute(habitId, request)), HttpStatus.CREATED, MessageCode.RESOURCE_CREATED);
	}

	@GetMapping
	public ApiResponse<ApiResponse.SuccessBody<List<HabitAchievement>>> browse(
			@PathVariable Long habitId, final SearchHabitAchievementRequest request) {
		return ApiResponseGenerator.success(searchUseCase.execute(habitId, request), HttpStatus.OK);
	}

	@DeleteMapping("/{habitAchievementId}")
	public ApiResponse<ApiResponse.SuccessBody<HabitResponse>> delete(
			@PathVariable Long habitId, @PathVariable Long habitAchievementId) {
		return ApiResponseGenerator.success(
				HabitResponseConverter.from(deleteUseCase.execute(habitId, habitAchievementId)),
				HttpStatus.OK);
	}
}
