package com.depromeet.threedays.data.entity.member.certification;

import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class Certification {
	@Enumerated(EnumType.STRING)
	CertificationSubject certificationSubject;

	@Column String certificationId;
}
