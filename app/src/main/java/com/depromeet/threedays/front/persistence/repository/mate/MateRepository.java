package com.depromeet.threedays.front.persistence.repository.mate;

import com.depromeet.threedays.data.entity.mate.MateEntity;
import com.depromeet.threedays.data.enums.MateStatus;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MateRepository extends JpaRepository<MateEntity, Long> {

	Optional<MateEntity> findFirstByHabitIdAndDeletedFalseOrderByCreateAtDesc(final Long habitId);

	Optional<MateEntity> findFirstByHabitIdAndStatusAndDeletedFalseOrderByCreateAtDesc(
			final Long habitId, final MateStatus status);

	Optional<MateEntity> findByHabitIdAndDeletedFalse(final Long habitId);

	Boolean existsByMemberIdAndDeletedFalse(final Long habitId);

	Optional<MateEntity> findFirstByHabitIdAndStatusOrderByCreateAtDesc(
			final Long habitId, final MateStatus status);

	void deleteAllByMemberId(final Long memberId);

	Optional<MateEntity> findByMemberIdAndDeletedFalseAndStatus(
			final Long memberId, final MateStatus status);
}
