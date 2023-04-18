package com.together.togetherpj.controller;

import com.together.togetherpj.dao.SampleDAO;
import com.together.togetherpj.dto.SampleDTO;
import com.together.togetherpj.service.SampleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class SampleController {

    @Autowired
    SampleService sampleService;

    @GetMapping("/")
    public String registerget(){
        return "hello";
    }

    @PostMapping("/register")
    public void registerPost(SampleDTO dto){
        sampleService.register(dto);
    }
}
