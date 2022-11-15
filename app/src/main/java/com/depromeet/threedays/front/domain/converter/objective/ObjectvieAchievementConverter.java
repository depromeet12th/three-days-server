package com.depromeet.threedays.front.domain.converter.objective;

import com.depromeet.threedays.data.entity.objective.ObjectiveAchievementEntity;
import com.depromeet.threedays.front.domain.model.objective.ObjectiveAchievement;
import lombok.experimental.UtilityClass;

@UtilityClass
public class ObjectvieAchievementConverter {

	public static ObjectiveAchievement from(ObjectiveAchievementEntity entity) {
		return ObjectiveAchievement.builder()
				.achievementDate(entity.getAchievementDate())
				.nextAchievementDate(entity.getNextAchievementDate())
				.objectiveAchievementId(entity.getId())
				.sequence(entity.getSequence())
				.build();
	}
}
