package com.depromeet.threedays.service;

import com.depromeet.threedays.controller.member.dto.request.CreateMemberRequestDto;
import com.depromeet.threedays.controller.member.dto.response.MemberResponseDto;
import com.depromeet.threedays.domain.member.entity.Member;
import com.depromeet.threedays.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class MemberService {

    private final MemberRepository memberRepository;

    public MemberResponseDto createUser(CreateMemberRequestDto requestDto) {
        Member createdMember = memberRepository.save(requestDto.toEntity());
        return MemberResponseDto.create(createdMember);
    }

    public MemberResponseDto findUserById(Long id) {
        Member member = memberRepository.findMemberById(id);
        return MemberResponseDto.create(member);
    }
}
