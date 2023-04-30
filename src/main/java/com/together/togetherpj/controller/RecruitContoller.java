package com.together.togetherpj.controller;

import com.together.togetherpj.service.RecruitService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
@Slf4j
public class RecruitContoller {

  private final RecruitService recruitService;

  @GetMapping("/recruit")
  public String recruit(){
    log.info("MemberController - recruit()");
    return "recruit/demo-recruit-main";
  }
}
