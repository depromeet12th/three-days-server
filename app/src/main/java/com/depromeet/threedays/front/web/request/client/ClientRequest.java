package com.depromeet.threedays.front.web.request.client;

import javax.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

@Builder(toBuilder = true)
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ClientRequest {

	@NotBlank
	@Length(max = 1000)
	private String fcmToken;

	@NotBlank
	@Length(max = 1000)
	private String identificationKey;
}
