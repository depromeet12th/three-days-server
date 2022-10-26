package com.depromeet.threedays.front.repository;

import com.depromeet.threedays.data.entity.member.MemberEntity;
import com.depromeet.threedays.data.entity.member.certification.CertificationSubject;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface MemberRepository extends JpaRepository<MemberEntity, Long> {

	Optional<MemberEntity> findById(final Long memberId);

	@Query(
			"select m from MemberEntity m where m.certification.certificationId = :id and m.certification.certificationSubject = :subject")
	Optional<MemberEntity> findByCertification(
			@Param(value = "id") final String id,
			@Param(value = "subject") final CertificationSubject subject);
}
