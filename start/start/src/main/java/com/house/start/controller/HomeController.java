package com.house.start.controller;

import com.house.start.controller.session.SessionConstants;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

@Slf4j
@Controller
@RequiredArgsConstructor
public class HomeController {

    @GetMapping("/")
    public String home(@SessionAttribute(name = SessionConstants.LOGIN_MEMBER, required = false) Object loginMember, Model model) {
        // 세션에 회원 데이터가 없으면 홈으로 이동
        if (loginMember == null) {
            return "home";
        }

        // 세션이 유지되면 로그인 홈으로 이동
        model.addAttribute("member", loginMember);
        return "loginHome";
    }
}
