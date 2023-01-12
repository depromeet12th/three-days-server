package com.depromeet.threedays.front.domain.usecase.habit;

import com.depromeet.threedays.data.enums.HabitStatus;
import com.depromeet.threedays.data.enums.MateStatus;
import com.depromeet.threedays.data.enums.MemberStatus;
import com.depromeet.threedays.front.config.security.AuditorHolder;
import com.depromeet.threedays.front.domain.converter.habit.HabitConverter;
import com.depromeet.threedays.front.domain.converter.mate.MateConverter;
import com.depromeet.threedays.front.domain.model.habit.Habit;
import com.depromeet.threedays.front.domain.model.mate.Mate;
import com.depromeet.threedays.front.exception.MemberNotFoundException;
import com.depromeet.threedays.front.persistence.repository.habit.HabitAchievementRepository;
import com.depromeet.threedays.front.persistence.repository.habit.HabitRepository;
import com.depromeet.threedays.front.persistence.repository.mate.MateRepository;
import com.depromeet.threedays.front.persistence.repository.member.MemberRepository;
import com.depromeet.threedays.front.persistence.repository.notification.HabitNotificationRepository;
import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@Transactional
@RequiredArgsConstructor
public class DeleteHabitUseCase {

	private final MemberRepository memberRepository;
	private final HabitRepository repository;
	private final HabitAchievementRepository habitAchievementRepository;
	private final MateRepository mateRepository;

	private final HabitNotificationRepository habitNotificationRepository;

	public void execute(Long habitId) {
		Long memberId = AuditorHolder.get();
		memberRepository
				.findByIdAndStatus(memberId, MemberStatus.REGULAR)
				.orElseThrow(() -> new MemberNotFoundException(memberId));

		Habit source =
				repository.findByIdAndDeletedFalse(habitId).map(HabitConverter::from).orElse(null);

		if (source == null) {
			return;
		}

		Mate mate =
				mateRepository.findByHabitIdAndDeletedFalse(habitId).map(MateConverter::from).orElse(null);

		if (source.getStatus().equals(HabitStatus.ACTIVE)) {
			Long achievementCount = habitAchievementRepository.countByHabitId(habitId);

			if (mate != null || achievementCount != 0L) {
				this.archive(source, mate);
				return;
			}
		}

		this.delete(source, mate);
	}

	private void deleteNotification(final Habit source) {
		if (source == null || source.getNotification() == null) {
			return;
		}

		habitNotificationRepository.deleteAllByHabitId(source.getId());
	}

	private void delete(final Habit source, final Mate mate) {
		repository.save(HabitConverter.to(source).toBuilder().deleted(true).build());
		this.deleteMate(mate);
		this.deleteNotification(source);
	}

	private void archive(final Habit source, final Mate mate) {
		repository.save(HabitConverter.to(source).toBuilder().status(HabitStatus.ARCHIVED).build());
		this.archiveMate(mate);
		this.deleteNotification(source);
	}

	private void archiveMate(final Mate mate) {
		if (mate == null) {
			return;
		}

		mateRepository.save(MateConverter.to(mate).toBuilder().status(MateStatus.ARCHIVED).build());
	}

	private void deleteMate(final Mate mate) {
		if (mate == null) {
			return;
		}

		mateRepository.save(MateConverter.to(mate).toBuilder().deleted(true).build());
	}
}
