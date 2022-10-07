package com.depromeet.threedays.domain.member.repository;

import com.depromeet.threedays.domain.member.entity.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class MemberRepositoryImpl implements MemberRepository {

    private final MemberJpaRepository memberJpaRepository;

    @Override
    public Member save(Member member) {
        return memberJpaRepository.save(member);
    }

    @Override
    public Member findMemberById(Long id) {
        return memberJpaRepository.findById(id).orElseThrow(() -> new RuntimeException("유저가 없어요!"));
    }
}
