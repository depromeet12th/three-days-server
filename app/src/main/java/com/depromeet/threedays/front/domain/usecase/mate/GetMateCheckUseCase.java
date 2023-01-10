package com.depromeet.threedays.front.domain.usecase.mate;

import com.depromeet.threedays.data.enums.MateStatus;
import com.depromeet.threedays.data.enums.MemberStatus;
import com.depromeet.threedays.front.config.security.AuditorHolder;
import com.depromeet.threedays.front.domain.converter.mate.MateConverter;
import com.depromeet.threedays.front.exception.MemberNotFoundException;
import com.depromeet.threedays.front.persistence.repository.mate.MateRepository;
import com.depromeet.threedays.front.persistence.repository.member.MemberRepository;
import com.depromeet.threedays.front.web.response.MateResponse;
import com.depromeet.threedays.front.web.response.assembler.MateAssembler;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class GetMateCheckUseCase {

	private final MemberRepository memberRepository;
	private final MateRepository mateRepository;
	private final MateAssembler mateAssembler;

	public List<MateResponse> execute() {
		Long memberId = AuditorHolder.get();
		memberRepository
				.findByIdAndStatus(memberId, MemberStatus.REGULAR)
				.orElseThrow(() -> new MemberNotFoundException(memberId));

		return mateRepository
				.findByMemberIdAndDeletedFalseAndStatus(AuditorHolder.get(), MateStatus.ACTIVE)
				.stream()
				.map(MateConverter::from)
				.map(mateAssembler::toMateResponse)
				.collect(Collectors.toList());
	}
}
