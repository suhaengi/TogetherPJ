package com.together.togetherpj.controller;

import com.together.togetherpj.dto.RecruitDto;
import com.together.togetherpj.service.RecruitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller

public class RecruitController {


    private RecruitService recruitService;

    public RecruitController(RecruitService recruitService){
        this.recruitService = recruitService;
    }

    @GetMapping("/board/recruitBoard")
    public String list(Model model){
        List<RecruitDto> boardDtoList = recruitService.getBoardList();
        model.addAttribute("boardList", boardDtoList);
        return "recruit_board.html";
    }

    @GetMapping("/board/search")
    public String search(@RequestParam(value="keyword")String keyword, Model model){
        List<RecruitDto> boardDtoList = recruitService.searchPosts(keyword);
        model.addAttribute("boardList", boardDtoList);

        return "recruit_board.html";
    }
}