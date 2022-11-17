package com.depromeet.threedays.front.domain.command.client;

import lombok.*;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class UpdateClientCommand {
	private Long id;
	private String fcmToken;
}
