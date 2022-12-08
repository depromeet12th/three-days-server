package com.depromeet.threedays.front.web.controller;

import com.depromeet.threedays.front.domain.model.notification.NotificationHistory;
import com.depromeet.threedays.front.domain.usecase.notification.SaveNotificationUseCase;
import com.depromeet.threedays.front.domain.usecase.notification.SearchNotificationUseCase;
import com.depromeet.threedays.front.domain.usecase.notification.SendGlobalNotificationUseCase;
import com.depromeet.threedays.front.domain.usecase.notification.SendHabitNotificationUseCase;
import com.depromeet.threedays.front.support.ApiResponse;
import com.depromeet.threedays.front.support.ApiResponseGenerator;
import com.depromeet.threedays.front.web.request.habit.NotificationRequest;
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

	@GetMapping
	public ApiResponse<List<NotificationHistory>> browse() {
		return ApiResponseGenerator.success(searchUseCase.execute(), HttpStatus.OK);
	}

	@PatchMapping("/{id}")
	public ApiResponse<Void> editStatus(@PathVariable final Long id,
			@RequestBody @Valid final EditStatusNotificationRequest request) {
		saveUseCase.execute(id, request);
		return ApiResponseGenerator.success(HttpStatus.NO_CONTENT);
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
