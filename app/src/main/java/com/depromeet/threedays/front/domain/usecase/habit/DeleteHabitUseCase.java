package com.depromeet.threedays.front.domain.usecase.habit;

import com.depromeet.threedays.data.entity.habit.HabitAchievementEntity;
import com.depromeet.threedays.data.entity.habit.HabitEntity;
import com.depromeet.threedays.data.entity.mate.MateEntity;
import com.depromeet.threedays.data.enums.HabitStatus;
import com.depromeet.threedays.front.domain.converter.habit.HabitAchievementConverter;
import com.depromeet.threedays.front.domain.converter.habit.HabitConverter;
import com.depromeet.threedays.front.domain.converter.mate.MateConverter;
import com.depromeet.threedays.front.domain.model.habit.Habit;
import com.depromeet.threedays.front.domain.model.habit.HabitAchievement;
import com.depromeet.threedays.front.domain.model.mate.Mate;
import com.depromeet.threedays.front.exception.ResourceNotFoundException;
import com.depromeet.threedays.front.persistence.repository.habit.HabitAchievementRepository;
import com.depromeet.threedays.front.persistence.repository.habit.HabitRepository;
import com.depromeet.threedays.front.persistence.repository.mate.MateRepository;
import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@Transactional
@RequiredArgsConstructor
public class DeleteHabitUseCase {
	private final HabitRepository repository;
	private final HabitAchievementRepository habitAchievementRepository;
	private final MateRepository mateRepository;

	public void execute(Long habitId) {
		HabitEntity entity = repository.findById(habitId).orElseThrow(ResourceNotFoundException::new);
		MateEntity mateEntity = mateRepository.findByHabitId(habitId).orElse(null);
		HabitAchievementEntity habitAchievementEntity =
				habitAchievementRepository
						.findFirstByHabitIdOrderByAchievementDateDesc(habitId)
						.orElse(null);

		Mate mate = MateConverter.from(mateEntity);
		HabitAchievement habitAchievement = HabitAchievementConverter.from(habitAchievementEntity);

		this.delete(HabitConverter.from(entity, habitAchievement, mate));
	}

	private void delete(Habit target) {
		if (target.getStatus().equals(HabitStatus.ACTIVE)) {
			this.archiveHabit(target);
		}

		if (target.getHabitAchievement() == null) {
			repository.updateDeletedById(target.getHabitId(), true);
		}
	}

	private void archiveHabit(Habit target) {
		repository.updateStatusById(target.getHabitId(), HabitStatus.ARCHIVED);
		this.deleteMate(target);
	}

	private void deleteMate(Habit target) {
		if (target.getMate() == null) {
			return;
		}
		mateRepository.updateDeletedById(target.getHabitId(), true);
	}
}
