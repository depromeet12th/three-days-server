package com.depromeet.threedays.front.web.response.assembler;

import com.depromeet.threedays.data.entity.mate.MateBubbleEntity;
import com.depromeet.threedays.data.enums.MateType;
import com.depromeet.threedays.front.domain.converter.RewardHistoryConverter;
import com.depromeet.threedays.front.domain.model.RewardHistory;
import com.depromeet.threedays.front.domain.model.habit.Habit;
import com.depromeet.threedays.front.domain.model.mate.Mate;
import com.depromeet.threedays.front.persistence.repository.RewardHistoryRepository;
import com.depromeet.threedays.front.persistence.repository.mate.MateBubbleRepository;
import com.depromeet.threedays.front.web.response.HabitResponse;
import com.depromeet.threedays.front.web.response.converter.HabitResponseConverter;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class HabitAssembler {
	private final RewardHistoryRepository rewardHistoryRepository;
	private final MateBubbleRepository mateBubbleRepository;

	public HabitResponse toHabitResponse(Habit habit) {
		if (habit == null) {
			return null;
		}
		if (!habit.hasMate()) {
			return HabitResponseConverter.from(habit, Collections.emptyList());
		}
		Mate mate = habit.getMate();
		List<RewardHistory> rewardHistories =
				rewardHistoryRepository
						.findAllByHabitIdAndCreateAtAfter(mate.getHabitId(), mate.getCreateAt())
						.stream()
						.map(RewardHistoryConverter::from)
						.collect(Collectors.toList());
		return HabitResponseConverter.from(
				habit, rewardHistories, getRandomMateBubble(mate.getCharacterType(), mate.getId()));
	}

	private String getRandomMateBubble(MateType type, Long mateId) {
		List<MateBubbleEntity> bubbles = mateBubbleRepository.findAllByCharacterType(type);
		Random random = new Random(LocalDate.now().toEpochDay() - mateId);
		return bubbles.get(random.nextInt(bubbles.size())).getMessage();
	}
}
