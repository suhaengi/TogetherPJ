package com.together.togetherpj.controller;

import com.together.togetherpj.service.TourService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TourController {
    private final TourService tourService;

    public TourController(TourService tourService) {
        this.tourService = tourService;
    }

    @GetMapping("/tour")
    public String getTourList(Model model) {
        String tourList = tourService.getTourList();
        model.addAttribute("tourList", tourList);
        return "tour";
    }
}
