package com.depromeet.threedays.front.domain.model.client;

import lombok.*;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class Client {
	private Long id;
	private Long memberId;
	private String identificationKey;
	private String fcmToken;
}
