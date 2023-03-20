package com.depromeet.threedays.front.web.request.member;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class AppleSignMemberRequest extends SignMemberRequest {

	private String idToken;
	private String code;
	private String nonce;
	private AppleUserInfo user;

	public String getName() {
		return this.getUser().getName();
	}

	@Deprecated
	@Override
	public String getSocialToken() {
		return getIdToken();
	}
}
