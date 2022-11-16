package com.depromeet.threedays.front.repository.mate;

import com.depromeet.threedays.data.entity.mate.MateEntity;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MateRepository extends JpaRepository<MateEntity, Long> {

	Optional<MateEntity> findByHabitId(final Long habitId);
}
