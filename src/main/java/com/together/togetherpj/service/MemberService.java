package com.together.togetherpj.service;

import com.together.togetherpj.domain.Member;
import com.together.togetherpj.dto.MemberRegisterFormDto;
import com.together.togetherpj.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class MemberService implements UserDetailsService {

  private final MemberRepository memberRepository;
  private final PasswordEncoder passwordEncoder;

  @Transactional(readOnly = true)
  public List<Member> findAll(){
    return memberRepository.findAll();
  }

  public Member saveMember(MemberRegisterFormDto registerFormDto){
    log.info("member service saveMember");
    Member member = Member.createMember(registerFormDto, passwordEncoder);
    log.info("member service member create");
    validateDuplicate(registerFormDto);
    log.info("member service member duplicate check");
    return memberRepository.save(member);
  }

  private void validateDuplicate(MemberRegisterFormDto registerFormDto) throws IllegalStateException{
    Optional<Member> findmember = memberRepository.findByEmail(registerFormDto.getEmail());
    if(findmember.isPresent()){
      throw new IllegalStateException("이미 등록된 사용자 입니다.");
    }
  }

  @Override
  public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

    Member member = memberRepository.findByEmail(email).orElseThrow(() ->{
          throw new UsernameNotFoundException("아이디 혹은 비밀번호가 잘못됐습니다.");
    });

//        .orElseThrow(EntityNotFoundException::new);
//            new EntityNotFoundException(email));
//    if(member == null){
//      throw new UsernameNotFoundException("해당 사용자가 없습니다." + email);
//    }
//    log.info("service loadUserByUserName member={}", member);

    return User.builder()
        .username(member.getEmail())
        .password(member.getPassword())
        .roles(String.valueOf(member.getRole()))
        .build();
  }
}
