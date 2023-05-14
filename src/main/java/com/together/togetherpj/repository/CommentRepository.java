package com.together.togetherpj.repository;

import com.together.togetherpj.domain.Comment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    @Query("select r from Comment r where r.recruit.id = :bno")
    Page<Comment> listOfBoard(Long bno, Pageable pageable);

}
