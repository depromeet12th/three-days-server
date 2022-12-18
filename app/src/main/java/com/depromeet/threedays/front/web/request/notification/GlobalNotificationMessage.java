package com.depromeet.threedays.front.web.request.notification;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class GlobalNotificationMessage {
	@Length(max = 100) private String title;
	@Length(max = 100) private String contents;
}
