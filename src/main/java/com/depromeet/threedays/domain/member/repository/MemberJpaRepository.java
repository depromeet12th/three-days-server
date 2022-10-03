package com.depromeet.threedays.domain.member.repository;

import com.depromeet.threedays.domain.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberJpaRepository extends JpaRepository<Member, Long> {
}
