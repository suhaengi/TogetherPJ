package com.together.togetherpj.service;

import com.together.togetherpj.constant.State;
import com.together.togetherpj.domain.Member;
import com.together.togetherpj.domain.Recruit;
import com.together.togetherpj.dto.RecruitWriteFormDto;
import com.together.togetherpj.dto.ViewForm;
import com.together.togetherpj.repository.MemberRepository;
import com.together.togetherpj.repository.RecruitRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.swing.text.View;
import java.awt.print.Pageable;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class RecruitService {
  private final RecruitRepository recruitRepository;
  private final MemberRepository memberRepository;

  public ViewForm readOne(Long bno) throws IOException{
    Recruit recruit = recruitRepository.findById(bno).orElseThrow();
    Member member = recruit.getRecruitWriter();
    ViewForm viewForm = ViewForm.builder()
            .bno(bno)
            .regDate(recruit.getRegDate())
            .modDate(recruit.getModDate())
            .writer(member.getNickname())
            .content(recruit.getContent())
            .title(recruit.getTitle())
            .city(recruit.getCity())
            .perNum(recruit.getPerNum())
            .curNum(recruit.getCurNum())
            .startdate(recruit.getStartdate())
            .enddate(recruit.getEnddate())
            .state(recruit.getState())
            .viewcount(recruit.getViewcount())
            .build();
    return viewForm;
  }

  public void save(RecruitWriteFormDto writeFormDto, String userEmail){
    Member writer = memberRepository.findByEmail(userEmail)
        .orElseThrow(IllegalStateException::new);
    Recruit recruit = createRecruit(writeFormDto, writer);

    recruitRepository.save(recruit);
  }

  private Recruit createRecruit(RecruitWriteFormDto writeFormDto, Member writer){
    return Recruit.builder()
        .title(writeFormDto.getTitle())
        .city(writeFormDto.getCity())
        .content(writeFormDto.getContent())
        .perNum(writeFormDto.getPerNum())
        .startdate(LocalDate.parse(writeFormDto.getStartdate()))
        .enddate(LocalDate.parse(writeFormDto.getEnddate()))
        .state(State.RECRUITING)
        .recruitWriter(writer)
        .build();
  }

  public List<Recruit> findAll() {
    return recruitRepository.findAll();
  }

/*
    public List<Post> getRecentPosts() {
      Pageable pageable = PageRequest.of(0, 10);
      return recruitRepository.findAllByOrderByCreatedAtDesc(pageable);
    }
*/



}
