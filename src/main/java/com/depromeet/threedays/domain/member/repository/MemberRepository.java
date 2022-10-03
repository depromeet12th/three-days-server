package com.depromeet.threedays.domain.member.repository;

import com.depromeet.threedays.domain.member.entity.Member;

public interface MemberRepository {
    Member save(Member member);

    Member findMemberById(Long id);
}
