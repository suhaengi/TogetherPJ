package com.together.togetherpj.service;

import com.together.togetherpj.domain.Member;
import com.together.togetherpj.dto.MemberFormDto;
import com.together.togetherpj.dto.MemberProfileDto;
import com.together.togetherpj.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class MemberService{

  private final MemberRepository memberRepository;
  private final PasswordEncoder passwordEncoder;

  public List<Member> findAll(){
    return memberRepository.findAll();
  }

  public Member findById(Long id){
    return memberRepository.findById(id).orElseThrow(IllegalStateException::new);
  }

  public Member findByEmail(String email) {
    return memberRepository.findByEmail(email).orElseThrow(IllegalStateException::new);
  }

  public Member saveMember(MemberFormDto memberFormDto){
    Member member = Member.createMember(memberFormDto, passwordEncoder);
    validateDuplicate(member);
    return memberRepository.save(member);
  }

  public MemberProfileDto getMemberProfile(Long id){
    Member member = memberRepository.findById(id).orElseThrow(IllegalStateException::new);

    return MemberProfileDto.builder()
        .nickname(member.getNickname())
        .ageGroup(getAgeGroup(member))
        .gender(member.getGender())
        .build();
  }

  private void validateDuplicate(Member member) throws IllegalStateException{
    Optional<Member> findMember = memberRepository.findByEmail(member.getEmail());
    if(findMember.isPresent()){
      throw new IllegalStateException("이미 등록된 사용자입니다.");
    }
  }

  private String getAgeGroup(Member member){
    SimpleDateFormat yearFormat = new SimpleDateFormat("yyyy");
    int birthYear = Integer.parseInt(yearFormat.format(member.getBirth()));
    Calendar now = Calendar.getInstance();
    int nowYear = now.get(Calendar.YEAR);
    int ageGroup = ((nowYear - birthYear) + 1 / 10);
    if(ageGroup == 0){
      return "10세 이하";
    }
    return String.valueOf(ageGroup) + "0대";
  }
}
