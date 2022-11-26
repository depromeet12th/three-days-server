package com.depromeet.threedays.front.domain.usecase.habit;

import com.depromeet.threedays.data.entity.habit.HabitAchievementEntity;
import com.depromeet.threedays.data.entity.habit.HabitEntity;
import com.depromeet.threedays.data.entity.mate.MateEntity;
import com.depromeet.threedays.data.enums.HabitStatus;
import com.depromeet.threedays.front.domain.converter.habit.HabitAchievementConverter;
import com.depromeet.threedays.front.domain.model.habit.HabitAchievement;
import com.depromeet.threedays.front.exception.ResourceNotFoundException;
import com.depromeet.threedays.front.persistence.repository.habit.HabitAchievementRepository;
import com.depromeet.threedays.front.persistence.repository.habit.HabitRepository;
import com.depromeet.threedays.front.persistence.repository.mate.MateRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;

@Component
@Transactional
@RequiredArgsConstructor
public class DeleteHabitUseCase {
    private final HabitRepository repository;
    private final HabitAchievementRepository habitAchievementRepository;
    private final MateRepository mateRepository;

    public void execute(Long habitId) {
        HabitEntity entity = repository.findById(habitId)
                                       .orElseThrow(ResourceNotFoundException::new);
        MateEntity mateEntity = mateRepository.findByHabitId(habitId)
                                              .orElse(null);
        HabitAchievementEntity habitAchievementEntity = habitAchievementRepository.findFirstByHabitIdOrderByAchievementDateDesc(habitId)
                                                                                  .orElse(null);

        HabitAchievement habitAchievement = HabitAchievementConverter.from(habitAchievementEntity);

        this.delete(entity, mateEntity, habitAchievement);
    }

    private void delete(HabitEntity entity, MateEntity mateEntity, HabitAchievement habitAchievement) {
        if (habitAchievement == null) {
            entity.deleteHabit();
        }

        if (mateEntity != null) {
            entity.changeStatusToArchived();
            this.deleteAssociation(mateEntity);
            return;
        }

        if (entity.getStatus()
                  .equals(HabitStatus.ACTIVE)) {
            entity.changeStatusToArchived();
            return;
        }

        if (entity.getStatus()
                  .equals(HabitStatus.ARCHIVED)) {
            entity.deleteHabit();
        }
    }

    private void deleteAssociation(MateEntity mateEntity) {
        mateEntity.deleteMate();
    }
}
