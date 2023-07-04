package com.depromeet.threedays.front.domain.command;

import com.depromeet.threedays.data.enums.CertificationSubject;
import com.depromeet.threedays.front.client.model.MemberInfo;
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
public class SaveMemberCommand {
	private String certificationId;
	private String certificationToken;
	private String name;
	private CertificationSubject certificationSubject;
	private MemberInfo memberInfo;
	private String resource;
	private Boolean notificationConsent;
}
