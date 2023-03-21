package com.depromeet.threedays.front.domain.usecase.client;

import com.depromeet.threedays.data.enums.MemberStatus;
import com.depromeet.threedays.front.config.security.AuditorHolder;
import com.depromeet.threedays.front.domain.converter.member.MemberConverter;
import com.depromeet.threedays.front.domain.model.member.Member;
import com.depromeet.threedays.front.domain.model.member.MemberEvent;
import com.depromeet.threedays.front.exception.MemberNotFoundException;
import com.depromeet.threedays.front.persistence.repository.client.ClientRepository;
import com.depromeet.threedays.front.persistence.repository.member.MemberRepository;
import com.depromeet.threedays.front.web.request.client.DeleteClientRequest;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

@Slf4j
@Transactional
@RequiredArgsConstructor
@Service
public class DeleteClientUseCase {

	private final MemberRepository memberRepository;
	private final ClientRepository clientRepository;

	public void execute(@Valid DeleteClientRequest query) {
		Long memberId = AuditorHolder.get();
		memberRepository
				.findByIdAndStatus(memberId, MemberStatus.REGULAR)
				.orElseThrow(() -> new MemberNotFoundException(memberId));

		clientRepository.deleteByIdentificationKey(query.getIdentificationKey());
	}

	@Async
	@TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
	public Member executeWhenMemberWithdrawn(MemberEvent event) {
		log.info("deleteClientUseCase execute() thread name : " + Thread.currentThread().getName());
		Long memberId = event.getMemberId();
		Member member =
				memberRepository
						.findByIdAndStatus(memberId, MemberStatus.WITHDRAWN)
						.map(MemberConverter::from)
						.orElseThrow(() -> new MemberNotFoundException(memberId));
		clientRepository.deleteAllByMemberId(memberId);
		return member;
	}
}
