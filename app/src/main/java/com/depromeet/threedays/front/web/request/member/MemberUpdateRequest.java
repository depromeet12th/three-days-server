package com.depromeet.threedays.front.web.request.member;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.json.simple.JSONObject;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class MemberUpdateRequest {

	private String name;
	private boolean notificationConsent;
	private JSONObject resource;
}
