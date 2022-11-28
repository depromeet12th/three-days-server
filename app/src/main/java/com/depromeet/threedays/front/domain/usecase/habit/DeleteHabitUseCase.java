package com.depromeet.threedays.front.domain.usecase.habit;

import com.depromeet.threedays.data.entity.habit.HabitEntity;
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
		HabitEntity entity = repository.findById(habitId).orElse(null);
		Mate mate = mateRepository.findByHabitId(habitId).map(MateConverter::from).orElse(null);
		HabitAchievement habitAchievement =
				habitAchievementRepository
						.findFirstByHabitIdOrderByAchievementDateDesc(habitId)
						.map(HabitAchievementConverter::from)
						.orElse(null);

		this.delete(HabitConverter.from(entity, habitAchievement, mate));
	}

	private void delete(Habit target) {
		if (target == null) {
			return;
		}

		if (target.getMate() != null || target.getHabitAchievement() != null) {
			this.archiveHabit(target);
			return;
		}

		repository.save(HabitConverter.to(target, true));
	}

	private void archiveHabit(Habit target) {
		repository.save(HabitConverter.to(target, HabitStatus.ARCHIVED));
		this.deleteMate(target);
	}

	private void deleteMate(Habit target) {
		if (target.getMate() == null) {
			return;
		}
		mateRepository.save(MateConverter.to(target.getMate(), true));
	}
}
