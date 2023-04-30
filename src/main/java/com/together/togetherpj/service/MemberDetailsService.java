package com.together.togetherpj.service;

import com.together.togetherpj.domain.Member;
import com.together.togetherpj.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class MemberDetailsService implements UserDetailsService {

  private final MemberRepository memberRepository;

  @Override
  public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

    Optional<Member> findMember = memberRepository.findByEmail(email);
//        .orElseThrow(EntityNotFoundException::new);
//            new EntityNotFoundException(email));

    if(findMember.isEmpty()){
      throw new UsernameNotFoundException("해당 사용자가 없습니다." + email);
    }

//    log.info("service loadUserByUserName member={}", member);

    return User.builder()
        .username(findMember.get().getEmail())
        .password(findMember.get().getPassword())
        .roles(String.valueOf(findMember.get().getRole()))
        .build();
  }
}
