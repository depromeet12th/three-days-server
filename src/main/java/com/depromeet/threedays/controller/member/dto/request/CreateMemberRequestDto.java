package com.depromeet.threedays.controller.member.dto.request;

import com.depromeet.threedays.domain.member.entity.Member;
import lombok.*;

import javax.validation.constraints.NotBlank;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class CreateMemberRequestDto {
    @NotBlank
    private String nickname;

    private String test;

    public Member toEntity() {
        return Member.builder()
                .username(nickname)
                .build();
    }
}
