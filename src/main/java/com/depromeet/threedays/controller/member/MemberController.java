package com.depromeet.threedays.controller.member;

import com.depromeet.threedays.controller.member.dto.request.CreateMemberRequestDto;
import com.depromeet.threedays.controller.member.dto.response.MemberResponseDto;
import com.depromeet.threedays.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequestMapping("/api/v1/members")
@RequiredArgsConstructor
@RestController
public class MemberController {

    private final MemberService memberService;

    @PostMapping
    public ResponseEntity<MemberResponseDto> createMember(@RequestBody @Valid CreateMemberRequestDto requestDto) {
        MemberResponseDto responseDto = memberService.createUser(requestDto);
        return ResponseEntity.ok(responseDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MemberResponseDto> findUserById(@PathVariable Long id) {
        MemberResponseDto responseDto = memberService.findUserById(id);
        return ResponseEntity.ok(responseDto);
    }
}
