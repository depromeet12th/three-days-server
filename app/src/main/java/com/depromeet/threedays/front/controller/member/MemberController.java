package com.depromeet.threedays.front.controller.member;

import com.depromeet.threedays.front.controller.command.member.SaveMemberCommand;
import com.depromeet.threedays.front.domain.model.Member;
import com.depromeet.threedays.front.domain.usecase.member.GetMemberUseCase;
import com.depromeet.threedays.front.domain.usecase.member.SaveMemberUseCase;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/v1/members")
@RequiredArgsConstructor
@RestController
public class MemberController {

	private final GetMemberUseCase getUseCase;
	private final SaveMemberUseCase saveUseCase;

	@PostMapping
	public ResponseEntity<Member> add(
			@RequestBody @Valid SaveMemberCommand command) {
		return ResponseEntity.ok(saveUseCase.execute(command));
	}

	@GetMapping("/{id}")
	public ResponseEntity<Member> read(@PathVariable Long id) {
		return ResponseEntity.ok(getUseCase.execute(id));
	}
}
