package com.depromeet.threedays.front.domain.usecase.habit;

import com.depromeet.threedays.data.enums.MemberStatus;
import com.depromeet.threedays.front.config.security.AuditorHolder;
import com.depromeet.threedays.front.domain.converter.habit.HabitAchievementConverter;
import com.depromeet.threedays.front.domain.converter.habit.HabitConverter;
import com.depromeet.threedays.front.domain.model.DatePeriod;
import com.depromeet.threedays.front.domain.model.habit.Habit;
import com.depromeet.threedays.front.domain.model.habit.HabitAchievement;
import com.depromeet.threedays.front.exception.MemberNotFoundException;
import com.depromeet.threedays.front.exception.ResourceNotFoundException;
import com.depromeet.threedays.front.persistence.repository.habit.HabitAchievementRepository;
import com.depromeet.threedays.front.persistence.repository.habit.HabitRepository;
import com.depromeet.threedays.front.persistence.repository.member.MemberRepository;
import com.depromeet.threedays.front.web.request.habit.SearchHabitAchievementRequest;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class SearchHabitAchievementUseCase {

	private final MemberRepository memberRepository;
	private final HabitAchievementRepository repository;
	private final HabitRepository habitRepository;

	public List<HabitAchievement> execute(
			final Long habitId, final SearchHabitAchievementRequest request) {
		Long memberId = AuditorHolder.get();
		memberRepository
				.findByIdAndStatus(memberId, MemberStatus.REGULAR)
				.orElseThrow(() -> new MemberNotFoundException(memberId));

		Habit habit =
				habitRepository
						.findById(habitId)
						.map(HabitConverter::from)
						.orElseThrow(ResourceNotFoundException::new);

		DatePeriod searchDatePeriod =
				Optional.ofNullable(request.getDatePeriod())
						.orElse(new DatePeriod(habit.getCreateAt().toLocalDate(), LocalDate.now()));

		return repository
				.findAllByHabitIdAndAchievementDateBetween(
						habitId, searchDatePeriod.getFrom(), searchDatePeriod.getTo())
				.stream()
				.map(HabitAchievementConverter::from)
				.collect(Collectors.toList());
	}
}
