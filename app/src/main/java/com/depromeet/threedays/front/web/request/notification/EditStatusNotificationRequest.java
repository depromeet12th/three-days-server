package com.depromeet.threedays.front.web.request.notification;

import com.depromeet.threedays.data.enums.NotificationStatus;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class EditStatusNotificationRequest {

	@NotNull private NotificationStatus status;

}
