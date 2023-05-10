package com.together.togetherpj.service;


import com.together.togetherpj.domain.Recruit;
import com.together.togetherpj.dto.RecruitDto;
import com.together.togetherpj.repository.RecruitRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.List;

import static com.together.togetherpj.domain.QRecruit.recruit;

@Service
@RequiredArgsConstructor
public class RecruitService {
    @Autowired
    private RecruitRepository recruitRepository;

public RecruitService(RecruitRepository recruitRepository){
    this.recruitRepository = recruitRepository;
}

    //조회
  @Transactional
    public List<RecruitDto> getBoardList(){
    List<Recruit> boards = recruitRepository.findAll();
    List<RecruitDto> boardDtoList = new ArrayList<>();

    for(Recruit board : boards){
        RecruitDto boardDto = RecruitDto.builder()
                .id(board.getId())
                .title(board.getTitle())
                .startdate(board.getStartdate())
                .enddate(board.getEnddate())
                .viewcount(board.getViewcount())
                .build();
        boardDtoList.add(boardDto);
    }
    return boardDtoList;
  }

  public List<RecruitDto> searchPosts(String keyword){
    List<Recruit> boards = recruitRepository.findByTitleContaining(keyword);
    List<RecruitDto> boardDtoList = new ArrayList<>();

    if(boards.isEmpty()) return boardDtoList;

    for(Recruit board: boards){
        boardDtoList.add(this.convertEntityToDto(board));
    }

    return boardDtoList;
  }

  private RecruitDto convertEntityToDto(Recruit board){
    return RecruitDto.builder()
            .id(board.getId())
            .title(board.getTitle())
            .startdate(board.getStartdate())
            .enddate(board.getEnddate())
            .viewcount(board.getViewcount())
            .build();
  }
}
