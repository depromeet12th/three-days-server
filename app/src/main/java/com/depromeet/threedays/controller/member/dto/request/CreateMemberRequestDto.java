package com.depromeet.threedays.controller.member.dto.request;

import com.depromeet.threedays.domain.member.entity.Member;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class CreateMemberRequestDto {
    @NotBlank
    private String nickname;

    public Member toEntity() {
        return Member.builder()
                .username(nickname)
                .build();
    }
}
