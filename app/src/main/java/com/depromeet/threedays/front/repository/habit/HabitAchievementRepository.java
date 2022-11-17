package com.depromeet.threedays.front.repository.habit;

import com.depromeet.threedays.data.entity.habit.HabitAchievementEntity;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HabitAchievementRepository extends JpaRepository<HabitAchievementEntity, Long> {

	Optional<HabitAchievementEntity> findFirstByHabitIdOrderByAchievementDateDesc(final Long habitId);
}
