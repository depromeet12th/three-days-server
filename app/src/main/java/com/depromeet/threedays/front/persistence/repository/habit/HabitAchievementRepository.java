package com.depromeet.threedays.front.persistence.repository.habit;

import com.depromeet.threedays.data.entity.habit.HabitAchievementEntity;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HabitAchievementRepository extends JpaRepository<HabitAchievementEntity, Long> {

	Optional<HabitAchievementEntity> findFirstByHabitIdOrderByAchievementDateDesc(
			final Long habitId);

	Long countByHabitId(final Long habitId);

	List<HabitAchievementEntity> findAllByHabitIdAndAchievementDateBetween(final Long habitId,
			final LocalDate startDate, final LocalDate endDate);
}
