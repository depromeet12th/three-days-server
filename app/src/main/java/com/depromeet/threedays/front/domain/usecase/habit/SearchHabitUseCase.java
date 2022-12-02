package com.depromeet.threedays.front.domain.usecase.habit;

import com.depromeet.threedays.data.entity.habit.HabitAchievementEntity;
import com.depromeet.threedays.data.entity.habit.HabitEntity;
import com.depromeet.threedays.front.config.security.AuditorHolder;
import com.depromeet.threedays.front.domain.converter.habit.HabitConverter;
import com.depromeet.threedays.front.domain.converter.mate.MateConverter;
import com.depromeet.threedays.front.domain.model.habit.HabitAchievement;
import com.depromeet.threedays.front.domain.model.habit.HabitOverview;
import com.depromeet.threedays.front.domain.model.mate.Mate;
import com.depromeet.threedays.front.persistence.repository.RewardHistoryRepository;
import com.depromeet.threedays.front.persistence.repository.habit.HabitAchievementRepository;
import com.depromeet.threedays.front.persistence.repository.habit.HabitRepository;
import com.depromeet.threedays.front.persistence.repository.mate.MateRepository;
import com.depromeet.threedays.front.web.request.habit.SearchHabitRequest;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class SearchHabitUseCase {

	private final HabitRepository repository;

	private final HabitAchievementRepository habitAchievementRepository;

	private final RewardHistoryRepository rewardHistoryRepository;
	private final MateRepository mateRepository;

	public List<HabitOverview> execute(
			final SearchHabitRequest request) {
		List<HabitEntity> habitEntities =
				repository.findAllByMemberIdAndDeletedFalseAndStatus(AuditorHolder.get(),
						request.getStatus());

		List<HabitOverview> habitOverviews = new ArrayList<>();

		for (HabitEntity habitEntity : habitEntities) {
			habitOverviews.add(this.setAssociation(habitEntity));
		}

		return habitOverviews;
	}

	private HabitOverview setAssociation(HabitEntity entity) {
		HabitAchievementEntity achievementEntity =
				habitAchievementRepository
						.findFirstByHabitIdOrderByAchievementDateDesc(entity.getId())
						.orElse(null);
		HabitAchievement achievementData = this.calculateSequence(achievementEntity);
		Long rewardCount = rewardHistoryRepository.countByHabitId(entity.getId());
		Mate mate =
				mateRepository.findFirstByHabitIdOrderByCreateAtDesc(entity.getId())
						.map(MateConverter::from).orElse(null);

		return HabitConverter.from(entity, achievementData, rewardCount, mate);
	}

	private HabitAchievement calculateSequence(HabitAchievementEntity achievementEntity) {
		if (achievementEntity == null
				|| achievementEntity.getNextAchievementDate().isBefore(LocalDate.now())) {
			return HabitAchievement.builder().sequence(0).build();
		}

		if (achievementEntity.getAchievementDate().isEqual(LocalDate.now())) {
			return HabitAchievement.builder()
					.habitAchievementId(achievementEntity.getId())
					.sequence(achievementEntity.getSequence())
					.build();
		}

		return HabitAchievement.builder().sequence(achievementEntity.getSequence()).build();
	}
}
