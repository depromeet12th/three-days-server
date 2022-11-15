package com.depromeet.threedays.front.repository.partner;

import com.depromeet.threedays.data.partner.PartnerEntity;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PartnerRepository extends JpaRepository<PartnerEntity, Long> {

	Optional<PartnerEntity> findByObjectiveId(final Long objectiveId);
}
