package com.depromeet.threedays.front.domain.model.member;

import com.depromeet.threedays.data.enums.CertificationSubject;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.json.simple.JSONObject;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class MemberOverview {
    private Long id;
    private String name;
    private CertificationSubject certificationSubject;
    private boolean notificationConsent;
    private JSONObject resource;
}
