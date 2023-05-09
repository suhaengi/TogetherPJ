package com.together.togetherpj.controller;

import com.together.togetherpj.service.ApplyingService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@Slf4j
@RequiredArgsConstructor
public class ApplyingController {
    private final ApplyingService applyingService;

    @GetMapping("find-applying")
    public @ResponseBody String findApplying(){
        applyingService.findById();
        return "findApplying";
    }
}
