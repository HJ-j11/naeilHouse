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

    /**
     * 홈 페이지
     */
    // SessionConstants.ROLE에 따라서 다른 페이지로 라우팅
    @GetMapping("/")
    public String home(HttpServletRequest request, Model model) {
        HttpSession session = request.getSession();
        log.info("home controller - check session: " + session.getAttribute(SessionConstants.LOGIN_MEMBER));
        // 세션이 없으면 홈으로 이동
        if (session.getAttribute(SessionConstants.LOGIN_MEMBER) == null) {
            return "home";
        }

        // ROLE에 따라 다르게 routing
        String role = (String) session.getAttribute(SessionConstants.ROLE);
        model.addAttribute(role, session.getAttribute(SessionConstants.LOGIN_MEMBER));
        return role+"/home";
    }
}
