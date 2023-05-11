package com.together.togetherpj.service;


import com.together.togetherpj.domain.Recruit;
import com.together.togetherpj.dto.RecruitBoardDto;
import com.together.togetherpj.repository.RecruitBoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RecruitBoardService {
    @Autowired
    private RecruitBoardRepository recruitRepository;

public RecruitBoardService(RecruitBoardRepository recruitBoardRepository){
    this.recruitRepository = recruitBoardRepository;
}

    //조회
  @Transactional
    public List<RecruitBoardDto> getBoardList(){
    List<Recruit> boards = recruitRepository.findAll();
    List<RecruitBoardDto> boardDtoList = new ArrayList<>();

    for(Recruit board : boards){
        RecruitBoardDto boardDto = RecruitBoardDto.builder()
                .id(board.getId())
                .title(board.getTitle())
                .startdate(board.getStartdate())
                .enddate(board.getEnddate())
                .viewcount(board.getViewcount())
                .curNum(board.getCurNum())
                .perNum(board.getPerNum())
                .build();
        boardDtoList.add(boardDto);
    }
    return boardDtoList;
  }

  @Transactional
  public List<RecruitBoardDto> searchPosts(String keyword){
    List<Recruit> boards = recruitRepository.findByTitleContaining(keyword);
    List<RecruitBoardDto> boardDtoList = new ArrayList<>();

    if(boards.isEmpty()) return boardDtoList;

    for(Recruit board : boards){
        boardDtoList.add(this.convertEntityToDto(board));
    }

    return boardDtoList;
  }

  private RecruitBoardDto convertEntityToDto(Recruit board){
    return RecruitBoardDto.builder()
            .id(board.getId())
            .title(board.getTitle())
            .startdate(board.getStartdate())
            .enddate(board.getEnddate())
            .viewcount(board.getViewcount())
            .curNum(board.getCurNum())
            .perNum(board.getPerNum())
            .build();
  }
}
