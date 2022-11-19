package com.depromeet.threedays.front.domain.usecase.client;

import com.depromeet.threedays.front.domain.converter.client.ClientConverter;
import com.depromeet.threedays.front.domain.model.client.Client;
import com.depromeet.threedays.front.domain.query.client.GetClientQuery;
import com.depromeet.threedays.front.persistence.repository.client.ClientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@RequiredArgsConstructor
@Service
public class GetClientUseCase {

	private final ClientRepository clientRepository;

	public Client execute(GetClientQuery query) {
		return ClientConverter.from(
				clientRepository
						.findByMemberIdAndIdentificationKey(query.getMemberId(), query.getIdentificationKey())
						.orElse(null));
	}
}
