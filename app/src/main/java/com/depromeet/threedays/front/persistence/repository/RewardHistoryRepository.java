package com.depromeet.threedays.front.persistence.repository;

import com.depromeet.threedays.data.entity.history.RewardHistoryEntity;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RewardHistoryRepository extends JpaRepository<RewardHistoryEntity, Long> {

	Long countByHabitId(Long habitId);

	Long countByHabitIdAndCreateAtIsAfter(final Long habitId, final LocalDateTime createAt);

	List<RewardHistoryEntity> findAllByHabitIdAndCreateAtAfter(
			final Long habitId, final LocalDateTime createAt);

	void deleteFirstByHabitIdOrderByCreateAtDesc(final Long habitId);
}
