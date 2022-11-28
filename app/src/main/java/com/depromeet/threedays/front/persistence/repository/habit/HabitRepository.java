package com.depromeet.threedays.front.persistence.repository.habit;

import com.depromeet.threedays.data.entity.habit.HabitEntity;
import com.depromeet.threedays.data.enums.HabitStatus;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface HabitRepository extends JpaRepository<HabitEntity, Long> {

	List<HabitEntity> findAllByMemberIdAndDeletedFalse(final Long memberId);
}
