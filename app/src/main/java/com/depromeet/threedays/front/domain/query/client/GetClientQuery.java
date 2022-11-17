package com.depromeet.threedays.front.domain.query.client;

import lombok.*;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class GetClientQuery {
	private Long memberId;
	private String identificationKey;
}
