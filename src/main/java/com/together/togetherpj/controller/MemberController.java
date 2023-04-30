package com.together.togetherpj.controller;

import com.together.togetherpj.dto.MemberFormDto;
import com.together.togetherpj.domain.Member;
import com.together.togetherpj.dto.MemberProfileDto;
import com.together.togetherpj.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("/member")
@Slf4j
@RequiredArgsConstructor
public class MemberController {
  private final MemberService memberService;
  private final PasswordEncoder passwordEncoder;

  @GetMapping("/register")
  public String register(Model model) {
    model.addAttribute("memberFormDto", new MemberFormDto());
    return "member/demo-register-form";
  }

  @PostMapping("/register")
  public String memberForm(
      @Valid MemberFormDto memberFormDto,
      BindingResult bindingResult,
      Model model) {

    if (bindingResult.hasErrors()) {
      return "member/demo-register-form";
    }

    try {
      memberService.saveMember(memberFormDto);
    } catch (IllegalStateException e) {
      log.error("IllegalStateException={}", e.getMessage());
      model.addAttribute("errorMessage", e.getMessage());
      return "member/demo-register-form";
    }

    return "redirect:/";
  }

  @GetMapping("/login")
  public String login(){
    return "member/demo-login";
  }

  @GetMapping("/login/error")
  public String loginError(Model model){
    model.addAttribute("loginErrorMsg", "아이디 또는 패스워드가 잘못되었습니다.");
    return "member/demo-login";
  }

  @GetMapping("/{id}")
  public String memberProfile(
      @PathVariable Long id,
      Model model){
    log.info("member controller get memberProfile");
    MemberProfileDto memberProfileDto = memberService.getMemberProfile(id);
    log.info("memberProfileDto={}", memberProfileDto);
    model.addAttribute("memberProfileDto", memberProfileDto);
    return "member/demo-profile";
  }

  @GetMapping("/mypage")
  public String myPage(Authentication authentication){
    log.info("MemberController - myPage()");

    Member authMember = memberService.findByEmail(authentication.getName());

    return "member/mypage_test";
  }

}
