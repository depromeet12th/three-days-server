package com.depromeet.threedays.front.domain.usecase.client;

import com.depromeet.threedays.data.enums.MemberStatus;
import com.depromeet.threedays.front.config.security.AuditorHolder;
import com.depromeet.threedays.front.domain.converter.client.ClientConverter;
import com.depromeet.threedays.front.domain.model.client.Client;
import com.depromeet.threedays.front.exception.MemberNotFoundException;
import com.depromeet.threedays.front.persistence.repository.client.ClientRepository;
import com.depromeet.threedays.front.persistence.repository.member.MemberRepository;
import com.depromeet.threedays.front.web.request.client.ClientRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@RequiredArgsConstructor
@Service
public class SaveClientUseCase {

	private final MemberRepository memberRepository;
	private final ClientRepository repository;

	public Client execute(final ClientRequest request) {
		Long memberId = AuditorHolder.get();
		memberRepository
				.findByIdAndStatus(memberId, MemberStatus.REGULAR)
				.orElseThrow(() -> new MemberNotFoundException(memberId));
		Client source =
				repository
						.findByMemberIdAndIdentificationKey(memberId, request.getIdentificationKey())
						.map(ClientConverter::from)
						.orElse(null);

		if (source == null) {
			return ClientConverter.from(repository.save(ClientConverter.to(memberId, request)));
		}

		return ClientConverter.from(
				repository.save(
						ClientConverter.to(memberId, request).toBuilder().id(source.getId()).build()));
	}
}
