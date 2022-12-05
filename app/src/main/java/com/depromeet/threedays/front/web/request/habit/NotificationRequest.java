package com.depromeet.threedays.front.web.request.habit;

import java.time.LocalTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class NotificationRequest {

	private LocalTime notificationTime;
	private String contents;
}
