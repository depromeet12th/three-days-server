package com.depromeet.threedays.front.persistence.repository.mate;

import com.depromeet.threedays.data.entity.mate.MateBubbleEntity;
import com.depromeet.threedays.data.enums.MateType;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MateBubbleRepository extends JpaRepository<MateBubbleEntity, Long> {
	List<MateBubbleEntity> findAllByCharacterType(MateType type);
}
