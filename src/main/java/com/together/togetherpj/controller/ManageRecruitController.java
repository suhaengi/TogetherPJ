package com.together.togetherpj.controller;

import com.together.togetherpj.dto.*;
import com.together.togetherpj.service.MemberService;
import com.together.togetherpj.service.ManageRecruitService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/manage")
@Slf4j
@RequiredArgsConstructor
public class ManageRecruitController {

    private final MemberService memberService;
    private final PasswordEncoder passwordEncoder;
    private final ManageRecruitService recruitService;


    //내가 참여하는 현재진행중인 동행목록
    @GetMapping("/myParticipate")
    public String todayList(Model model,  Authentication authentication){
        List<ApplyingResponseDTO> applyingResponseDTOList=recruitService.selectApplying(authentication);
        model.addAttribute("applyingDTO", applyingResponseDTOList);

        List<MyApplyResponseDTO> myApplyResponseDTOList=recruitService.selectMyApply(authentication);
        model.addAttribute("myApplyDTO", myApplyResponseDTOList);

        return "myRecruit_test";
    }

    @GetMapping("/pastParticipate")
    public String pastList(Model model, Authentication authentication){
        List<PastAppliedDTO> pastApplyList=recruitService.selectPastApply(authentication);
        model.addAttribute("pastApplyDTO", pastApplyList);

        return "user/pastParticipate";
    }

    @GetMapping("/createReview")
    public String getCreateReview(Long rid, Authentication authentication, Model model){
        List<PastAppliedDTO> appliedReview=recruitService.selectAppliedReview(authentication, rid);
        model.addAttribute("appliedNickname", appliedReview);

        return "user/Review";
    }

    @PostMapping("/createReview")
    public String  createPostReview(@Valid ReviewFormDTO reviewFormDto,
                                  BindingResult bindingResult,
                                  Authentication authentication,
                                  Model model){

        if (bindingResult.hasErrors()) {
            //log.info(reviewFormDto.getReviewedId());
            return "user/Review";
        }

        try {
            log.info(reviewFormDto.getReviewedId().toString());
            log.info(reviewFormDto.getComment());
            reviewFormDto.setRid(reviewFormDto.getRid());
            log.info(reviewFormDto.toString());

            recruitService.postReview(authentication, reviewFormDto);
        } catch (IllegalStateException e) {
            model.addAttribute("errorMessage", e.getMessage());
            return "user/Review";
        }

        return "redirect:/manage/pastParticipate";

    }



}
