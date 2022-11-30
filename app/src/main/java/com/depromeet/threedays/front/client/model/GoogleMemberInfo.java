package com.depromeet.threedays.front.client.model;

import lombok.Getter;

@Getter
public class GoogleMemberInfo extends MemberInfo {
	private String name;
	private String email;
	private Boolean verifiedEmail;
	private String givenName;
	private String familyName;
	private String picture;
	private String locale;
}
