package com.house.start.controller;

import com.house.start.service.SearchService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class SearchController {
    private final SearchService searchService;
    /**
     * 검색
     * **/
    @GetMapping("/search")
    public String findItemAndPost(@RequestParam(required = false) String word, Model model) {
        String param = word.trim();

        if(param.length()==0) {
            return "errPage";
        }

        model.addAttribute("items", searchService.findItemByName(param));
        model.addAttribute("posts", searchService.findPostByContents(param));
        return "searchResult";
    }
}
