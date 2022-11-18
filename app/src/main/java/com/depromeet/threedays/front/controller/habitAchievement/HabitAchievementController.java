package com.depromeet.threedays.front.controller.habitAchievement;

import com.depromeet.threedays.front.domain.model.habit.Habit;
import com.depromeet.threedays.front.domain.usecase.habit.DeleteHabitAchievementUseCase;
import com.depromeet.threedays.front.support.ApiResponse;
import com.depromeet.threedays.front.support.ApiResponseGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/habits")
public class HabitAchievementController {

	private final DeleteHabitAchievementUseCase deleteHabitAchievementUseCase;

	@DeleteMapping("/{habitId}/achievements/{habitAchievementId}")
	public ApiResponse<Habit> cancelAchievement(
			@PathVariable("habitId") Long habitId,
			@PathVariable("habitAchievementId") Long habitAchievementId) {
		return ApiResponseGenerator.success(deleteHabitAchievementUseCase.execute(habitId, habitAchievementId));
	}
}
