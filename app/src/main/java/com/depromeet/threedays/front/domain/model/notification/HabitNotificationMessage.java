package com.depromeet.threedays.front.domain.model.notification;

import com.depromeet.threedays.front.domain.model.client.Client;
import java.time.LocalTime;
import java.util.List;
import lombok.*;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class HabitNotificationMessage {
	private Long notificationId;
	private Long memberId;
	private List<Client> clients;
	private Long habitId;
	private String content;
	private LocalTime notificationTime;
}
