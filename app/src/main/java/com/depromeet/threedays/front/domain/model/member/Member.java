package com.depromeet.threedays.front.domain.model.member;

import com.depromeet.threedays.data.enums.CertificationSubject;
import com.depromeet.threedays.data.enums.MemberStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.json.simple.JSONObject;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class Member {
	private Long id;
	private String name;
	private CertificationSubject certificationSubject;
	private MemberStatus status;
	private boolean notificationConsent;
	private JSONObject resource;
}
