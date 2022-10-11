package com.depromeet.threedays.front.repository;

import com.depromeet.threedays.data.entity.MemberEntity;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<MemberEntity, Long> {

	Optional<MemberEntity> findById(final Long memberId);
}
