package com.depromeet.threedays.front.web.request.notification;

import com.depromeet.threedays.data.enums.NotificationStatus;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class EditStatusNotificationRequest {

	@NotNull
	@Length(max = 100)
	private NotificationStatus status;
}
