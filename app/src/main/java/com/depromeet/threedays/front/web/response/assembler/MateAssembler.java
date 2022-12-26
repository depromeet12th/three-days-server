package com.depromeet.threedays.front.web.response.assembler;

import com.depromeet.threedays.data.entity.mate.MateBubbleEntity;
import com.depromeet.threedays.data.entity.mate.MateEntity;
import com.depromeet.threedays.data.enums.MateType;
import com.depromeet.threedays.front.domain.converter.RewardHistoryConverter;
import com.depromeet.threedays.front.domain.converter.mate.MateConverter;
import com.depromeet.threedays.front.domain.model.RewardHistory;
import com.depromeet.threedays.front.domain.model.mate.Mate;
import com.depromeet.threedays.front.persistence.repository.RewardHistoryRepository;
import com.depromeet.threedays.front.persistence.repository.mate.MateBubbleRepository;
import com.depromeet.threedays.front.web.response.MateResponse;
import com.depromeet.threedays.front.web.response.converter.MateResponseConverter;
import java.time.LocalDate;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MateAssembler {
	private final RewardHistoryRepository rewardHistoryRepository;
	private final MateBubbleRepository mateBubbleRepository;

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
		return MateResponseConverter.from(
				mate, rewardHistories, getRandomMateBubble(mate.getCharacterType()));
	}

	public Mate toMate(MateEntity entity) {
		if (entity == null) {
			return null;
		}
		return MateConverter.from(entity, getRandomMateBubble(entity.getCharacterType()));
	}

	private String getRandomMateBubble(MateType type) {
		List<MateBubbleEntity> bubbles = mateBubbleRepository.findAllByCharacterType(type);
		Random random = new Random(LocalDate.now().toEpochDay());
		return bubbles.get(random.nextInt(bubbles.size())).getMessage();
	}
}
