package com.depromeet.threedays.front.web.request.member;

import com.depromeet.threedays.data.enums.CertificationSubject;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class SignMemberRequest {

	@NotNull private CertificationSubject certificationSubject;
	@NotBlank private String accessToken;
	@NotBlank private String fcmToken;
	@NotBlank private String identificationKey;
}
