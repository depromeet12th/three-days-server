package com.depromeet.threedays.front.data.habit;

import com.depromeet.threedays.data.entity.habit.HabitAchievementEntity;
import com.depromeet.threedays.front.support.DateCalculator;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class FakeHabitAchievementEntity {
    private FakeHabitAchievementEntity() {
    }

    static HabitAchievementEntity createBy(Long habitId, int index) {
        return HabitAchievementEntity.builder()
                .habitId(habitId)
                .memberId(0L)
                .sequence(index + 1)
                .achievementDate(LocalDate.now().minusDays(index))
                .nextAchievementDate(DateCalculator.findNextDate(
                        FakeHabitEntity.dayOfWeeks,
                        LocalDate.now()))
                .createAt(LocalDateTime.now())
                .updateAt(LocalDateTime.now())
                .build();
    }

}
