package com.together.togetherpj.controller;

import com.together.togetherpj.dto.MemberRegisterFormDto;
import com.together.togetherpj.domain.Member;
import com.together.togetherpj.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
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
    model.addAttribute("memberRegisterFormDto", new MemberRegisterFormDto());
    return "member/register-form";
  }

  @PostMapping("/register")
  public String memberForm(
      @Valid MemberRegisterFormDto memberRegisterFormDto,
//      @RequestParam("birth") @DateTimeFormat (iso = DateTimeFormat.ISO.DATE_TIME) Date birth,
      BindingResult bindingResult,
      Model model) {

    if (bindingResult.hasErrors()) {
      return "member/register-form";
    }

    try {
      Member member = Member.createMember(memberRegisterFormDto, passwordEncoder);
      memberService.saveMember(member);
      log.info("saved member={}", member);
    } catch (IllegalStateException e) {
      log.error("IllegalStateException={}", e.getMessage());
      model.addAttribute("errorMessage", e.getMessage());
      return "member/register-form";
    }

    return "redirect:/";
  }

  @GetMapping("/login")
  public String login(){
    return "member/login";
  }

  @GetMapping("/login/error")
  public String loginError(Model model){
    model.addAttribute("loginErrorMsg", "아이디 또는 패스워드가 잘못되었습니다.");
    return "member/login";
  }



  @GetMapping("/mypage")
  public String myPage(){
    log.info("MemberController - myPage()");
    return "member/mypage_test";
  }

}
