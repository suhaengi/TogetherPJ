package com.together.togetherpj.repository;

import com.together.togetherpj.domain.Member;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {
  Member findByEmail(String email);

  @EntityGraph(attributePaths = {"profileImg"})
  @Query("select m from Member m where m.email =:email")
  Optional<Member> findByIdWithImages(String email);
}
