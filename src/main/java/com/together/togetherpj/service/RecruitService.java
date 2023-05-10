package com.together.togetherpj.service;

import com.together.togetherpj.constant.State;
import com.together.togetherpj.domain.Applying;
import com.together.togetherpj.domain.Member;
import com.together.togetherpj.domain.Recruit;
import com.together.togetherpj.domain.id.ApplyingId;
import com.together.togetherpj.domain.id.QApplyingId;
import com.together.togetherpj.dto.RecruitWriteFormDto;
import com.together.togetherpj.repository.ApplyingRepository;
import com.together.togetherpj.repository.MemberRepository;
import com.together.togetherpj.repository.RecruitRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor

public class RecruitService {
  private final RecruitRepository recruitRepository;
  private final MemberRepository memberRepository;
  private final ApplyingRepository applyingRepository;

  public List<Recruit> findAll(){
    return recruitRepository.findAll();
  }

  public void save(RecruitWriteFormDto writeFormDto, String userEmail){
    Member writer = memberRepository.findByEmail(userEmail)
        .orElseThrow(IllegalStateException::new);
    Recruit recruit = createRecruit(writeFormDto, writer);
    Applying applying = createWriterApplying(writer, recruit);
    recruitRepository.save(recruit);
    applyingRepository.save(applying);



  }

  private Recruit createRecruit(RecruitWriteFormDto writeFormDto, Member writer){
    return Recruit.builder()
        .title(writeFormDto.getTitle())
        .city(writeFormDto.getCity())
        .content(writeFormDto.getContent())
        .perNum(Long.parseLong(writeFormDto.getPerNum()))
        .startdate(LocalDate.parse(writeFormDto.getStartdate()))
        .enddate(LocalDate.parse(writeFormDto.getEnddate()))
        .state(State.RECRUITING)
        .recruitWriter(writer)
        .build();
  }


  private Applying createWriterApplying(Member writer, Recruit recruit) {
    Applying applying=Applying.builder()
            .id(new ApplyingId())
            .isOk(true)
            .applier(writer)
            .recruit(recruit)
            .build();


    return applying;
  }



}
