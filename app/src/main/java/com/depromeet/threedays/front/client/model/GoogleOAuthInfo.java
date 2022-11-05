package com.depromeet.threedays.front.client.model;

import lombok.Getter;

@Getter
public class GoogleOAuthInfo extends OAuthInfo {
	private Boolean verifiedEmail;
	private String givenName;
	private String familyName;
	private String picture;
	private String locale;
}
