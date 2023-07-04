package com.depromeet.threedays.front.web.request.member;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
class AppleUserInfo {

	private String email;

	@JsonProperty("name")
	private AppleUserName name;

	protected AppleUserInfo(String email, String firstName, String lastName) {
		this.email = email;
		this.name = new AppleUserName(firstName, lastName);
	}

	@Getter
	@AllArgsConstructor
	@NoArgsConstructor
	private static class AppleUserName {
		private String firstName;
		private String lastName;
	}

	@JsonIgnore
	public String getName() {
		return this.name.getLastName() + this.name.getFirstName();
	}
}
