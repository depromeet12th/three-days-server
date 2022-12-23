package com.depromeet.threedays.front.web.controller;

import com.depromeet.threedays.front.domain.usecase.notification.SendGlobalNotificationUseCase;
import com.depromeet.threedays.front.domain.usecase.notification.SendHabitNotificationUseCase;
import com.depromeet.threedays.front.support.ApiResponse;
import com.depromeet.threedays.front.support.ApiResponseGenerator;
import com.depromeet.threedays.front.web.response.NotificationBatchResponse;
import com.google.firebase.messaging.BatchResponse;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/v1/notifications")
@RequiredArgsConstructor
@RestController
public class NotificationController {
	private final SendGlobalNotificationUseCase sendGlobalNotificationUseCase;
	private final SendHabitNotificationUseCase sendHabitNotificationUseCase;

	// FIXME: 관리자 권한 검증 필요
	@PostMapping("/global")
	public ApiResponse<ApiResponse.SuccessBody<List<NotificationBatchResponse>>>
			sendGlobalNotification() {
		return ApiResponseGenerator.success(sendGlobalNotificationUseCase.execute(), HttpStatus.OK);
	}

	// FIXME: 관리자 권한 검증 필요
	@PostMapping("/habit")
	public ApiResponse<ApiResponse.SuccessBody<List<BatchResponse>>> sendHabitNotification() {
		return ApiResponseGenerator.success(sendHabitNotificationUseCase.execute(), HttpStatus.OK);
	}

	@PostMapping("/test")
	public ApiResponse<ApiResponse.SuccessBody<Void>> test() {
		return ApiResponseGenerator.success(HttpStatus.OK);
	}
}
