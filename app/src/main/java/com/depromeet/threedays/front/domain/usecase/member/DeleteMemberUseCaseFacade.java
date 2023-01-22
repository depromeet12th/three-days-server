package com.depromeet.threedays.front.domain.usecase.member;

import com.depromeet.threedays.front.config.security.AuditorHolder;
import com.depromeet.threedays.front.domain.usecase.client.DeleteClientUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class DeleteMemberUseCaseFacade {

	private final DeleteMemberUseCase deleteMemberUseCase;
	private final DeleteClientUseCase deleteClientUseCase;

	public void execute() {
		deleteMemberUseCase.execute();
		deleteClientUseCase.executeWithMemberWithdrawn(AuditorHolder.get());
	}
}
