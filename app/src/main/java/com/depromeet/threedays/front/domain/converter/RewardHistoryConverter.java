package com.depromeet.threedays.front.domain.converter;

import com.depromeet.threedays.data.entity.history.RewardHistoryEntity;
import com.depromeet.threedays.front.domain.model.habit.Habit;
import java.time.LocalDateTime;
import lombok.experimental.UtilityClass;

@UtilityClass
public class RewardHistoryConverter {

	public static RewardHistoryEntity to(Habit habit) {
		return RewardHistoryEntity.builder()
				.habitId(habit.getHabitId())
				.memberId(habit.getMemberId())
				.createDate(LocalDateTime.now())
				.build();
	}
}
