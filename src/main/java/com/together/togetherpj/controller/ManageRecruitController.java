package com.together.togetherpj.controller;

import com.together.togetherpj.dto.ApplyingResponseDTO;
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
    public String plist(Model model,  Authentication authentication){
        List<ApplyingResponseDTO> applyingResponseDTOList=recruitService.selectApplying(authentication);
        model.addAttribute("applyingDTO", applyingResponseDTOList);

        return "myRecruit";
    }
}
