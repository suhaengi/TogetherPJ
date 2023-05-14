package com.together.togetherpj.repository;

import com.together.togetherpj.domain.Recruit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RecruitBoardRepository extends JpaRepository<Recruit, Long> {
    //리스트페이지 검색기능
    List<Recruit> findByTitleContainingOrCityContaining(String keyword1, String keyword2);

    //viewcount 기능
    @Modifying
    @Query("update Recruit p set p.viewcount = p.viewcount + 1 where p.id = :id")
    int updateView(Long id);
}
