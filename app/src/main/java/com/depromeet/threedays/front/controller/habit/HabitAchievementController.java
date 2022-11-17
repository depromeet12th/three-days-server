package com.depromeet.threedays.front.controller.habit;

import com.depromeet.threedays.front.controller.request.habit.SaveHabitAchievementRequest;
import com.depromeet.threedays.front.domain.model.habit.Habit;
import com.depromeet.threedays.front.domain.usecase.habit.SaveHabitAchievementUseCase;
import com.depromeet.threedays.front.support.ApiResponse;
import com.depromeet.threedays.front.support.ApiResponseGenerator;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/habits/{habitId}/achievements")
public class HabitAchievementController {

	private final SaveHabitAchievementUseCase saveHabitAchievementUseCase;

	@PostMapping
	public ApiResponse<Habit> addAchievement(
			@PathVariable("habitId") Long habitId,
			@RequestBody @Valid final SaveHabitAchievementRequest request) {
		return ApiResponseGenerator.success(saveHabitAchievementUseCase.execute(habitId, request));
	}
}
