package com.depromeet.threedays.front.domain.usecase.habit;

import com.depromeet.threedays.data.entity.habit.HabitAchievementEntity;
import com.depromeet.threedays.data.entity.habit.HabitEntity;
import com.depromeet.threedays.front.domain.converter.habit.HabitAchievementConverter;
import com.depromeet.threedays.front.domain.converter.habit.HabitConverter;
import com.depromeet.threedays.front.domain.model.habit.Habit;
import com.depromeet.threedays.front.domain.model.habit.HabitAchievement;
import com.depromeet.threedays.front.domain.validation.HabitAchievementValidator;
import com.depromeet.threedays.front.exception.ResourceNotFoundException;
import com.depromeet.threedays.front.repository.RewardHistoryRepository;
import com.depromeet.threedays.front.repository.habit.HabitAchievementRepository;
import com.depromeet.threedays.front.repository.habit.HabitRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional
@RequiredArgsConstructor
public class DeleteHabitAchievementUseCase {
    private final HabitRepository habitRepository;
    private final HabitAchievementRepository habitAchievementRepository;
    private final RewardHistoryRepository rewardHistoryRepository;

    private final HabitAchievementValidator validator;

    public Habit execute(final Long habitId, final Long habitAchievementId) {
        HabitEntity habitEntity = habitRepository.findById(habitId)
                                                 .orElseThrow(ResourceNotFoundException::new);
        HabitAchievementEntity habitAchievementEntity = habitAchievementRepository.findById(habitAchievementId)
                                                                                  .orElseThrow(ResourceNotFoundException::new);

        HabitAchievement habitAchievement = HabitAchievementConverter.from(habitAchievementEntity);
        Habit data = HabitConverter.from(habitEntity, habitAchievement);

        validator.validateCancelDateConstraints(data);

        return this.delete(habitEntity, data);
    }
    private Habit delete(HabitEntity entity, Habit data) {
        Long habitId = data.getHabitId();
        Long habitAchievementId = data.getHabitAchievement()
                                      .getHabitAchievementId();

        habitAchievementRepository.deleteById(habitAchievementId);
        HabitAchievementEntity beforeHabitAchievementEntity = habitAchievementRepository.findFirstByHabitIdOrderByAchievementDateDesc(habitId)
                                                                                        .orElseThrow(ResourceNotFoundException::new);
        HabitAchievement beforeHabitAchievement = HabitAchievementConverter.from(beforeHabitAchievementEntity);
        Habit beforeData = HabitConverter.from(entity, beforeHabitAchievement);

        return this.deleteAssociation(habitId, data, beforeData);
    }

    private Habit deleteAssociation(Long habitId, Habit data, Habit beforeData) {
        Long totalReward = rewardHistoryRepository.countByHabitId(habitId);
        if (data.getHabitAchievement().getSequence() % 3 == 0) {
            rewardHistoryRepository.deleteFirstByHabitIdOrderByCreateDateDesc(habitId);
            return HabitConverter.from(beforeData, totalReward - 1);
        }
        return HabitConverter.from(beforeData, totalReward);
    }
}
