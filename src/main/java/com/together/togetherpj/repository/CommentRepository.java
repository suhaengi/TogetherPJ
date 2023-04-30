package com.together.togetherpj.repository;

import com.together.togetherpj.domain.Comment;
import com.together.togetherpj.domain.id.CommentId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, CommentId> {
}
