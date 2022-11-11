package com.depromeet.threedays.front.controller.member;

import com.depromeet.threedays.front.controller.command.oauth.SignMemberRequest;
import com.depromeet.threedays.front.domain.model.Member;
import com.depromeet.threedays.front.domain.usecase.member.SignMemberUseCase;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/v1/members")
@RequiredArgsConstructor
@RestController
public class MemberController {

	private final SignMemberUseCase signUseCase;

	@PostMapping
	public ResponseEntity<Member> sign(@RequestBody @Valid SignMemberRequest request) {
		return ResponseEntity.ok(signUseCase.execute(request));
	}
}
