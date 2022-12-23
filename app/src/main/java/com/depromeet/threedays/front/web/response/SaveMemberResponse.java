package com.depromeet.threedays.front.web.response;

import com.depromeet.threedays.data.enums.CertificationSubject;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class SaveMemberResponse {

	private Long id;
	private String name;

	@JsonProperty("token")
	private TokenResponse tokenResponse;

	private String resource;
	private Boolean notificationConsent;
	private CertificationSubject certificationSubject;
}
