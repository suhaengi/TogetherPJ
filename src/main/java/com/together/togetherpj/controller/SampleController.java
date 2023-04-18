package com.together.togetherpj.controller;

import com.together.togetherpj.dao.SampleDAO;
import com.together.togetherpj.dto.SampleDTO;
import com.together.togetherpj.service.SampleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class SampleController {

    @Autowired
    SampleService sampleService;

    @PostMapping("/register")
    public String registerPost(HttpServletRequest httpServletRequest){

        SampleDTO sampleDTO1 = SampleDTO.builder()
                .name(httpServletRequest.getParameter("name"))
                .id(httpServletRequest.getParameter("id"))
                .build();

        System.out.println(httpServletRequest.getParameter("name"));
        System.out.println(httpServletRequest.getParameter("id"));
        sampleService.register(sampleDTO1);
        return "";
    }
}
