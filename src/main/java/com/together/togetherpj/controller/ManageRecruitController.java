package com.together.togetherpj.controller;

import com.together.togetherpj.dto.ApplyingResponseDTO;
import com.together.togetherpj.dto.MyApplyResponseDTO;
import com.together.togetherpj.dto.PastAppliedDTO;
import com.together.togetherpj.service.MemberService;
import com.together.togetherpj.service.ManageRecruitService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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

        return "myRecruit";
    }

    @GetMapping("/pastParticipate")
    public String pastList(Model model, Authentication authentication){
        List<PastAppliedDTO> pastApplyList=recruitService.selectPastApply(authentication);
        model.addAttribute("pastApplyDTO", pastApplyList);

        return "./user/accom_list";
    }

    @GetMapping("/createReview")
    public String createReview(Long rid, Model model){

        return null;
    }
}
