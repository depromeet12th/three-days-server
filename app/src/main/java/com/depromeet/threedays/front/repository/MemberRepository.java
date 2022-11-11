package com.depromeet.threedays.front.repository;

import com.depromeet.threedays.data.entity.member.MemberEntity;
import com.depromeet.threedays.data.enums.CertificationSubject;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<MemberEntity, Long> {

	Optional<MemberEntity> findById(final Long memberId);

	Optional<MemberEntity> findByIdAndAndCertificationSubject(
			final Long memberId, final CertificationSubject certificationSubject);
}
