package com.depromeet.threedays.front.domain.model.member;

import com.depromeet.threedays.data.enums.CertificationSubject;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class SaveMemberUseCaseResponse {

	private Long id;
	private String name;
	private Boolean isNew;
	private Token token;
	private String resource;
	public Boolean notificationConsent;
	public CertificationSubject certificationSubject;
}
