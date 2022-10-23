package com.depromeet.threedays.front.repository;

import com.depromeet.threedays.data.entity.objective.ObjectiveEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ObjectiveRepository extends JpaRepository<ObjectiveEntity, Long> {}
