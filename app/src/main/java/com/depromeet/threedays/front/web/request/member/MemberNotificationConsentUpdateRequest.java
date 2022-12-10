package com.depromeet.threedays.front.web.request.member;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class MemberNotificationConsentUpdateRequest {

	private boolean notificationConsent;
}
