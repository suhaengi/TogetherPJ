package com.together.togetherpj.controller;

import com.together.togetherpj.dto.EditForm;
import com.together.togetherpj.dto.ProfileDto;
import com.together.togetherpj.service.ProfileService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/member")
@Slf4j
@RequiredArgsConstructor
public class ProfileController {
    private final ProfileService profileService;

    @PreAuthorize("isAuthenticated()")  //로그인한 사용자만 조회할 수 있도록
    @GetMapping("/mypage")
    public String myPage(Model model, Authentication authentication){
        String email = authentication.getName();
        ProfileDto profileDto = profileService.readOne(email);

        model.addAttribute("profileDTO",profileDto);
        log.info("PROFILE CONTROLLER - myPage()");
        return "member/mypage_test";
    }

    @PreAuthorize("isAuthenticated()")  //로그인한 사용자만 조회할 수 있도록
    @GetMapping("/editProfile")
    public String EditGet(Model model,Authentication authentication){
        String email = authentication.getName();
        EditForm editForm = profileService.readForEdit(email);
        model.addAttribute("dto",editForm);
        log.info("PROFILE CONTROLLER - GET EDITPROFILE");
        return "member/editProfile_test";
    }

    @PostMapping("/editProfile")
    public String modify(Authentication authentication,
                         EditForm editForm, RedirectAttributes redirectAttributes){
        log.info("PROFILE CONTROLLER - POST EDITPROFILE");

        String email = authentication.getName();
        profileService.change(authentication,editForm);
        //profileService.modify(editForm,authentication);
        EditForm dto = profileService.readForEdit(email);
        //redirectAttributes.addAttribute("dto",dto);
        redirectAttributes.addFlashAttribute("dto","dto");
        return "redirect:/member/editProfile";
    }

}
