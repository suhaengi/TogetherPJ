package com.together.togetherpj.repository;

import com.together.togetherpj.domain.Review;
import com.together.togetherpj.domain.id.ReviewId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review, ReviewId> {
}
