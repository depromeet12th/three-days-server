package com.depromeet.threedays.front.persistence.repository.client;

import com.depromeet.threedays.data.entity.client.ClientEntity;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<ClientEntity, Long> {

	Optional<ClientEntity> findByMemberIdAndIdentificationKey(
			final Long memberId, final String identificationKey);

	void deleteAllByMemberId(Long memberId);

	void deleteByIdentificationKey(final String identificationKey);

	Optional<ClientEntity> findByMemberId(final Long memberId);
}
