package com.depromeet.threedays.front.domain.usecase.habit;

import com.depromeet.threedays.data.entity.habit.HabitAchievementEntity;
import com.depromeet.threedays.data.entity.habit.HabitEntity;
import com.depromeet.threedays.front.domain.converter.habit.HabitAchievementConverter;
import com.depromeet.threedays.front.domain.converter.habit.HabitConverter;
import com.depromeet.threedays.front.domain.model.habit.Habit;
import com.depromeet.threedays.front.domain.model.habit.HabitAchievement;
import com.depromeet.threedays.front.exception.ResourceNotFoundException;
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
        HabitEntity habitEntity = habitRepository.findById(habitId)
                                                 .orElseThrow(ResourceNotFoundException::new);
        HabitAchievementEntity habitAchievementEntity = habitAchievementRepository.findById(habitAchievementId)
                                                                                  .orElseThrow(ResourceNotFoundException::new);
        LocalDate achievementDate = habitAchievementEntity.getAchievementDate();
        LocalDate requestDate = LocalDate.now();

        if(!achievementDate.isEqual(requestDate)) {
            return null;
        }

        habitAchievementRepository.deleteById(habitAchievementId);
        HabitAchievementEntity beforeHabitAchievementEntity = habitAchievementRepository.findFirstByHabitIdOrderByAchievementDateDesc(habitId)
                                                                             .orElseThrow(ResourceNotFoundException::new);
        HabitAchievement habitAchievement = HabitAchievementConverter.from(beforeHabitAchievementEntity);

        Long totalReward = rewardHistoryRepository.countByHabitId(habitId);
        if (habitAchievementEntity.getSequence() % 3 == 0) {
            rewardHistoryRepository.deleteFirstByHabitIdOrderByCreateDateDesc(habitId);
            return HabitConverter.from(habitEntity, habitAchievement, totalReward - 1);
        }
        return HabitConverter.from(habitEntity, habitAchievement, totalReward);
    }
}
