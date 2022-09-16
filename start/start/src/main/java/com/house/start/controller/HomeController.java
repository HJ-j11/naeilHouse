package com.house.start.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@Controller
@RequiredArgsConstructor
public class HomeController {

    /**
     * 홈 페이지
     */
    @GetMapping("/")
    public String home(HttpServletRequest request, Model model) {

        log.info("--- home controller - / -----------------------------------------");

        return "home";
    }
}
