package com.depromeet.threedays.controller.member.dto.response;

import com.depromeet.threedays.domain.member.entity.Member;
import lombok.Builder;
import lombok.Getter;

@Getter
public class MemberResponseDto {
    private Long id;
    private String nickname;

    @Builder
    public MemberResponseDto(Long id, String nickname) {
        this.id = id;
        this.nickname = nickname;
    }

    public static final MemberResponseDto create(Member member) {
        return MemberResponseDto.builder()
                .id(member.getId())
                .nickname(member.getNickname())
                .build();
    }
}
