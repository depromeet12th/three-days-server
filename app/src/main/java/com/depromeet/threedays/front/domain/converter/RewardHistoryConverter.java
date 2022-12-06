package com.depromeet.threedays.front.domain.converter;

import com.depromeet.threedays.data.entity.history.RewardHistoryEntity;
import com.depromeet.threedays.front.domain.model.RewardHistory;
import com.depromeet.threedays.front.domain.model.habit.Habit;
import java.time.LocalDateTime;
import lombok.experimental.UtilityClass;

@UtilityClass
public class RewardHistoryConverter {

	public static RewardHistoryEntity to(final Habit habit) {
		return RewardHistoryEntity.builder()
				.habitId(habit.getId())
				.memberId(habit.getMemberId())
				.createAt(LocalDateTime.now())
				.build();
	}

	public static RewardHistory from(final RewardHistoryEntity entity) {
		if (entity == null) {
			return null;
		}

		return RewardHistory.builder()
				.createAt(entity.getCreateAt())
				.build();
	}
}
