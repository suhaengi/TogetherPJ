package com.together.togetherpj.img;

import com.amazonaws.util.IOUtils;
import com.together.togetherpj.dto.ProfileDto;
import com.together.togetherpj.service.ProfileService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

@Controller
@Slf4j
@RequiredArgsConstructor
public class ImgController {
    private final ImgService imgService;

    //프로필 이미지 등록..
    @PostMapping("/member/image")
    public String uploadImg(@RequestPart(value = "imgFile",required = false) MultipartFile img, Authentication authentication) throws IOException {
        imgService.upload(img, "/profile",authentication);
        return "redirect:/member/mypage";
    }

    //게시글 이미지 등록..
    @PostMapping("/recruit/image")
    public void recruitImg(@RequestPart(value = "imgFile",required = false) MultipartFile img, Authentication authentication) throws IOException {
        imgService.upload(img, "/profile",authentication);
    }

    /*
    @GetMapping("/member/image")
    public ResponseEntity<?> getProfileImg (String email) throws IOException {
        ProfileDto profileDto = profileService.readOne(email);

        InputStream inputStream = new FileInputStream(profileDto.getProfileImgPath());
        System.out.println("=========================================================");

        System.out.println(profileDto.getProfileImgPath());
        System.out.println("=========================================================");

        byte[] imageByteArray = IOUtils.toByteArray(inputStream);
        inputStream.close();
        System.out.println("=========================================================");
        System.out.println(imageByteArray);
        System.out.println("=========================================================");
        return new ResponseEntity<>(imageByteArray, HttpStatus.OK);
    }
*/
    

    
    
    
}
