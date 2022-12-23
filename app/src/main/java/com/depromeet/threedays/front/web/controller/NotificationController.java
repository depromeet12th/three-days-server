package com.depromeet.threedays.front.web.controller;

import com.depromeet.threedays.front.domain.usecase.notification.SaveNotificationUseCase;
import com.depromeet.threedays.front.domain.usecase.notification.SearchNotificationUseCase;
import com.depromeet.threedays.front.domain.usecase.notification.SendGlobalNotificationUseCase;
import com.depromeet.threedays.front.domain.usecase.notification.SendHabitNotificationUseCase;
import com.depromeet.threedays.front.support.ApiResponse;
import com.depromeet.threedays.front.support.ApiResponseGenerator;
import com.depromeet.threedays.front.web.request.notification.EditStatusNotificationRequest;
import com.depromeet.threedays.front.web.response.NotificationBatchResponse;
import com.depromeet.threedays.front.web.response.NotificationHistoryResponse;
import com.google.firebase.messaging.BatchResponse;
import java.util.List;
import java.util.stream.Collectors;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/v1/notifications")
@RequiredArgsConstructor
@RestController
public class NotificationController {

	private final SendGlobalNotificationUseCase globalUseCase;
	private final SendHabitNotificationUseCase habitUseCase;

	private final SearchNotificationUseCase searchUseCase;

	private final SaveNotificationUseCase saveUseCase;

	@GetMapping
	public ApiResponse<ApiResponse.SuccessBody<List<NotificationHistoryResponse>>> browse() {
		return ApiResponseGenerator.success(
				searchUseCase.execute().stream()
						.map(NotificationHistoryResponse::from)
						.collect(Collectors.toList()),
				HttpStatus.OK);
	}

	@PatchMapping("/{notificationHistoryId}")
	public ApiResponse<ApiResponse.SuccessBody<NotificationHistoryResponse>> editStatus(
			@PathVariable final Long notificationHistoryId,
			@RequestBody @Valid final EditStatusNotificationRequest request) {
		return ApiResponseGenerator.success(
				NotificationHistoryResponse.from(saveUseCase.execute(notificationHistoryId, request)),
				HttpStatus.OK);
	}

	@PostMapping("/global")
	public ApiResponse<ApiResponse.SuccessBody<List<NotificationBatchResponse>>>
			sendGlobalNotification() {
		return ApiResponseGenerator.success(globalUseCase.execute(), HttpStatus.OK);
	}

	@PostMapping("/habit")
	public ApiResponse<ApiResponse.SuccessBody<List<BatchResponse>>> sendHabitNotification() {
		return ApiResponseGenerator.success(habitUseCase.execute(), HttpStatus.OK);
	}

	@PostMapping("/test")
	public ApiResponse<ApiResponse.SuccessBody<Void>> test() {
		return ApiResponseGenerator.success(HttpStatus.OK);
	}
}
