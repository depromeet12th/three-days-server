package com.depromeet.threedays.front.data.habit;

import com.depromeet.threedays.data.entity.habit.HabitAchievementEntity;
import com.depromeet.threedays.front.support.DateCalculator;
import java.time.LocalDate;

public class FakeHabitAchievementEntity {

	static HabitAchievementEntity createBy(Long habitId, int index) {
		return HabitAchievementEntity.builder()
				.habitId(habitId)
				.memberId(0L)
				.achievementDate(LocalDate.now().minusDays(index))
				.nextAchievementDate(DateCalculator.findNextDate(
						FakeHabitEntity.dayOfWeeks,
						LocalDate.now()))
				.sequence(index + 1)
				.build();
	}

}
