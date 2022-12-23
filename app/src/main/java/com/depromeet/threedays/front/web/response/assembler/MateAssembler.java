package com.depromeet.threedays.front.web.response.assembler;

import com.depromeet.threedays.front.domain.converter.RewardHistoryConverter;
import com.depromeet.threedays.front.domain.model.RewardHistory;
import com.depromeet.threedays.front.domain.model.mate.Mate;
import com.depromeet.threedays.front.persistence.repository.RewardHistoryRepository;
import com.depromeet.threedays.front.web.response.MateResponse;
import com.depromeet.threedays.front.web.response.converter.MateResponseConverter;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MateAssembler {
	private final RewardHistoryRepository rewardHistoryRepository;

	public MateResponse toMateResponse(Mate mate) {
		if (mate == null) {
			return null;
		}
		List<RewardHistory> rewardHistories =
				rewardHistoryRepository
						.findAllByHabitIdAndCreateAtAfter(mate.getHabitId(), mate.getCreateAt())
						.stream()
						.map(RewardHistoryConverter::from)
						.collect(Collectors.toList());
		return MateResponseConverter.from(mate, rewardHistories);
	}
}
