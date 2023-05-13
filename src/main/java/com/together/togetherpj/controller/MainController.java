package com.together.togetherpj.controller;

import com.together.togetherpj.domain.Recruit;
import com.together.togetherpj.repository.RecruitRepository;
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

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@Slf4j
@RequiredArgsConstructor
public class MainController {

  private final RecruitService recruitService;

/*  @GetMapping("/")
  public String mainPage(Model model){
      model.addAttribute("recruitList",recruitService.findAll());

    return "mainpage";
  }*/

/*  @GetMapping("/")
  public String mainPage(Model model){
    List<Recruit> recruitList = recruitService.findAll();
    recruitList.sort(Comparator.comparing(Recruit::getModDate).reversed());
    List<Recruit> latestRecruitList = recruitList.stream().limit(12).collect(Collectors.toList());

    model.addAttribute("recruitList", latestRecruitList);

    return "mainpage";
  }*/

/*  @GetMapping("/")
  public String mainPage(Model model) {
    List<Recruit> recruitList = recruitService.findAll();

    if(recruitList != null) { // recruitList가 null인 경우 처리
      recruitList.sort(Comparator.comparing(Recruit::getModDate, Comparator.nullsLast(Comparator.reverseOrder())));
      List<Recruit> latestRecruitList = recruitList.stream().limit(12).collect(Collectors.toList());

      model.addAttribute("recruitList", latestRecruitList);
    }

    return "mainpage";
  }*/

/*  @GetMapping("/")
  public String mainPage(Model model) {
    List<Recruit> recruitList = recruitService.getRecruitList();
    model.addAttribute("recruitList", recruitList);
    return "mainpage";
  }*/

/*  @GetMapping("/")
  public String mainPage(Model model) {
    List<Recruit> recruits = RecruitRepository.findTop12ByOrderByModDateDesc();
    model.addAttribute("recruits", recruits);
    return "mainpage";
  }*/

  @GetMapping("/")
  public String mainPage(Model model) {
    List<Recruit> recruitList = recruitService.getLatestRecruits();
    model.addAttribute("recruitList", recruitList);
    return "mainpage";
  }

  @GetMapping("/recruit")
  public String recruit(){
    log.info("MemberController - recruit()");

    return "recruit";
  }

  @GetMapping("/developer")
  public String developer(){
    return "development/development";
  }
}
