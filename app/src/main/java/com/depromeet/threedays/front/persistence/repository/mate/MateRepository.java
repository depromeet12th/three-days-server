package com.depromeet.threedays.front.persistence.repository.mate;

import com.depromeet.threedays.data.entity.mate.MateEntity;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface MateRepository extends JpaRepository<MateEntity, Long> {

	Optional<MateEntity> findByHabitId(final Long habitId);

	@Modifying(clearAutomatically = true)
	@Query("update MateEntity m set m.deleted = :deleted where  m.habitId = :habitId")
	void updateDeletedById(final Long habitId, final Boolean deleted);
}
