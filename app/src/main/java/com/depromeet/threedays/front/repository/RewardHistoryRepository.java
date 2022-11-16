package com.depromeet.threedays.front.repository;

import com.depromeet.threedays.data.entity.history.RewardHistoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RewardHistoryRepository extends JpaRepository<RewardHistoryEntity, Long> {

	Long countByHabitId(Long habitId);
}
