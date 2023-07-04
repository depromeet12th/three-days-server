package com.depromeet.threedays.front.web.request.member;

import com.depromeet.threedays.data.enums.CertificationSubject;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class AppleSignMemberRequest extends SignMemberRequest {

	private String code;
	private String nonce;
	private AppleUserInfo user;

	private AppleSignMemberRequest(
			CertificationSubject certificationSubject,
			String socialToken,
			String code,
			String nonce,
			String email,
			String firstName,
			String lastName) {
		super(certificationSubject, socialToken);
		this.code = code;
		this.nonce = nonce;
		this.user = new AppleUserInfo(email, firstName, lastName);
	}

	@JsonIgnore
	public String getName() {
		return this.getUser().getName();
	}

	/** socialToken과 idToken은 동일하다. */
	@JsonIgnore
	public String getIdToken() {
		return super.getSocialToken();
	}
}
