package com.together.togetherpj.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@Slf4j
public class MainController {
  @GetMapping("/")
  public String mainPage(){
    return "demo-main";
  }
}
