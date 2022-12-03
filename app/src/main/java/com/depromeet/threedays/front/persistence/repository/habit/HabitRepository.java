package com.depromeet.threedays.front.persistence.repository.habit;

import com.depromeet.threedays.data.entity.habit.HabitEntity;
import com.depromeet.threedays.data.enums.HabitStatus;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HabitRepository extends JpaRepository<HabitEntity, Long> {

	List<HabitEntity> findAllByMemberIdAndDeletedFalseAndStatus(final Long memberId,
			final HabitStatus status);
}
