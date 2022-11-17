package com.depromeet.threedays.front.domain.command.client;

import lombok.*;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class SaveClientCommand {
	private Long memberId;
	private String fcmToken;
	private String identificationKey;
}
