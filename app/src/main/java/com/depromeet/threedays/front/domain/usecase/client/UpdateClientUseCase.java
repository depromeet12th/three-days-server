package com.depromeet.threedays.front.domain.usecase.client;

import com.depromeet.threedays.front.domain.command.client.UpdateClientCommand;
import com.depromeet.threedays.front.exception.ResourceNotFoundException;
import com.depromeet.threedays.front.repository.client.ClientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@RequiredArgsConstructor
@Service
public class UpdateClientUseCase {

	private final ClientRepository clientRepository;

	public void execute(UpdateClientCommand query) {
		clientRepository
				.findById(query.getId())
				.orElseThrow(() -> new ResourceNotFoundException("member.not.found"))
				.updateFcmToken(query.getFcmToken());
	}
}
