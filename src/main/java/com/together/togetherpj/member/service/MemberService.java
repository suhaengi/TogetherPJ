package com.together.togetherpj.member.service;

import com.together.togetherpj.member.entity.Member;
import com.together.togetherpj.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class MemberService implements UserDetailsService {

  private final MemberRepository memberRepository;

  public List<Member> findAll(){
    return memberRepository.findAll();
  }
  public Member saveMember(Member member){
    validateDuplicate(member);
    return memberRepository.save(member);
  }

  private void validateDuplicate(Member member) throws IllegalStateException{
    Member findMember = memberRepository.findByEmail(member.getEmail());
    if(findMember != null){
      throw new IllegalStateException("이미 등록된 사용자 입니다.");
    }
  }

  @Override
  public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

    Member member = memberRepository.findByEmail(email);
//        .orElseThrow(EntityNotFoundException::new);
//            new EntityNotFoundException(email));

    if(member == null){
      throw new UsernameNotFoundException("해당 사용자가 없습니다." + email);
    }

    log.info("service loadUserByUserName member={}", member);

    return User.builder()
        .username(member.getEmail())
        .password(member.getPassword())
        .roles(String.valueOf(member.getRole()))
        .build();
  }
}
