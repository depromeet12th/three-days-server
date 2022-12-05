package com.depromeet.threedays.front.web.response;

import com.google.firebase.messaging.BatchResponse;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class NotificationBatchResponse {
	private String title;
	private String content;
	private List<BatchResponse> messageResponse;
}
