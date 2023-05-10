package com.together.togetherpj.repository;

import com.together.togetherpj.domain.Review;
import com.together.togetherpj.domain.id.ReviewId;
import com.together.togetherpj.dto.PastAppliedDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, ReviewId> {
   /* @Modifying
    @Query("insert into Review values(:m_id, :comment,:daw, :qweet)")
    List<PastAppliedDTO> postReview(@Param("m_id")Long id, @Param("comment")String comment, @Param());*/
}
