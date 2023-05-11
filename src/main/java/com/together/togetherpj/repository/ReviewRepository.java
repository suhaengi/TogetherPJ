package com.together.togetherpj.repository;

import com.together.togetherpj.domain.Review;
import com.together.togetherpj.domain.id.ReviewId;
import com.together.togetherpj.dto.ApplyingResponseDTO;
import com.together.togetherpj.dto.PastAppliedDTO;
import com.together.togetherpj.dto.ReviewResponseDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


import java.util.ArrayList;
import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, ReviewId> {
   /* @Modifying
    @Query("insert into Review values(:m_id, :comment,:daw, :qweet)")
    List<PastAppliedDTO> postReview(@Param("m_id")Long id, @Param("comment")String comment, @Param());*/

    @Query(  "select re.reviewer.nickname as reviewer, r.title as title, r.city as city," +
            " r.enddate as enddate, re.comment as comment from Review re, " +
            "Recruit r,  Applying a where a.recruit.id=r.id and re.applying.id=a.id and " +
            "re.reviewId.applyingId.applierId=:m_id")
    ArrayList<ReviewResponseDTO> getMyReview(@Param("m_id")Long id);


}
