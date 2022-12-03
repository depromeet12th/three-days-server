package com.depromeet.threedays.front.persistence.repository.mate;

import com.depromeet.threedays.data.entity.mate.MateEntity;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MateRepository extends JpaRepository<MateEntity, Long> {

	Optional<MateEntity> findFirstByHabitIdOrderByCreateAtDesc(final Long habitId);

	Optional<MateEntity> findByHabitIdAndMemberIdAndDeletedFalse(
			final Long habitId, final Long memberId);

	Boolean existsByMemberIdAndDeletedFalse(final Long habitId);
}
