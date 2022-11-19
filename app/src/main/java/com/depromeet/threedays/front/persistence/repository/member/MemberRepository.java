package com.depromeet.threedays.front.persistence.repository.member;

import com.depromeet.threedays.data.entity.member.MemberEntity;
import com.depromeet.threedays.data.enums.CertificationSubject;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<MemberEntity, Long> {

	Optional<MemberEntity> findById(final Long memberId);

	Optional<MemberEntity> findByCertificationIdAndCertificationSubject(
			final Long certificationId, final CertificationSubject certificationSubject);
}
