package com.depromeet.threedays.front.support;

import com.depromeet.threedays.front.domain.model.notification.HabitNotificationMessage;
import com.depromeet.threedays.front.domain.model.notification.NotificationMessage;
import com.google.firebase.messaging.BatchResponse;
import com.google.firebase.messaging.SendResponse;
import java.time.LocalDateTime;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@UtilityClass
public class NotificationLogger {

	public static void messagesLogger(NotificationMessage message) {
		log.info(
				"===prepare to sending globalNoti=== title:{}, contents:{}, notification_time:{}",
				message.getTitle(),
				message.getContents(),
				message.getNotificationTime());
	}

	public static void messagesLogger(HabitNotificationMessage habitMessage) {
		log.info(
				"===prepare to sending habitNoti=== member:{}, habit:{}, content:{}, notification_time:{}",
				habitMessage.getMemberId(),
				habitMessage.getHabitId(),
				habitMessage.getContent(),
				habitMessage.getNotificationTime());
	}

	public static void batchResponseLogger(BatchResponse response) {
		log.info("===sending completed===");
		for (SendResponse r : response.getResponses()) {
			log.info(
					"messageId: {}, exception:{}, successful:{}",
					r.getMessageId(),
					r.getException(),
					r.isSuccessful());
		}
		log.info(
				"successCount {}, failureCount {} at {}",
				response.getSuccessCount(),
				response.getFailureCount(),
				LocalDateTime.now());
	}
}
