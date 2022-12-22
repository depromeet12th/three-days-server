package com.depromeet.threedays.front.web.controller;

import com.depromeet.threedays.front.domain.model.notification.NotificationHistory;
import com.depromeet.threedays.front.domain.usecase.notification.SaveNotificationUseCase;
import com.depromeet.threedays.front.domain.usecase.notification.SearchNotificationUseCase;
import com.depromeet.threedays.front.domain.usecase.notification.SendGlobalNotificationUseCase;
import com.depromeet.threedays.front.domain.usecase.notification.SendHabitNotificationUseCase;
import com.depromeet.threedays.front.support.ApiResponse;
import com.depromeet.threedays.front.support.ApiResponseGenerator;
import com.depromeet.threedays.front.web.request.notification.EditStatusNotificationRequest;
import com.depromeet.threedays.front.web.response.NotificationBatchResponse;
import com.google.firebase.messaging.BatchResponse;
import java.util.List;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

	private final SaveNotificationUseCase saveUseCase;

	// FIXME: 응답 모델 NotificationHistoryResponse 로 변경
	@GetMapping
	public ApiResponse<ApiResponse.SuccessBody<List<NotificationHistory>>> browse() {
		return ApiResponseGenerator.success(searchUseCase.execute(), HttpStatus.OK);
	}

	// FIXME: 응답 모델 NotificationHistoryResponse 로 변경
	@PatchMapping("/{notificationHistoryId}")
	public ApiResponse<ApiResponse.SuccessBody<NotificationHistory>> editStatus(
			@PathVariable final Long notificationHistoryId,
			@RequestBody @Valid final EditStatusNotificationRequest request) {
		return ApiResponseGenerator.success(
				saveUseCase.execute(notificationHistoryId, request), HttpStatus.OK);
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
