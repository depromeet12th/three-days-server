package com.depromeet.threedays.front.domain.query;

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
public class GetMemberQuery {

	private Long certificationId;
	private CertificationSubject certificationSubject;
}
