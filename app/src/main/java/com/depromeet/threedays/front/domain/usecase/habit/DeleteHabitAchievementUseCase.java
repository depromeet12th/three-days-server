package com.depromeet.threedays.front.domain.usecase.habit;

import com.depromeet.threedays.data.entity.habit.HabitAchievementEntity;
import com.depromeet.threedays.data.entity.habit.HabitEntity;
import com.depromeet.threedays.front.domain.converter.habit.HabitAchievementConverter;
import com.depromeet.threedays.front.domain.converter.habit.HabitConverter;
import com.depromeet.threedays.front.domain.model.habit.Habit;
import com.depromeet.threedays.front.domain.model.habit.HabitAchievement;
import com.depromeet.threedays.front.domain.validation.HabitAchievementValidator;
import com.depromeet.threedays.front.exception.ResourceNotFoundException;
import com.depromeet.threedays.front.persistence.repository.RewardHistoryRepository;
import com.depromeet.threedays.front.persistence.repository.habit.HabitAchievementRepository;
import com.depromeet.threedays.front.persistence.repository.habit.HabitRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional
@RequiredArgsConstructor
public class DeleteHabitAchievementUseCase {

	private final HabitRepository habitRepository;
	private final HabitAchievementRepository repository;
	private final RewardHistoryRepository rewardHistoryRepository;

	private final HabitAchievementValidator validator;

	public Habit execute(final Long habitId, final Long habitAchievementId) {
		HabitEntity habitEntity =
				habitRepository.findById(habitId).orElseThrow(ResourceNotFoundException::new);
		HabitAchievementEntity entity = repository.findById(habitAchievementId).orElse(null);

		HabitAchievement target = HabitAchievementConverter.from(entity);

		validator.validateDeleteConstraints(habitEntity, target);
		Habit habit = HabitConverter.from(habitEntity, this.delete(target));

		return HabitConverter.from(habit, rewardHistoryRepository.countByHabitId(habitId)).toBuilder()
				.totalAchievementCount(repository.countByHabitId(habitId))
				.build();
	}

	private HabitAchievement delete(final HabitAchievement target) {
		if (target == null) {
			return null;
		}
		this.deleteAssociations(target);

		repository.deleteById(target.getId());

		return repository
				.findFirstByHabitIdOrderByAchievementDateDesc(target.getHabitId())
				.map(HabitAchievementConverter::from)
				.orElse(null);
	}

	private void deleteAssociations(final HabitAchievement target) {
		if (target.getSequence() % 3 != 0) {
			return;
		}

		rewardHistoryRepository.deleteFirstByHabitIdOrderByCreateAtDesc(target.getHabitId());
	}
}
