package com.together.togetherpj.repository;

import com.together.togetherpj.domain.Member;
import com.together.togetherpj.domain.Recruit;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.persistence.TypedQuery;
import java.util.List;

public interface RecruitRepository extends JpaRepository<Recruit, Long>, RecruitRepositoryCustom {

    List<Recruit> findTop16ByOrderByModDateDesc();
}
