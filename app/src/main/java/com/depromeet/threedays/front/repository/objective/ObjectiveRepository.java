package com.depromeet.threedays.front.repository.objective;

import com.depromeet.threedays.data.entity.objective.ObjectiveEntity;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ObjectiveRepository extends JpaRepository<ObjectiveEntity, Long> {

	List<ObjectiveEntity> findAllByMemberIdAndDeletedFalse(final Long memberId);
}
