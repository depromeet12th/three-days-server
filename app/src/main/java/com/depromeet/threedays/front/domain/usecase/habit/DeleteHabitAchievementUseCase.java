package com.depromeet.threedays.front.domain.usecase.habit;

import com.depromeet.threedays.front.domain.model.habit.Habit;
import com.depromeet.threedays.front.repository.RewardHistoryRepository;
import com.depromeet.threedays.front.repository.habit.HabitAchievementRepository;
import com.depromeet.threedays.front.repository.habit.HabitRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Component
@Transactional
@RequiredArgsConstructor
public class DeleteHabitAchievementUseCase {
    private final HabitRepository habitRepository;
    private final HabitAchievementRepository habitAchievementRepository;
    private final RewardHistoryRepository rewardHistoryRepository;

    public Habit execute(Long habitId, Long habitAchievementId) {

    }
}
