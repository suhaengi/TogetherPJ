package com.together.togetherpj.controller;

import com.together.togetherpj.constant.State;
import com.together.togetherpj.domain.Member;
import com.together.togetherpj.domain.Recruit;
import com.together.togetherpj.dto.RecruitWriteFormDto;
import com.together.togetherpj.dto.ViewForm;
import com.together.togetherpj.service.MemberService;
import com.together.togetherpj.service.RecruitService;
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
import java.io.IOException;
import java.time.LocalDate;

@Controller
@RequestMapping("/recruit")
@Slf4j
@RequiredArgsConstructor
public class RecruitController {
  private final RecruitService recruitService;
  private final MemberService memberService;
  private final PasswordEncoder passwordEncoder;

  @GetMapping("/write-form")
  public String register(Model model) {
    model.addAttribute("writeFormDto", new RecruitWriteFormDto());
    return "recruit/write-form";
  }

  @PostMapping("/save")
  public String recruitSave(@Valid RecruitWriteFormDto writeFormDto,
      BindingResult bindingResult, Authentication authentication, Model model) {

    if (bindingResult.hasErrors()) {
      return "recruit/write-form";
    }

    try {
      recruitService.save(writeFormDto, authentication.getName());
    } catch (IllegalStateException e) {
      model.addAttribute("errorMessage", e.getMessage());
      return "recruit/write-form";
    }

    return "redirect:/";
  }

  @GetMapping({"/view", "/modify"})
  public void read(Long bno, Model model) throws IOException {
    ViewForm dto = recruitService.readOne(bno);
    model.addAttribute("dto", dto);
  }

  @PostMapping("/apply")
  public String applying(Authentication authentication,Long bno){
    String email = authentication.getName();
    recruitService.Applying(email,bno);
    return "redirect:/";
  }
}
