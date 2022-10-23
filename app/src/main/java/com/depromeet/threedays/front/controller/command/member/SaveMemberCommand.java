package com.depromeet.threedays.front.controller.command.member;

import javax.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class SaveMemberCommand {
	@NotBlank private String name;
}
