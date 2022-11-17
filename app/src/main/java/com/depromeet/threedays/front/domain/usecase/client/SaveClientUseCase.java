package com.depromeet.threedays.front.domain.usecase.client;

import com.depromeet.threedays.front.domain.command.client.SaveClientCommand;
import com.depromeet.threedays.front.domain.converter.client.ClientConverter;
import com.depromeet.threedays.front.domain.model.client.Client;
import com.depromeet.threedays.front.repository.client.ClientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@RequiredArgsConstructor
@Service
public class SaveClientUseCase {
	private final ClientRepository clientRepository;

	public Client execute(SaveClientCommand command) {
		return ClientConverter.from(clientRepository.save(ClientConverter.to(command)));
	}
}
