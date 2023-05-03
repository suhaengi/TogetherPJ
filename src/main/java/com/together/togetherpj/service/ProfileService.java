package com.together.togetherpj.service;

import com.together.togetherpj.domain.Member;
import com.together.togetherpj.dto.EditForm;
import com.together.togetherpj.dto.ProfileDto;
import com.together.togetherpj.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProfileService {
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    public ProfileDto readOne(String email){
        //board_image까지 조인 처리되는 findByWithImages()를 이용
        //Optional<Member> result = memberRepository.findByIdWithImages(email);
        //Member member = result.orElseThrow();
        Member member = memberRepository.findByEmail(email);
        //ProfileDto profileDto =entityToDTO(member);
        ProfileDto profileDto= ProfileDto.builder()
                .nickname(member.getNickname())
                .intro(member.getIntro())
                .gender(member.getGender())
                .regDate(member.getJoinDate())
                .like(member.getLike())
                .build();

        return profileDto;
    }

/*    private ProfileDto entityToDTO(Member member) {
        ProfileDto profileDto= ProfileDto.builder()
                .nickname(member.getNickname())
                .intro(member.getIntro())
                .gender(member.getGender())
                .regDate(member.getJoinDate())
                .like(member.getLike())
                .build();
        String fileName = member.getProfileImg().getUuid()+"_"
                            + member.getProfileImg().getFileName();

        profileDto.setFileName(fileName);

        return profileDto;
    };*/

    public EditForm readForEdit(String email){
        Member member = memberRepository.findByEmail(email);
        EditForm editForm = EditForm.builder()
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

    @Transactional
   public void change(Authentication authentication, EditForm editForm){
        Member member = memberRepository.findByEmail(authentication.getName());
        member.setNickname(editForm.getNickname());
        member.setIntro(editForm.getIntro());
        member.setPhone(editForm.getPhone());
        member.setPassword(passwordEncoder.encode(editForm.getPassword()));
    }
/*
   public void modify(EditForm editForm, Authentication authentication){
        Member member=memberRepository.findByEmail(authentication.getName());
        member.change(editForm.getNickname(),editForm.getIntro(),editForm.getPhone(),
                editForm.getPassword(),passwordEncoder);
        memberRepository.save(member);
    }*/

}
