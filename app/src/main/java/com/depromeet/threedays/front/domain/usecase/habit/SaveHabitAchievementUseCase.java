package com.depromeet.threedays.front.domain.usecase.habit;

import com.depromeet.threedays.data.entity.habit.HabitEntity;
import com.depromeet.threedays.data.enums.DayOfWeek;
import com.depromeet.threedays.front.controller.request.habit.SaveHabitAchievementRequest;
import com.depromeet.threedays.front.domain.model.habit.Habit;
import com.depromeet.threedays.front.repository.habit.HabitAchievementRepository;
import com.depromeet.threedays.front.repository.habit.HabitRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.EnumSet;

@Component
@Transactional
@RequiredArgsConstructor
public class SaveHabitAchievementUseCase {
    private final HabitRepository habitRepository;
    private final HabitAchievementRepository habitAchievementRepository;

    public Habit execute(Long habitId, final SaveHabitAchievementRequest request) {
        return null;
    }

    private int calculateDayDifference(HabitEntity habitEntity, LocalDate achievementDate) {
        EnumSet<DayOfWeek> dayOfWeeks = habitEntity.getDayOfWeeks();
        int achievementDayOfWeek = achievementDate.getDayOfWeek()
                                                  .getValue();

        int count = 0;
        int dayDifference = 0;

        for (DayOfWeek dayOfWeek : dayOfWeeks) {
            int objectiveDayOfWeek = dayOfWeek.getValue();
            if (achievementDayOfWeek < objectiveDayOfWeek) {
                dayDifference = objectiveDayOfWeek - achievementDayOfWeek;
                break;
            }
            count++;
        }

        if (count == dayOfWeeks.size()) {
            for (DayOfWeek dayOfWeek : dayOfWeeks) {
                int habitDayOfWeek = dayOfWeek.getValue();
                dayDifference = (habitDayOfWeek + 7) - achievementDayOfWeek;
                break;
            }
        }
        return dayDifference;
    }
}
