package com.together.togetherpj.controller;

import com.together.togetherpj.dto.*;
import com.together.togetherpj.service.ManageRecruitService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
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

    private final PasswordEncoder passwordEncoder;
    private final ManageRecruitService recruitService;



    //내가 참여하는 현재진행중인 동행목록
    @GetMapping("/myParticipate")
    public String todayList(Model model,  Authentication authentication){
        List<ApplyingResponseDTO> applyingResponseDTOList=recruitService.selectApplying(authentication);
        model.addAttribute("applyingDTO", applyingResponseDTOList);

        List<ApplyingResponseDTO> myApplyResponseDTOList=recruitService.selectMyApplyingTitle(authentication);
        model.addAttribute("myApplyTitleDTO", myApplyResponseDTOList);
        //신청인들 ISOK=FALSE
        List<ApplyingResponseDTO> myApplyingApplierList=recruitService.selectMyApplyingApplier(authentication);
        model.addAttribute("myApplierDTO", myApplyingApplierList);
        //신청허락된 동행인들 ISOK=TRUE
        List<ApplyingResponseDTO> myApplyingMemberList=recruitService.selectMyApplyingMember(authentication);
        model.addAttribute("myMemberDTO", myApplyingMemberList);
        return "myRecruit_test";
    }

    @PostMapping("/changeState")
    public String changeState(Model model, Authentication authentication
    , @Valid ApplyingRequestDTO applyingRequestDTO){

        try {
            log.info("---------------------------");
            log.info(applyingRequestDTO.getRid().toString());
            log.info("++++++++++++++");
            applyingRequestDTO.setRid(applyingRequestDTO.getRid());
            recruitService.changeState(authentication, applyingRequestDTO);
        } catch (IllegalStateException e) {
            model.addAttribute("errorMessage", e.getMessage());
            return "";
        }

        return "redirect:/manage/myParticipate";
    }

    @PostMapping("/applyIsOk")
    public String changeIsOk(Model model, Authentication authentication
            , @Valid ApplyingRequestDTO applyingRequestDTO){

        try {

            applyingRequestDTO.setRid(applyingRequestDTO.getRid());
            applyingRequestDTO.setAid(applyingRequestDTO.getAid());
            recruitService.changeApplyIsOk(authentication, applyingRequestDTO);
        } catch (IllegalStateException e) {
            model.addAttribute("errorMessage", e.getMessage());
            return "";
        }

        return "redirect:/manage/myParticipate";
    }

    //동행거절
    @PostMapping("/applyIsNo")
    public String applydel(Long rid,Long aid){
        recruitService.applyNo(aid,rid);
        return "redirect:/manage/myParticipate";
    }

    //동행 받았는데 다시 취소시키기
    @PostMapping("/applyDel")
    public String apply(Long rid,Long aid){
        recruitService.applyDel(aid,rid);
        return "redirect:/manage/myParticipate";
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
                                  Authentication authentication,@RequestParam(value = "like",required = false)boolean like,
                                  Model model){

        if (bindingResult.hasErrors()) {
            //log.info(reviewFormDto.getReviewedId());
            return "user/Review";
        }

        try {

            reviewFormDto.setLike(like);
            recruitService.postReview(authentication, reviewFormDto);
            log.info("+++++++++++++++++++++++++++");
        } catch (IllegalStateException e) {
            model.addAttribute("errorMessage", e.getMessage());
            return "user/createReview";
        }

        return "redirect:/manage/pastParticipate";

    }



}
