package com.depromeet.threedays.front.persistence.repository.habit;

import com.depromeet.threedays.data.entity.habit.HabitEntity;
import com.depromeet.threedays.data.enums.HabitStatus;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HabitRepository extends JpaRepository<HabitEntity, Long> {

	List<HabitEntity> findAllByMemberIdAndDeletedFalseAndStatus(
			final Long memberId, final HabitStatus status);

	Optional<HabitEntity> findByIdAndDeletedFalse(final Long habitId);

	Boolean existsByIdAndDeletedFalse(final Long id);

	void deleteAllByMemberId(final Long memberId);

	Optional<List<HabitEntity>> findAllByMemberIdAndDeletedFalse(final Long memberId);
}
