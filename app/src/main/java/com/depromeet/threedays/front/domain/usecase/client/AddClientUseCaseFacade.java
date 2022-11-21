package com.depromeet.threedays.front.domain.usecase.client;

import com.depromeet.threedays.front.domain.converter.client.ClientCommandConverter;
import com.depromeet.threedays.front.domain.converter.client.ClientQueryConverter;
import com.depromeet.threedays.front.domain.model.client.Client;
import com.depromeet.threedays.front.web.request.client.ClientRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AddClientUseCaseFacade {

	private final GetClientUseCase getUseCase;
	private final SaveClientUseCase saveUseCase;
	private final UpdateClientUseCase updateUseCase;

	public void execute(Long memberId, ClientRequest request) {
		if (request == null || memberId == null) {
			return;
		}
		Client client = getUseCase.execute(ClientQueryConverter.from(memberId, request));
		if (client == null) {
			saveUseCase.execute(ClientCommandConverter.from(memberId, request));
		} else {
			updateUseCase.execute(ClientCommandConverter.fromUpdate(client.getId(), request));
		}
	}
}
