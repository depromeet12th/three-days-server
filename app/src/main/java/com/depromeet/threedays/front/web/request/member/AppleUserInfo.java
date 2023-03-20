package com.depromeet.threedays.front.web.request.member;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
class AppleUserInfo {

	private String email;
	private AppleUserName name;

	@Getter
	@AllArgsConstructor
	@NoArgsConstructor
	private static class AppleUserName {
		private String firstName;
		private String lastName;
	}

	public String getName() {
		return this.name.getLastName() + this.name.getFirstName();
	}
}
