package com.depromeet.threedays.front.repository.habit;

import com.depromeet.threedays.data.entity.habit.HabitEntity;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HabitRepository extends JpaRepository<HabitEntity, Long> {

	List<HabitEntity> findAllByMemberIdAndDeletedFalse(final Long memberId);
}
