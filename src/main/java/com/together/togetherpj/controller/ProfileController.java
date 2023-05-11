package com.together.togetherpj.controller;

import com.together.togetherpj.dto.ProfileDto;
import com.together.togetherpj.dto.PwForm;
import com.together.togetherpj.dto.ReviewResponseDTO;
import com.together.togetherpj.service.ManageRecruitService;
import com.together.togetherpj.service.ProfileService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@Controller
@RequestMapping("/member")
@Slf4j
@RequiredArgsConstructor
public class ProfileController {
    private final ProfileService profileService;
    private final ManageRecruitService recruitService;

    @PreAuthorize("isAuthenticated()")  //로그인한 사용자만 조회할 수 있도록
    @GetMapping("/mypage")
    public String myPage(Model model, Authentication authentication) throws IOException{
        String email = authentication.getName();
        ProfileDto profileDto = profileService.readOne(email);

        List<ReviewResponseDTO> list=recruitService.selectMyReview(authentication);
        model.addAttribute("myReviewList", list);

        model.addAttribute("profileDTO",profileDto);
        log.info("PROFILE CONTROLLER - myPage()");
        return "member/mypage";
    }

    @PreAuthorize("isAuthenticated()")  //로그인한 사용자만 조회할 수 있도록
    @GetMapping("/editProfile")
    public String EditGet(Model model,Authentication authentication){
        String email = authentication.getName();
        ProfileDto dto = profileService.readForEdit(email);
        model.addAttribute("dto",dto);
        log.info("PROFILE CONTROLLER - GET EDITPROFILE");
        return "member/editProfile";
    }

    @PostMapping("/editProfile")
    public String modify(Authentication authentication, ProfileDto profileDto){
        log.info("PROFILE CONTROLLER - POST EDITPROFILE");
        profileService.change(authentication,profileDto);

        return "redirect:/member/mypage";
    }

    @PostMapping("/changePw")
    public String changePw(Authentication authentication, @Valid PwForm pwForm){
        log.info("PROFILE CONTROLLER - POST changePw");
        profileService.PWchange(authentication,pwForm);
        return "redirect:/member/mypage";
    }

    @PostMapping("/image")
    public String imageUpload(@RequestPart(value = "imgFile") MultipartFile imgFile, Authentication authentication)
            throws IOException {
        profileService.saveImg(authentication,imgFile);

        return "redirect:/member/mypage";
    }

    @GetMapping("/image")
    public ResponseEntity<?> getProfileImg (Authentication authentication) throws IOException {
        String email = authentication.getName();
        ProfileDto profileDto = profileService.readOne(email);

        InputStream inputStream = new FileInputStream(profileDto.getProfileImgPath());
        byte[] imageByteArray = IOUtils.toByteArray(inputStream);
        inputStream.close();
        return new ResponseEntity<>(imageByteArray, HttpStatus.OK);
    }

   /* @GetMapping("/mypage")
    public String getMyReview(Authentication authentication, Model model){
        List<ReviewResponseDTO> list=recruitService.selectMyReview(authentication);
        model.addAttribute("myReviewList", list);

        return "member/mypage";
    }*/
}
