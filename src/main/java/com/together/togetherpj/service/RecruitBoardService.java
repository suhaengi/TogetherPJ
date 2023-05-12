package com.together.togetherpj.service;


import com.together.togetherpj.domain.Recruit;
import com.together.togetherpj.dto.RecruitBoardDto;
import com.together.togetherpj.repository.RecruitBoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RecruitBoardService {
    @Autowired
    private RecruitBoardRepository recruitRepository;

    private static final int BLOCK_PAGE_NUM_COUNT = 5;
    private static final int PAGE_POST_COUNT = 10;

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

  //paging
    @Transactional
    public List<RecruitBoardDto> getBoardList(Integer pageNum){
    Page<Recruit> page = recruitRepository
            .findAll(PageRequest.of(pageNum-1,PAGE_POST_COUNT, Sort.by(Sort.Direction.ASC, "id")));

    List<Recruit> boards = page.getContent();
    List<RecruitBoardDto> boardDtoList = new ArrayList<>();

    for(Recruit board : boards){
        boardDtoList.add(this.convertEntityToDto(board));
    }
    return boardDtoList;
    }

    public Integer[] getPageList(Integer curPageNum){
        Integer[] pageList = new Integer[BLOCK_PAGE_NUM_COUNT];

        //총게시글 수
        Double postsTotalCount = Double.valueOf(this.getBoardCount());

        Integer totalLastPageNum = (int)(Math.ceil((postsTotalCount/PAGE_POST_COUNT)));

        Integer blockLastPageNum = (totalLastPageNum > curPageNum + BLOCK_PAGE_NUM_COUNT)
                ? curPageNum + BLOCK_PAGE_NUM_COUNT
                : totalLastPageNum;

        curPageNum = (curPageNum<=3) ? 1 : curPageNum -2;

        for(int val = curPageNum, i = 0; val <= blockLastPageNum; val ++, i++){
            pageList[i] = val;
        }

        return pageList;

    }

    @Transactional
    public Long getBoardCount(){
        return recruitRepository.count();
    }




  //viewcount

}
