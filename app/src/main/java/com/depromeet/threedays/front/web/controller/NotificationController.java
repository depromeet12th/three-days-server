package com.depromeet.threedays.front.web.controller;

import com.depromeet.threedays.front.domain.usecase.notification.SendGlobalNotificationUseCase;
import com.depromeet.threedays.front.domain.usecase.notification.SendHabitNotificationUseCase;
import com.depromeet.threedays.front.support.ApiResponse;
import com.depromeet.threedays.front.support.ApiResponseGenerator;
import com.depromeet.threedays.front.web.request.habit.NotificationRequest;
import com.depromeet.threedays.front.web.response.NotificationBatchResponse;
import java.util.List;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/v1/notifications")
@RequiredArgsConstructor
@RestController
public class NotificationController {

	private final SendGlobalNotificationUseCase globalUseCase;
	private final SendHabitNotificationUseCase habitUseCase;

	@PostMapping("/global")
	public ApiResponse<List<NotificationBatchResponse>> sendGlobalNotification(
			@RequestBody @Valid NotificationRequest request) {
		return ApiResponseGenerator.success(globalUseCase.execute(request));
	}

	@PostMapping("/habit")
	public ApiResponse<Void> sendHabitNotification(@RequestBody @Valid NotificationRequest request) {
		habitUseCase.execute(request);
		return ApiResponseGenerator.success();
	}
}
