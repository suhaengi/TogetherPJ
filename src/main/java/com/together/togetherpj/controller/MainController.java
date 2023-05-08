package com.together.togetherpj.controller;

import com.together.togetherpj.service.RecruitService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@Slf4j
@RequiredArgsConstructor
public class MainController {

  private final RecruitService recruitService;

  @GetMapping("/")
  public String mainPage(
      Model model){
//    model.addAttribute("recruitList",recruitService.findAll());

    return "mainpage";
  }

  @GetMapping("/recruit")
  public String recruit(){
    log.info("MemberController - recruit()");

    return "recruit";
  }
}
