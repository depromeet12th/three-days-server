package com.depromeet.threedays.front.persistence.repository.habit;

import com.depromeet.threedays.data.entity.habit.HabitEntity;
import com.depromeet.threedays.data.enums.HabitStatus;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface HabitRepository extends JpaRepository<HabitEntity, Long> {

	List<HabitEntity> findAllByMemberIdAndDeletedFalse(final Long memberId);

	@Modifying(clearAutomatically = true)
	@Query("update HabitEntity h set h.deleted = :deleted where  h.id = :habitId")
	void updateDeletedById(final Long habitId, final Boolean deleted);

	@Modifying(clearAutomatically = true)
	@Query("update HabitEntity h set h.status = :status where h.id = :habitId")
	void updateStatusById(final Long habitId, final HabitStatus status);
}
