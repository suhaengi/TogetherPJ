package com.together.togetherpj.service;

import com.together.togetherpj.domain.Member;
import com.together.togetherpj.dto.EditForm;
import com.together.togetherpj.dto.ProfileDto;
import com.together.togetherpj.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Transactional
@Service
@RequiredArgsConstructor
public class ProfileService {
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    public ProfileDto readOne(String email){
        //board_image까지 조인 처리되는 findByWithImages()를 이용
        //Optional<Member> result = memberRepository.findByIdWithImages(email);
        //Member member = result.orElseThrow();
        Member member = memberRepository.findByEmail(email).orElseThrow(() ->{
            throw new UsernameNotFoundException("아이디 혹은 비밀번호가 잘못됐습니다.");
        });        //ProfileDto profileDto =entityToDTO(member);
        ProfileDto profileDto= ProfileDto.builder()
                .nickname(member.getNickname())
                .intro(member.getIntro())
                .gender(member.getGender())
                .regDate(member.getJoinDate())
                .like(member.getLike())
                .build();

        return profileDto;
    }

    public EditForm readForEdit(String email){
        Member member = memberRepository.findByEmail(email).orElseThrow(() ->{
            throw new UsernameNotFoundException("아이디 혹은 비밀번호가 잘못됐습니다.");
        });        EditForm editForm = EditForm.builder()
                .name(member.getName())
                .gender(member.getGender())
                //.birth(member.getBirth())
                .email(member.getEmail())
                .nickname(member.getNickname())
                .intro(member.getIntro())
                .phone(member.getPhone())
                .build();
        return editForm;
    }

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
