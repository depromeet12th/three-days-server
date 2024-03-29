package com.depromeet.threedays.front.persistence.repository.member;

import com.depromeet.threedays.data.entity.member.MemberEntity;
import com.depromeet.threedays.data.enums.CertificationSubject;
import com.depromeet.threedays.data.enums.MemberStatus;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<MemberEntity, Long> {

	Optional<MemberEntity> findByIdAndStatus(Long memberId, MemberStatus status);

	Optional<MemberEntity> findByCertificationIdAndCertificationSubjectAndStatus(
			final String certificationId,
			final CertificationSubject certificationSubject,
			final MemberStatus status);

	List<MemberEntity> findAllByNotificationConsentAndStatus(
			final boolean consent, final MemberStatus status);
}
