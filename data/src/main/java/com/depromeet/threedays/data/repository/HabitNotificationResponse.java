package com.depromeet.threedays.data.repository;

import java.time.LocalTime;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class HabitNotificationResponse {
	private Long habitId;
	private Long memberId;
	private String contents;
	private LocalTime notificationTime;
	private List<String> fcmToken;
}
