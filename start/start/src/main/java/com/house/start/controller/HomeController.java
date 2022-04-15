package com.house.start.controller;

import com.house.start.controller.session.SessionConstants;
import com.house.start.domain.Consumer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Slf4j
@Controller
@RequiredArgsConstructor
public class HomeController {

    @GetMapping("/")
    public String home(HttpServletRequest request, Model model) {
        HttpSession session = request.getSession();
        log.info("home controller - check session: " + session.getAttribute(SessionConstants.LOGIN_MEMBER));
        // 세션이 없으면 홈으로 이동
        if (session.getAttribute(SessionConstants.LOGIN_MEMBER) == null) {
            return "main";
        }

        // ROLE에 따라 다르게 routing
        String role = (String) session.getAttribute(SessionConstants.ROLE);
        model.addAttribute(role, session.getAttribute(SessionConstants.LOGIN_MEMBER));
        return role+"/home";

        // 24-25 줄 코드와 29-41 줄 코드 동작은 동일합니다!
        /*
        if (role == "consumer") {
            // 로그인 데이터: 소비자
            model.addAttribute("consumer", SessionConstants.LOGIN_MEMBER);
            return "consumer/home";
        } else if (role == "seller") {
            // 로그인 데이터: 판매자
            model.addAttribute("seller", SessionConstants.LOGIN_MEMBER);
            return "seller/home";
        } else {
            // 로그인 데이터: 관리자
            model.addAttribute("admin", SessionConstants.LOGIN_MEMBER);
            return "admin/home";
        }*/
    }
}
