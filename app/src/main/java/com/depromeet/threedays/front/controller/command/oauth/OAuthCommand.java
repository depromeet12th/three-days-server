package com.depromeet.threedays.front.controller.command.oauth;

import com.depromeet.threedays.data.entity.member.certification.CertificationSubject;
import javax.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class OAuthCommand {
	@NotBlank private CertificationSubject certificationSubject;
	@NotBlank private String accessToken;
	@NotBlank private String idToken;
}
