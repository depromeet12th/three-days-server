package com.depromeet.threedays.front.domain.converter;

import com.depromeet.threedays.data.entity.habit.HabitEntity;
import com.depromeet.threedays.data.entity.history.RewardHistoryEntity;
import com.depromeet.threedays.front.domain.model.habit.HabitAchievement;
import lombok.experimental.UtilityClass;

import java.time.LocalDateTime;

@UtilityClass
public class RewardHistoryConverter {

    public static RewardHistoryEntity to(HabitEntity habitEntity, HabitAchievement habitAchievement) {
        return RewardHistoryEntity.builder()
                                  .habitId(habitEntity.getId())
                                  .memberId(habitEntity.getMemberId())
                                  .createDate(LocalDateTime.now())
                                  .build();
    }
}
