package com.depromeet.threedays.front.domain.usecase;

import com.depromeet.threedays.data.entity.habit.HabitAchievementEntity;
import com.depromeet.threedays.data.entity.member.MemberEntity;
import com.depromeet.threedays.front.config.security.AuditorHolder;
import com.depromeet.threedays.front.domain.converter.habit.HabitConverter;
import com.depromeet.threedays.front.domain.model.DatePeriod;
import com.depromeet.threedays.front.domain.model.habit.Habit;
import com.depromeet.threedays.front.persistence.repository.RewardHistoryRepository;
import com.depromeet.threedays.front.persistence.repository.habit.HabitAchievementRepository;
import com.depromeet.threedays.front.persistence.repository.habit.HabitRepository;
import com.depromeet.threedays.front.persistence.repository.member.MemberRepository;
import com.depromeet.threedays.front.web.request.SearchRecordRequest;
import com.depromeet.threedays.front.web.response.RecordResponse;
import com.depromeet.threedays.front.web.response.converter.RecordHabitResponseConverter;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class SearchRecordUseCase {

	private final MemberRepository memberRepository;

	private final HabitRepository habitRepository;

	private final HabitAchievementRepository habitAchievementRepository;

	private final RewardHistoryRepository rewardHistoryRepository;

	public RecordResponse execute(final SearchRecordRequest request) {

		MemberEntity memberEntity =
				memberRepository.findById(AuditorHolder.get())
						.orElseThrow(IllegalArgumentException::new);

		DatePeriod datePeriod =
				Optional.ofNullable(request.getDatePeriod())
						.orElse(new DatePeriod(memberEntity.getCreateAt().toLocalDate(),
								LocalDate.now()));

		Long rewardCount =
				rewardHistoryRepository.countByMemberIdAndCreateAtBetween(
						memberEntity.getId(),
						datePeriod.getFrom().atStartOfDay(),
						datePeriod.getTo().atTime(LocalTime.MAX));

		List<HabitAchievementEntity> habitAchievementEntities =
				habitAchievementRepository.findAllByMemberIdAndAchievementDateBetween(
						memberEntity.getId(), datePeriod.getFrom(), datePeriod.getTo());

		List<Long> habitIds =
				habitAchievementEntities.stream()
						.map(HabitAchievementEntity::getHabitId)
						.collect(Collectors.toList());

		Long frequentHabitId = this.getFrequentId(this.calculateFrequentHabit(habitIds));

		Habit habit = getHabit(frequentHabitId);

		return RecordResponse.builder()
				.rewardCount(rewardCount)
				.achievementCount(habitAchievementEntities.size())
				.frequentHabit(RecordHabitResponseConverter.from(habit))
				.build();
	}

	private Map<Long, Integer> calculateFrequentHabit(final List<Long> habitIds) {
		Map<Long, Integer> achievementCountMap = new LinkedHashMap<>();

		for (Long habitId : habitIds) {
			achievementCountMap.merge(habitId, 1, (oldValue, initValue) -> oldValue + 1);
		}

		return achievementCountMap;
	}

	private Habit getHabit(final Long habitId) {
		if (habitId == null) {
			return null;
		}

		return habitRepository.findById(habitId).map(HabitConverter::from).orElse(null);
	}

	private Long getFrequentId(final Map<Long, Integer> map) {
		Long maxId = null;
		Integer max = Integer.MIN_VALUE;

		for (Entry<Long, Integer> entry : map.entrySet()) {
			Long key = entry.getKey();
			Integer value = entry.getValue();

			if (value > max) {
				maxId = key;
				max = value;
			}
		}

		return maxId;
	}
}
