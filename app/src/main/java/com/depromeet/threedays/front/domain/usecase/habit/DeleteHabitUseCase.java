package com.depromeet.threedays.front.domain.usecase.habit;

import com.depromeet.threedays.data.entity.habit.HabitEntity;
import com.depromeet.threedays.data.entity.mate.MateEntity;
import com.depromeet.threedays.front.domain.converter.habit.HabitConverter;
import com.depromeet.threedays.front.domain.converter.mate.MateConverter;
import com.depromeet.threedays.front.domain.model.habit.Habit;
import com.depromeet.threedays.front.exception.ResourceNotFoundException;
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
    private final MateRepository mateRepository;

    public void execute(Long habitId) {
        HabitEntity entity = repository.findById(habitId)
                                            .orElseThrow(ResourceNotFoundException::new);
        MateEntity mateEntity = mateRepository.findByHabitId(HabitConverter.from(entity)
                                                                           .getHabitId())
                                              .orElseThrow(ResourceNotFoundException::new);

        Habit target = HabitConverter.from(entity, MateConverter.from(mateEntity));

        deleteAssociation(target);
    }

    private void deleteAssociation(Habit target) {
        if (target != null) {
            mateRepository.findByHabitId(target.getHabitId())
                          .orElseThrow(ResourceNotFoundException::new)
                          .deleteMate();
        }
    }
}
