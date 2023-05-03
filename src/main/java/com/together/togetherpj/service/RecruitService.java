package com.together.togetherpj.service;

import com.together.togetherpj.repository.RecruitRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;

@Service
@Slf4j
@RequiredArgsConstructor
public class RecruitService {
  private final RecruitRepository recruitRepository;

  @Transactional
  @GetMapping("/recruit/register-form")
  public void getRegisterForm(){

  }

}
