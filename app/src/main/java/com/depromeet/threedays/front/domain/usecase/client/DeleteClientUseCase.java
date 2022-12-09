package com.depromeet.threedays.front.domain.usecase.client;

import com.depromeet.threedays.front.persistence.repository.client.ClientRepository;
import com.depromeet.threedays.front.web.request.client.DeleteClientRequest;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@RequiredArgsConstructor
@Service
public class DeleteClientUseCase {

	private final ClientRepository clientRepository;

	public void execute(@Valid DeleteClientRequest query) {
		clientRepository.deleteByIdentificationKey(query.getIdentificationKey());
	}
}
