package com.depromeet.threedays.front.domain.usecase.objective;

import com.depromeet.threedays.data.entity.objective.ObjectiveAchievementEntity;
import com.depromeet.threedays.data.entity.objective.ObjectiveEntity;
import com.depromeet.threedays.front.config.security.AuditorHolder;
import com.depromeet.threedays.front.domain.converter.objective.ObjectiveConverter;
import com.depromeet.threedays.front.domain.converter.partner.PartnerConverter;
import com.depromeet.threedays.front.domain.model.objective.ObjectiveAchievement;
import com.depromeet.threedays.front.domain.model.objective.ObjectiveOverview;
import com.depromeet.threedays.front.domain.model.partner.Partner;
import com.depromeet.threedays.front.repository.RewardHistoryRepository;
import com.depromeet.threedays.front.repository.objective.ObjectiveAchievementRepository;
import com.depromeet.threedays.front.repository.objective.ObjectiveRepository;
import com.depromeet.threedays.front.repository.partner.PartnerRepository;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class SearchObjectiveUseCase {

	private final ObjectiveRepository repository;

	private final ObjectiveAchievementRepository objectiveAchievementRepository;

	private final RewardHistoryRepository rewardHistoryRepository;
	private final PartnerRepository partnerRepository;

	public List<ObjectiveOverview> execuete() {
		List<ObjectiveEntity> objectiveEntities =
				repository.findAllByMemberIdAndDeletedFalse(AuditorHolder.get());

		List<ObjectiveOverview> objectiveOverviews = new ArrayList<>();

		for (ObjectiveEntity objectiveEntity : objectiveEntities) {
			objectiveOverviews.add(this.setAssociation(objectiveEntity));
		}

		return objectiveOverviews;
	}

	private ObjectiveOverview setAssociation(ObjectiveEntity entity) {
		ObjectiveAchievementEntity achievementEntity =
				objectiveAchievementRepository
						.findFirstByObjectiveIdOrderByAchievementDateAsc(entity.getId())
						.orElse(null);
		ObjectiveAchievement achievementData = this.calculateSequence(achievementEntity);
		Long rewardCount = rewardHistoryRepository.countByObjectiveId(entity.getId());
		Partner partnerData =
				partnerRepository
						.findByObjectiveId(entity.getId())
						.map(PartnerConverter::from)
						.orElse(null);

		return ObjectiveConverter.from(entity, achievementData, rewardCount, partnerData);
	}

	private ObjectiveAchievement calculateSequence(ObjectiveAchievementEntity achievementEntity) {
		if (achievementEntity == null
				|| achievementEntity.getNextAchievementDate().isAfter(LocalDate.now())) {
			return ObjectiveAchievement.builder().sequence(0).build();
		}

		if (achievementEntity.getNextAchievementDate().isEqual(LocalDate.now())) {
			return ObjectiveAchievement.builder()
					.objectiveAchievementId(achievementEntity.getId())
					.sequence(achievementEntity.getSequence())
					.build();
		}

		return ObjectiveAchievement.builder().sequence(achievementEntity.getSequence()).build();
	}
}
