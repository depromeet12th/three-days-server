package com.depromeet.threedays.front.repository.objective;

import com.depromeet.threedays.data.entity.objective.ObjectiveAchievementEntity;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ObjectiveAchievementRepository
		extends JpaRepository<ObjectiveAchievementEntity, Long> {

	Optional<ObjectiveAchievementEntity> findFirstByObjectiveIdOrderByAchievementDateAsc(
			final Long objectiveId);
}
