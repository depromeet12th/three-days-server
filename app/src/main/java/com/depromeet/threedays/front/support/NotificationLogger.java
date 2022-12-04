package com.depromeet.threedays.front.support;

import com.depromeet.threedays.front.domain.model.notification.NotificationMessage;
import com.google.firebase.messaging.BatchResponse;
import com.google.firebase.messaging.SendResponse;
import java.time.LocalDateTime;
import java.util.List;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@UtilityClass
public class NotificationLogger {

	public static void messagesLogger(List<NotificationMessage> messages) {
		log.info("=====SEND MESSAGE=====");
		for (NotificationMessage message : messages) {
			log.info(
					"title:{}, contents:{}, notification_time:{}",
					message.getTitle(),
					message.getContents(),
					message.getNotificationTime());
		}
	}

	public static void batchResponseLogger(BatchResponse response) {
		log.info("=====SEND RESULT=====");
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
