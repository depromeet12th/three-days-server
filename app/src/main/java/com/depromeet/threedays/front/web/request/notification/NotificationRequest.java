package com.depromeet.threedays.front.web.request.notification;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class NotificationRequest {
	private String title;
	private String contents;
}
