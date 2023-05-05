package com.together.togetherpj.service;

import com.together.togetherpj.domain.Applying;
import com.together.togetherpj.domain.Member;
import com.together.togetherpj.dto.ApplyingResponseDTO;
import com.together.togetherpj.dto.MyApplyResponseDTO;
import com.together.togetherpj.dto.PastAppliedDTO;
import com.together.togetherpj.repository.ApplyingRepository;
import com.together.togetherpj.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;
import java.util.stream.Collectors;

@Service
@Log4j2
@RequiredArgsConstructor
@Transactional
public class ManageRecruitService {
    private final ModelMapper modelMapper;
    private final ApplyingRepository  repository;
    private final MemberRepository memberRepository;

    //내가 참여하는 동행게시글 내역
    public List<ApplyingResponseDTO> selectApplying(Authentication authentication){
        String email = authentication.getName();
        Member member = memberRepository.findByEmail(email).orElseThrow(() ->{
            throw new UsernameNotFoundException("아이디 혹은 비밀번호가 잘못됐습니다.");
        });

        List<ApplyingResponseDTO> list
                =repository.selectApplyingMember(member.getId());
        /*Member member = memberRepository.findByEmail(authentication.getName());
        List<Applying> list=member.getApplyingList();


        List<ExDTO> dtoList=list.stream().map(applying -> modelMapper
                .map(applying, ExDTO.class)).collect(Collectors.toList());*/

        return list;
    }
    //내가 모집장인 현재 동행게시글 내역
    public List<MyApplyResponseDTO> selectMyApply(Authentication authentication){
        String email = authentication.getName();
        Member member = memberRepository.findByEmail(email).orElseThrow(() ->{
            throw new UsernameNotFoundException("아이디 혹은 비밀번호가 잘못됐습니다.");
        });

        List<Applying> list=repository.myApplyingMember(member.getId());
        List<MyApplyResponseDTO> dtoList=list.stream().map(applying-> modelMapper
                .map(applying, MyApplyResponseDTO.class)).collect(Collectors.toList());

        return dtoList;
    }

    //모집 완료된 동행게시글 내역
    public List<PastAppliedDTO> selectPastApply(Authentication authentication){
        String email = authentication.getName();
        Member member = memberRepository.findByEmail(email).orElseThrow(() ->{
            throw new UsernameNotFoundException("아이디 혹은 비밀번호가 잘못됐습니다.");
        });
        List<PastAppliedDTO> list=repository.pastApply(member.getId());

        return list;
    }


}
