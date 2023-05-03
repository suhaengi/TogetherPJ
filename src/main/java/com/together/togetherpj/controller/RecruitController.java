package com.together.togetherpj.controller;

import com.together.togetherpj.service.RecruitService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Slf4j
@Controller
@RequiredArgsConstructor
public class RecruitController {

  private final RecruitService recruitService;
  @GetMapping("/recruit/register-form")
  public String getRegisterForm(){
    return "/recruit/write-form";
  }
}
