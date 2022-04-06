package com.house.start.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DevelopController {

    @GetMapping("/sample")
    public String sample() {
        return "sample";
    }


    @GetMapping("/")
    public String main() {
        return "main";
    }

    @GetMapping("/login")
    public String loginForm() {
        return "login";
    }

    @GetMapping("/admin/consumerSetting")
    public String consumerManagement() {
        return "admin_회원관리";
    }

    @GetMapping("/admin")
    public String adminHome() {
        return "admin_default";
    }

    @GetMapping("/items")
    public String itemList() {
        return "itemList";
    }





}
