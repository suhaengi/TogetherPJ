package com.together.togetherpj.repository;

import com.together.togetherpj.domain.Applying;
import com.together.togetherpj.dto.ApplyingResponseDTO;
import com.together.togetherpj.dto.PastAppliedDTO;
import org.springframework.data.jpa.repository.EntityGraph;
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
   /* @Query(nativeQuery = true, value ="select * from (select  r.recruitWriter.nickname as nickname, r.city as city " +
            ", r.enddate as enddate, r.id as id from Applying a, Recruit r, Member m" +
            " where   " +
            " m.id=a.applier.id and r.id=a.recruit.id and  a.applier.id=:m_id  and r.state='FINISHED'" +
            "UNION select m.nickname as nickname, r.city as city, r.enddate as enddate" +
            ", r.id as id from Applying a, Recruit r, Member m where m.id=a.applier.id and r.id=a.recruit.id and  " +
            "r.recruitWriter.id=:m_id  and r.state='FINISHED')")*/
    @EntityGraph(attributePaths = {"recruit"})
    @Query(" select distinct r.recruitWriter.nickname as nickname, r.city as city " +
            ", r.enddate as enddate, r.id as id from  Recruit r left outer join Applying a " +
            " on a.recruit.id=r.id where   " +
            "    (a.applier.id=:m_id or r.recruitWriter.id=:m_id)  and r.state='FINISHED'"
            )
    List<PastAppliedDTO> pastApply(@Param("m_id")Long id);


    //모집이 완료된 지난 동행참여 및 모집글에서 같이 갔던 동행인들 닉네임
    //미완성
    @Query("select  m.nickname as nickname" +
            " from  Recruit r left  join Member m on r.recruitWriter.id=m.id left  join" +
            " Applying a on m.id=a.applier.id" +
            " where " +
            "r.id=:r_id   and r.state='FINISHED'")
    List<PastAppliedDTO> pastApplyw(@Param("r_id")Long rid);


}