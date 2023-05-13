package com.together.togetherpj.repository;

import com.together.togetherpj.domain.Applying;
import com.together.togetherpj.domain.Member;
import com.together.togetherpj.domain.id.ApplyingId;
import com.together.togetherpj.dto.ApplyingResponseDTO;
import com.together.togetherpj.dto.PastAppliedDTO;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public interface ApplyingRepository extends JpaRepository<Applying, ApplyingId> {




    //지금 접속한 내 아이디와 동행그룹의 신청자아이디가 같을 때 나오는 현재동행그룹 데이터들,
    @Query(  "select a.recruit.title as title , a.isOk as isOk, a.recruit.recruitWriter.nickname as nickname" +
            " from Applying a, Recruit r, Member m where a.applier.id= :m_id and " +
            "r.id=a.recruit.id and m.id=a.applier.id and r.state='RECRUITING' and not r.recruitWriter.id=:m_id")
    ArrayList<ApplyingResponseDTO> selectApplyingMember(@Param("m_id")Long id);

    //내가 모집장인 현재 게시글제목
    @Query("select r.title as title, r.id as rid from  Recruit r" +
            " where r.recruitWriter.id=:m_id " +
            " and r.state='RECRUITING'")
    List<ApplyingResponseDTO> myApplyingTitle(@Param("m_id")Long id);

    //내가 모집장인 현재 동행그룹 참여자들 리스트
    @Query("select a.applier.nickname as nickname, a.recruit.id as rid, a.applier.id as aid" +
            " ,a.applier.email as email from  Recruit r, Applying a " +
            "where a.recruit.id=r.id and r.recruitWriter.id=:m_id " +
            " and r.state='RECRUITING' and not a.applier.id=:m_id and a.isOk=false")
    List<ApplyingResponseDTO> myApplyingApplier(@Param("m_id")Long id);

    //내가 모집장인 현재 동행그룹 동행인들 리스트
    @Query("select a.applier.nickname as nickname, a.recruit.id as rid, a.applier.id as aid," +
            "a.applier.email as email from  Recruit r, Applying a " +
            "where a.recruit.id=r.id and r.recruitWriter.id=:m_id " +
            " and r.state='RECRUITING' and not a.applier.id=:m_id and a.isOk=true")
    List<ApplyingResponseDTO> myApplyingMember(@Param("m_id")Long id);



    //모집이 완료된 지난 동행참여 및 모집글
    @EntityGraph(attributePaths = {"recruit"})
    @Query(" select distinct r.recruitWriter.nickname as nickname, r.city as city " +
            ", r.enddate as enddate, r.id as id from  Recruit r left outer join Applying a " +
            " on a.recruit.id=r.id where   " +
            "    (a.applier.id=:m_id )  and r.state='FINISHED'"
            )
    List<PastAppliedDTO> pastApply(@Param("m_id")Long id);


    //모집이 완료된 지난 동행참여 및 모집글에서 자기자신 빼고 같이 갔던 동행인들 닉네임
    //자기가 참가하거난 모집장이었던 동행게시글 id가 기본적으로 받아야함
   /* @Query(nativeQuery = true, value ="select * from ("+
            "select m.m_nick as nickname, m.m_id as reviewedId from recruit r, member m where r.c_writer_id=" +
                    "m.m_id and r.c_state='FINISHED' and " +
                    "r.c_id=:r_id and not m.m_id=:m_id union " +
            "select m.m_nick as nickname, m.m_id as reviewedId from recruit r, member m, applying a where " +
            " a.c_id=r.c_id  and a.c_applier_id=m.m_id and r.c_state='FINISHED' and" +
            "                    r.c_id=:r_id and not m.m_id=:m_id) A ")
    List<PastAppliedDTO> pastAppliedReview(@Param("r_id")Long rid, @Param("m_id")Long mid);*/

    @Query(nativeQuery = true, value =
            "select m.m_nick as nickname, m.m_id as reviewedId, r.c_id as id from recruit r, member m, applying a where " +
            " a.c_id=r.c_id  and a.c_applier_id=m.m_id and r.c_state='FINISHED' and" +
            "                    r.c_id=:r_id and not m.m_id=:m_id and a.c_applier_id not in" +
                    "(select re.reviewed_memberid from review re where re.c_id=:r_id and re.reviewer_id=:m_id)")
    List<PastAppliedDTO> pastAppliedReview(@Param("r_id")Long rid, @Param("m_id")Long mid);





}
