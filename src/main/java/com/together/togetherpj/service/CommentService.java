package com.together.togetherpj.service;

import com.together.togetherpj.domain.Comment;
import com.together.togetherpj.domain.Member;
import com.together.togetherpj.domain.Recruit;
import com.together.togetherpj.dto.CommentRequestDTO;
import com.together.togetherpj.dto.CommentResponseDTO;
import com.together.togetherpj.dto.PastAppliedDTO;
import com.together.togetherpj.repository.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Log4j2
@RequiredArgsConstructor
@Transactional
public class CommentService {
    private final ModelMapper modelMapper;
    private final ApplyingRepository applyingRepository;
    private final MemberRepository memberRepository;
    private final RecruitRepository recruitRepository;
    private final ReviewRepository reviewRepository;
    private final CommentRepository commentRepository;

    public void createComment(Authentication authentication, CommentRequestDTO commentRequestDTO){
        String email=authentication.getName();

        Member replyWriter=memberRepository.findByEmail(email).orElseThrow(()->{
            throw new UsernameNotFoundException("아이디 비번 잘못됐습니다");
        });

        Recruit recruit1=recruitRepository.findById(commentRequestDTO.getBno()).orElseThrow(()->{
            throw new UsernameNotFoundException("게시글 아님");
        });

        Comment comment=Comment.builder()
                .commentWriter(replyWriter)
                .content(commentRequestDTO.getReply())
                .recruit(recruit1)
                .build();

        commentRepository.save(comment);

    }

    public List<CommentResponseDTO> selectComment(Long rid){

        List<CommentResponseDTO> list=commentRepository.listComment(rid);

        return list;
    }


}
