package com.depromeet.threedays.front.web.controller;

import com.depromeet.threedays.front.domain.model.notification.NotificationHistory;
import com.depromeet.threedays.front.domain.usecase.notification.SearchNotificationUseCase;
import com.depromeet.threedays.front.domain.usecase.notification.SendGlobalNotificationUseCase;
import com.depromeet.threedays.front.domain.usecase.notification.SendHabitNotificationUseCase;
import com.depromeet.threedays.front.support.ApiResponse;
import com.depromeet.threedays.front.support.ApiResponseGenerator;
import com.depromeet.threedays.front.web.request.habit.NotificationRequest;
import com.depromeet.threedays.front.web.response.NotificationBatchResponse;
import com.google.firebase.messaging.BatchResponse;
import java.util.List;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/v1/notifications")
@RequiredArgsConstructor
@RestController
public class NotificationController {

	private final SendGlobalNotificationUseCase globalUseCase;
	private final SendHabitNotificationUseCase habitUseCase;

	private final SearchNotificationUseCase searchUseCase;

	@GetMapping
	public ApiResponse<List<NotificationHistory>> browse() {
		return ApiResponseGenerator.success(searchUseCase.execute(), HttpStatus.OK);
	}

	@PostMapping("/global")
	public ApiResponse<List<NotificationBatchResponse>> sendGlobalNotification(
			@RequestBody @Valid NotificationRequest request) {
		return ApiResponseGenerator.success(globalUseCase.execute(request), HttpStatus.OK);
	}

	@PostMapping("/habit")
	public ApiResponse<List<BatchResponse>> sendHabitNotification(
			@RequestBody @Valid NotificationRequest request) {
		return ApiResponseGenerator.success(habitUseCase.execute(request), HttpStatus.OK);
	}
}
