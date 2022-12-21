package com.depromeet.threedays.front.domain.usecase.mate;

import com.depromeet.threedays.front.config.security.AuditorHolder;
import com.depromeet.threedays.front.domain.converter.mate.MateConverter;
import com.depromeet.threedays.front.domain.model.mate.Mate;
import com.depromeet.threedays.front.persistence.repository.mate.MateRepository;
import com.depromeet.threedays.front.web.response.MateResponse;
import com.depromeet.threedays.front.web.response.converter.MateResponseConverter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class GetMateCheckUseCase {

	private final MateRepository repository;

	public List<MateResponse> execute() {
		return repository.findByMemberIdAndDeletedFalse(AuditorHolder.get())
						  .stream()
						  .map(MateConverter::from)
						  .map(MateResponseConverter::from)
						  .collect(Collectors.toList());

	}
}
