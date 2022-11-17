package com.depromeet.threedays.front.domain.usecase.habit;

import com.depromeet.threedays.data.entity.habit.HabitAchievementEntity;
import com.depromeet.threedays.data.entity.habit.HabitEntity;
import com.depromeet.threedays.front.config.security.AuditorHolder;
import com.depromeet.threedays.front.domain.converter.habit.HabitConverter;
import com.depromeet.threedays.front.domain.converter.mate.MateConverter;
import com.depromeet.threedays.front.domain.model.habit.HabitAchievement;
import com.depromeet.threedays.front.domain.model.habit.HabitOverview;
import com.depromeet.threedays.front.domain.model.mate.Mate;
import com.depromeet.threedays.front.repository.RewardHistoryRepository;
import com.depromeet.threedays.front.repository.habit.HabitAchievementRepository;
import com.depromeet.threedays.front.repository.habit.HabitRepository;
import com.depromeet.threedays.front.repository.mate.MateRepository;
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

	public List<HabitOverview> execute() {
		List<HabitEntity> habitEntities =
				repository.findAllByMemberIdAndDeletedFalse(AuditorHolder.get());

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
		Mate mateData =
				mateRepository.findByHabitId(entity.getId()).map(MateConverter::from).orElse(null);

		return HabitConverter.from(entity, achievementData, rewardCount, mateData);
	}

	private HabitAchievement calculateSequence(HabitAchievementEntity achievementEntity) {
		if (achievementEntity == null
				|| achievementEntity.getNextAchievementDate().isAfter(LocalDate.now())) {
			return HabitAchievement.builder().sequence(0).build();
		}

		if (achievementEntity.getNextAchievementDate().isEqual(LocalDate.now())) {
			return HabitAchievement.builder()
					.habitAchievementId(achievementEntity.getId())
					.sequence(achievementEntity.getSequence())
					.build();
		}

		return HabitAchievement.builder().sequence(achievementEntity.getSequence()).build();
	}
}
