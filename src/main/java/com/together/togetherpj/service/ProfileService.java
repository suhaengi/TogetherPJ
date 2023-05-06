package com.together.togetherpj.service;

import com.together.togetherpj.domain.Member;
import com.together.togetherpj.dto.EditForm;
import com.together.togetherpj.dto.ProfileDto;
import com.together.togetherpj.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

@Transactional
@Service
@RequiredArgsConstructor
public class ProfileService {
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    //mypage불러올때
    public ProfileDto readOne(String email) throws IOException{

        Member member = memberRepository.findByEmail(email).orElseThrow(() ->{
            throw new UsernameNotFoundException("아이디 혹은 비밀번호가 잘못됐습니다.");
        });

        ProfileDto profileDto= ProfileDto.builder()
                .nickname(member.getNickname())
                .intro(member.getIntro())
                .gender(member.getGender())
                .birth(member.getBirth())
                .regDate(member.getJoinDate())
                .like(member.getLike())
                .profileImgPath(member.getProfileImgPath())
                .profileImgName(member.getProfileImgName())
                .build();

        return profileDto;
    }

    //프로필편집 페이지 불러올 때
    public EditForm readForEdit(String email){
        Member member = memberRepository.findByEmail(email).orElseThrow(() ->{
            throw new UsernameNotFoundException("아이디 혹은 비밀번호가 잘못됐습니다.");
        });        EditForm editForm = EditForm.builder()
                .name(member.getName())
                .gender(member.getGender())
                .birth(member.getBirth())
                .email(member.getEmail())
                .nickname(member.getNickname())
                .intro(member.getIntro())
                .phone(member.getPhone())
                .build();
        return editForm;
    }

    //프로필 편집할때
   public void change(Authentication authentication, EditForm editForm){
        String email = authentication.getName();
       Member member = memberRepository.findByEmail(email).orElseThrow(() ->{
           throw new UsernameNotFoundException("아이디 혹은 비밀번호가 잘못됐습니다.");
       });
        member.setNickname(editForm.getNickname());
        member.setIntro(editForm.getIntro());
        member.setPhone(editForm.getPhone());
        member.setPassword(passwordEncoder.encode(editForm.getPassword()));
    }

    //이미지 업로드할 때
    @Value("c://upload")
    private String uploadPath;
    public void saveImg(Authentication authentication, MultipartFile imgFile) throws IOException {

        String email = authentication.getName();
        Member member = memberRepository.findByEmail(email).orElseThrow(() ->{
            throw new UsernameNotFoundException("아이디 혹은 비밀번호가 잘못됐습니다.");
        });        UUID uuid = UUID.randomUUID();
        String fileName = uuid.toString() + "_" + imgFile.getOriginalFilename();
        File profileImg=  new File(uploadPath,fileName);
        imgFile.transferTo(profileImg);
        member.setProfileImgName(fileName);
        member.setProfileImgPath(uploadPath+"/"+fileName);
    }


}
