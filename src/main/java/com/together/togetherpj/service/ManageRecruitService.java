package com.together.togetherpj.service;

import com.together.togetherpj.constant.State;
import com.together.togetherpj.domain.Applying;
import com.together.togetherpj.domain.Member;
import com.together.togetherpj.domain.Recruit;
import com.together.togetherpj.domain.Review;
import com.together.togetherpj.domain.id.ApplyingId;
import com.together.togetherpj.domain.id.ReviewId;
import com.together.togetherpj.dto.*;
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
public class ManageRecruitService {
    private final ModelMapper modelMapper;
    private final ApplyingRepository  repository;
    private final MemberRepository memberRepository;
    private final RecruitRepository recruitRepository;
    private final ReviewRepository reviewRepository;


    //내가 참여하는 동행게시글 내역
    public List<ApplyingResponseDTO> selectApplying(Authentication authentication){
        String email = authentication.getName();
        Member member = memberRepository.findByEmail(email).orElseThrow(() ->{
            throw new UsernameNotFoundException("아이디 혹은 비밀번호가 잘못됐습니다.");
        });

        List<ApplyingResponseDTO> list
                =repository.selectApplyingMember(member.getId());
        /*Member member = memberRepository.findByEmail(authentication.getName());
        List<Applying> list=member.getApplyingList();


        List<ExDTO> dtoList=list.stream().map(applying -> modelMapper
                .map(applying, ExDTO.class)).collect(Collectors.toList());*/

        return list;
    }
    //내가 모집장인 현재 동행게시글 내역
    public List<ApplyingResponseDTO> selectMyApplyingTitle(Authentication authentication){
        String email = authentication.getName();
        Member member = memberRepository.findByEmail(email).orElseThrow(() ->{
            throw new UsernameNotFoundException("아이디 혹은 비밀번호가 잘못됐습니다.");
        });

        List<ApplyingResponseDTO> list=repository.myApplyingTitle(member.getId());
       /* List<MyApplyResponseDTO> dtoList=list.stream().map(applying-> modelMapper
                .map(applying, MyApplyResponseDTO.class)).collect(Collectors.toList());*/

        return list;
    }
    //내가 모집장인 현재 동행게시글 참여자들
    public List<ApplyingResponseDTO> selectMyApplyingApplier(Authentication authentication){
        String email = authentication.getName();
        Member member = memberRepository.findByEmail(email).orElseThrow(() ->{
            throw new UsernameNotFoundException("아이디 혹은 비밀번호가 잘못됐습니다.");
        });

        List<ApplyingResponseDTO> list=repository.myApplyingApplier(member.getId());
       /* List<MyApplyResponseDTO> dtoList=list.stream().map(applying-> modelMapper
                .map(applying, MyApplyResponseDTO.class)).collect(Collectors.toList());*/

        return list;
    }

    //내가 모집장인 현재 동행게시글 멤버들
    public List<ApplyingResponseDTO> selectMyApplyingMember(Authentication authentication){
        String email = authentication.getName();
        Member member = memberRepository.findByEmail(email).orElseThrow(() ->{
            throw new UsernameNotFoundException("아이디 혹은 비밀번호가 잘못됐습니다.");
        });

        List<ApplyingResponseDTO> list=repository.myApplyingMember(member.getId());
       /* List<MyApplyResponseDTO> dtoList=list.stream().map(applying-> modelMapper
                .map(applying, MyApplyResponseDTO.class)).collect(Collectors.toList());*/

        return list;
    }
    //모집 완료된 동행게시글 내역
    public List<PastAppliedDTO> selectPastApply(Authentication authentication){
        String email = authentication.getName();
        Member member = memberRepository.findByEmail(email).orElseThrow(() ->{
            throw new UsernameNotFoundException("아이디 혹은 비밀번호가 잘못됐습니다.");
        });
        List<PastAppliedDTO> list=repository.pastApply(member.getId());

        return list;
    }

    //같이 갔던 동행인들의 닉네임 리스트
    public List<PastAppliedDTO> selectAppliedReview(Authentication authentication, Long rid){
        String email=authentication.getName();
        Member member=memberRepository.findByEmail(email).orElseThrow(()->{
            throw new UsernameNotFoundException("아이디 비번 잘못됐습니다");
        });
        List<PastAppliedDTO> list=repository.pastAppliedReview(rid,member.getId());

        return list;
    }

    //리뷰쓰기
    public void postReview(Authentication authentication, ReviewFormDTO reviewFormDTO) {
        String email=authentication.getName();

        Member writer=memberRepository.findByEmail(email).orElseThrow(()->{
            throw new UsernameNotFoundException("아이디 비번 잘못됐습니다");
        });

        Recruit recruit=recruitRepository.findById(reviewFormDTO.getRid())
                .orElseThrow(IllegalStateException::new);

        //작성당하는사람
        Member reviewed=memberRepository.findById(reviewFormDTO.getReviewedId()).orElseThrow(IllegalStateException::new);
        if(reviewFormDTO.isLike()){
            reviewed.setLike(reviewed.getLike()+1);
        }

        Applying applying1=repository.findById(new ApplyingId(reviewFormDTO.getReviewedId(),reviewFormDTO.getRid()))
                .orElseThrow(IllegalStateException::new);



        Review review= Review.builder()
                .reviewId(new ReviewId())
                .comment(reviewFormDTO.getComment())
                .reviewer(writer)
                .applying(applying1)
                .build();

        reviewRepository.save(review);
    }

    //리뷰보여주기
    public List<ReviewResponseDTO> selectMyReview(String email){
        Member member=memberRepository.findByEmail(email).orElseThrow(()->{
            throw new UsernameNotFoundException("아이디 비번 잘못됐습니다");
        });
        List<ReviewResponseDTO> list=reviewRepository.getMyReview(member.getId());

        return list;
    }

    public void changeState(Authentication authentication, ApplyingRequestDTO applyingRequestDTO ){
        Recruit recruit=recruitRepository.findById(applyingRequestDTO.getRid()).orElseThrow(()->{
            throw new UsernameNotFoundException("게시글 아님");
        });
        recruit.setState(State.FINISHED);
    }

    public void changeApplyIsOk(Authentication authentication, ApplyingRequestDTO applyingRequestDTO){
        Applying applying=repository.findById(new ApplyingId(applyingRequestDTO.getAid(),
                applyingRequestDTO.getRid())).orElseThrow(()->{
            throw new UsernameNotFoundException("게시글 아님");
        });


        applying.setOk(true);
        Recruit recruit = recruitRepository.findById(applyingRequestDTO.getRid()).get();
        recruit.setCurNum(recruit.getCurNum()+1L);

    }

    public void applyNo(Long applierId, Long recruitId){
        repository.deleteById(new ApplyingId(applierId,recruitId));
    };

    public void applyDel(Long applierId, Long recruitId){
        repository.deleteById(new ApplyingId(applierId,recruitId));

        Recruit recruit = recruitRepository.findById(recruitId).get();
        recruit.setCurNum(recruit.getCurNum()-1L);
    };

}
