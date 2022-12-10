package com.depromeet.threedays.front.web.request.member;

import com.depromeet.threedays.data.enums.CertificationSubject;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class SignMemberRequest {

	private CertificationSubject certificationSubject;
	private String socialToken;
}
