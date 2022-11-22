package com.depromeet.threedays.front.web.controller;

import com.depromeet.threedays.front.domain.model.notification.NotificationBatchResponse;
import com.depromeet.threedays.front.domain.usecase.notification.SendGlobalNotificationUseCase;
import com.depromeet.threedays.front.support.ApiResponse;
import com.depromeet.threedays.front.support.ApiResponseGenerator;
import com.depromeet.threedays.front.web.request.notification.NotificationRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/v1/notification")
@RequiredArgsConstructor
@RestController
public class NotificationController {

	private final SendGlobalNotificationUseCase useCase;

	@PostMapping
	public ApiResponse<NotificationBatchResponse> sendGlobalNotification(
			@RequestBody NotificationRequest request) {

		return ApiResponseGenerator.success(useCase.execute(request));
	}
}
