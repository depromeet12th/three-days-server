package com.depromeet.threedays.front.domain.converter;

import com.depromeet.threedays.data.entity.history.RewardHistoryEntity;
import com.depromeet.threedays.front.domain.model.habit.Habit;
import lombok.experimental.UtilityClass;

import java.time.LocalDateTime;

@UtilityClass
public class RewardHistoryConverter {

    public static RewardHistoryEntity to(Habit habit) {
        return RewardHistoryEntity.builder()
                .habitId(habit.getId())
                .memberId(habit.getMemberId())
                .createAt(LocalDateTime.now())
                .build();
    }
}
