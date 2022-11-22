package com.depromeet.threedays.front.domain.model.notification;

import com.google.firebase.messaging.BatchResponse;
import java.util.List;
import lombok.Builder;

@Builder
public class NotificationBatchResponse {
	private List<BatchResponse> messageResponse;
}
