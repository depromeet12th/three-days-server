package com.depromeet.threedays.front.domain.usecase.mate;

import com.depromeet.threedays.front.config.security.AuditorHolder;
import com.depromeet.threedays.front.domain.converter.mate.MateConverter;
import com.depromeet.threedays.front.domain.model.mate.Mate;
import com.depromeet.threedays.front.persistence.repository.mate.MateRepository;
import com.depromeet.threedays.front.web.response.MateResponse;
import com.depromeet.threedays.front.web.response.converter.MateResponseConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class GetMateCheckUseCase {

	private final MateRepository repository;

	public MateResponse execute() {
		Mate mate =
				repository
						.findByMemberIdAndDeletedFalse(AuditorHolder.get())
						.map(MateConverter::from)
						.orElse(null);

		return MateResponseConverter.from(mate);
	}
}
