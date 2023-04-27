package com.together.togetherpj.controller;

import com.together.togetherpj.dao.SampleDAO;
import com.together.togetherpj.dto.SampleDTO;
import com.together.togetherpj.service.SampleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@Slf4j
public class SampleController {

    @Autowired
    SampleService sampleService;

//    @GetMapping({"/", "/main"})
//    public String registerget(){
//        return "mainpage";
//    }

//    @GetMapping("/login")
//    public String login() {
//        return "login";
//    }

//    @PostMapping("/register")
//    public void registerPost(SampleDTO dto){
//        sampleService.register(dto);
//    }

    @GetMapping("/sample")
    public String sample(Model model){
        List<SampleDTO> list = sampleService.findAll();
        log.info("list={}", list.toString());
        for (SampleDTO dto :
                list) {
            log.info("dto={}", dto);
            log.info("dto.id={}", dto.getId());
            log.info("dto.name={}", dto.getName());
        }
        model.addAttribute("list", list);
        return "sample";
    }
}
