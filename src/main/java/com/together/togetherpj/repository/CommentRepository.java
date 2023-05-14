package com.together.togetherpj.repository;

import com.together.togetherpj.domain.Comment;
import com.together.togetherpj.dto.CommentRequestDTO;
import com.together.togetherpj.dto.CommentResponseDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface CommentRepository extends JpaRepository<Comment, Long> {
    @Query("select r.commentWriter.nickname as nickname, r.content as content" +
            ", r.modDate as moddate from Comment r where r.recruit.id = :bno")
    List<CommentResponseDTO> listComment(@Param("bno")Long bno);

}
