package com.depromeet.threedays.front.domain.usecase.habit;

import com.depromeet.threedays.data.entity.habit.HabitEntity;
import com.depromeet.threedays.data.enums.MemberStatus;
import com.depromeet.threedays.front.config.security.AuditorHolder;
import com.depromeet.threedays.front.domain.converter.habit.HabitConverter;
import com.depromeet.threedays.front.domain.converter.notification.HabitNotificationConverter;
import com.depromeet.threedays.front.domain.model.habit.Habit;
import com.depromeet.threedays.front.domain.model.notification.Notification;
import com.depromeet.threedays.front.domain.validation.HabitValidator;
import com.depromeet.threedays.front.exception.MemberNotFoundException;
import com.depromeet.threedays.front.persistence.repository.habit.HabitRepository;
import com.depromeet.threedays.front.persistence.repository.member.MemberRepository;
import com.depromeet.threedays.front.persistence.repository.notification.HabitNotificationRepository;
import com.depromeet.threedays.front.web.request.habit.SaveHabitRequest;
import java.time.DayOfWeek;
import java.util.EnumSet;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional
@RequiredArgsConstructor
public class SaveHabitUseCase {

	private final MemberRepository memberRepository;
	private final HabitRepository repository;
	private final HabitNotificationRepository habitNotificationRepository;

	private final HabitValidator validator;

	public Habit execute(final SaveHabitRequest request) {
		Long memberId = AuditorHolder.get();
		memberRepository
				.findByIdAndStatus(memberId, MemberStatus.REGULAR)
				.orElseThrow(() -> new MemberNotFoundException(memberId));

		Habit data = HabitConverter.from(request);
		validator.validateCreateConstraints(data);

		return this.save(data);
	}

	private Habit save(Habit data) {
		Notification notification = data.getNotification();
		EnumSet<DayOfWeek> dayOfWeeks = data.getDayOfWeeks();
		HabitEntity habitEntity = repository.save(HabitConverter.to(data));
		// Habit data 는 id 가 없어서 habitEntity.id 가져오기위함
		Habit habit = HabitConverter.from(habitEntity);
		this.saveAssociation(habit, notification, dayOfWeeks);
		return HabitConverter.from(habitEntity, notification).toBuilder()
				.totalAchievementCount(0L)
				.build();
	}

	private void saveAssociation(
			Habit habit, Notification notification, EnumSet<DayOfWeek> dayOfWeeks) {
		if (notification == null) {
			return;
		}

		for (DayOfWeek dayOfWeek : dayOfWeeks) {
			habitNotificationRepository.save(
					HabitNotificationConverter.to(habit, notification, dayOfWeek));
		}
	}
}
