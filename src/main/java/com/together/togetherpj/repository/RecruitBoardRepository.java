package com.together.togetherpj.repository;

import com.together.togetherpj.domain.Recruit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RecruitBoardRepository extends JpaRepository<Recruit, Long> {
    List<Recruit> findByTitleContaining(String keyword);
}
