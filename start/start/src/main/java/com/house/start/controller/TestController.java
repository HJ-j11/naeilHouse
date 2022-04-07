package com.house.start.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.house.start.domain.Item;
import com.house.start.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class TestController {
    @Autowired
    TestService testService;

    @GetMapping("/")
    public String sample() {
        return "sample";
    }

    @GetMapping("/list")
    public String showList(Model model) {
        List<Item> items = testService.findAll();
        model.addAttribute("itemList", items);
        return "itemList";
    }

    @GetMapping("/list/{id}")
    public String showDetail(String id, Model model) {
        Item oneItem = testService.findById(id);
        model.addAttribute("oneItem", oneItem);
        return "itemDetail";
    }

}
