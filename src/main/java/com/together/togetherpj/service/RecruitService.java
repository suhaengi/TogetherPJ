package com.together.togetherpj.service;

import com.together.togetherpj.domain.Recruit;
import com.together.togetherpj.repository.MemberRepository;
import com.together.togetherpj.repository.RecruitRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class RecruitService {
  private final RecruitRepository recruitRepository;
  private final MemberRepository memberRepository;

  public List<Recruit> findAll() {
    return recruitRepository.findAll();
  }
}
