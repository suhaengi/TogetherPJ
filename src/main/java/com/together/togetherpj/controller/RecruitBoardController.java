package com.together.togetherpj.controller;

import com.together.togetherpj.dto.RecruitBoardDto;
import com.together.togetherpj.service.RecruitBoardService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller

public class RecruitBoardController {


    private RecruitBoardService recruitService;

    public RecruitBoardController(RecruitBoardService recruitBoardService){
        this.recruitService = recruitBoardService;
    }

    @GetMapping("/board/recruitBoard")
    public String list(Model model){
        List<RecruitBoardDto> boardDtoList = recruitService.getBoardList();
        model.addAttribute("boardList", boardDtoList);
        return "recruit_board";
    }

    @GetMapping("/board/search")
    public String search(@RequestParam(value="keyword")String keyword, Model model){
        List<RecruitBoardDto> boardDtoList = recruitService.searchPosts(keyword);
        model.addAttribute("boardList", boardDtoList);

        return "recruit_board";
    }
}