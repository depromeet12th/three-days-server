package com.depromeet.threedays.front.client.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class AppleToken {

	private String accessToken;
	private Long expires_in;

	@JsonProperty("id_token")
	private String idToken;

	private String refresh_token;
	private String token_type;
}
