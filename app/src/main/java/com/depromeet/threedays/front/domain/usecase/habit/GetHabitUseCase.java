package com.depromeet.threedays.front.domain.usecase.habit;

import com.depromeet.threedays.data.entity.habit.HabitEntity;
import com.depromeet.threedays.data.entity.mate.MateEntity;
import com.depromeet.threedays.front.domain.converter.habit.HabitAchievementConverter;
import com.depromeet.threedays.front.domain.converter.habit.HabitConverter;
import com.depromeet.threedays.front.domain.converter.mate.MateConverter;
import com.depromeet.threedays.front.domain.converter.notification.HabitNotificationConverter;
import com.depromeet.threedays.front.domain.model.habit.Habit;
import com.depromeet.threedays.front.domain.model.habit.HabitAchievement;
import com.depromeet.threedays.front.domain.model.notification.Notification;
import com.depromeet.threedays.front.exception.ResourceNotFoundException;
import com.depromeet.threedays.front.persistence.repository.habit.HabitAchievementRepository;
import com.depromeet.threedays.front.persistence.repository.habit.HabitRepository;
import com.depromeet.threedays.front.persistence.repository.mate.MateRepository;
import com.depromeet.threedays.front.persistence.repository.notification.HabitNotificationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class GetHabitUseCase {

	private final HabitRepository repository;

	private final HabitAchievementRepository habitAchievementRepository;
	private final MateRepository mateRepository;

	private final HabitNotificationRepository habitNotificationRepository;

	public Habit execute(final Long id) {
		HabitEntity source = repository.findById(id).orElseThrow(ResourceNotFoundException::new);

		MateEntity mateEntity = mateRepository.findByHabitId(id).orElse(null);

		Long totalAchievementCount = habitAchievementRepository.countByHabitId(id);

		HabitAchievement lastHabitAchievement = habitAchievementRepository.findFirstByHabitIdOrderByAchievementDateDesc(
						id).map(HabitAchievementConverter::from)
				.orElse(HabitAchievement.builder().sequence(0).build());

		Notification notification =
				habitNotificationRepository.findFirstByHabitId(id)
						.map(HabitNotificationConverter::from)
						.orElse(null);

		return HabitConverter.from(source).toBuilder()
				.totalAchievementCount(totalAchievementCount)
				.habitAchievement(lastHabitAchievement)
				.mate(MateConverter.from(mateEntity))
				.notification(notification)
				.build();
	}
}
