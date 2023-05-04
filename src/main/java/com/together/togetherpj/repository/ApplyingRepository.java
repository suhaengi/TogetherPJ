package com.together.togetherpj.repository;

import com.together.togetherpj.domain.Applying;
import com.together.togetherpj.dto.ApplyingResponseDTO;
import com.together.togetherpj.dto.PastAppliedDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.ArrayList;
import java.util.List;

public interface ApplyingRepository extends JpaRepository<Applying, Long> {
    //지금 접속한 내 아이디와 동행그룹의 신청자아이디가 같을 때 나오는 현재동행그룹 데이터들,
    @Query(  "select a.recruit.title as title , a.isOk as isOk, a.recruit.recruitWriter.nickname as nickname" +
            " from Applying a, Recruit r, Member m where a.applier.id= :m_id and " +
            "r.id=a.recruit.id and m.id=a.applier.id and r.state='RECRUITING'")
    ArrayList<ApplyingResponseDTO> selectApplyingMember(@Param("m_id")Long id);

    //내가 모집장인 현재 동행그룹 리스트
    @Query("select a from Applying a, Recruit r, Member m" +
            " where r.recruitWriter.id=:m_id and r.id=a.recruit.id and m.id=a.applier.id" +
            " and r.state='RECRUITING'")
    List<Applying> myApplyingMember(@Param("m_id")Long id);

    //모집이 완료된 지난 동행참여 및 모집글
    @Query("select r.recruitWriter.nickname as nickname, r.city as city " +
            ", r.enddate as enddate from Applying a, Recruit r, Member m" +
            " where (a.applier.id=:m_id or r.recruitWriter.id=:m_id) " +
            "and m.id=a.applier.id and r.id=a.recruit.id  and r.state='FINISHED'")
    List<PastAppliedDTO> pastApply(@Param("m_id")Long id);


}
