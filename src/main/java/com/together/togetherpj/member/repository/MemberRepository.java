package com.together.togetherpj.member.repository;

import com.together.togetherpj.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {
  Member findByEmail(String email);
}
