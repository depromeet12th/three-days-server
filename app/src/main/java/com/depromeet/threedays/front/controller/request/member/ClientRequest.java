package com.depromeet.threedays.front.controller.request.member;

import javax.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class ClientRequest {
	@NotBlank private String fcmToken;
	@NotBlank private String identificationKey;
}
