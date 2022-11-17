package com.depromeet.threedays.front.controller.request.member;

import javax.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder(toBuilder = true)
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ClientRequest {
	@NotBlank private String fcmToken;
	@NotBlank private String identificationKey;
}
