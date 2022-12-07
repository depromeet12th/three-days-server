package com.depromeet.threedays.front.web.response;

import com.depromeet.threedays.data.enums.CertificationSubject;
import com.depromeet.threedays.front.domain.model.member.Token;
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
	private Token token;
	private String resource;
	public Boolean notificationConsent;
	public CertificationSubject certificationSubject;
}
