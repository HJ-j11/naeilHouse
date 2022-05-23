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
    @GetMapping("/")
    public String home(HttpServletRequest request, Model model) {
        HttpSession session = request.getSession();
        log.info("home controller - check session: " + session.getAttribute(SessionConstants.LOGIN_MEMBER));

        if (session.getAttribute(SessionConstants.LOGIN_MEMBER) == null) {
            session.setAttribute("login_user", false);
            session.setAttribute("role", false);
        } else {
            session.setAttribute("login_user", session.getAttribute(SessionConstants.LOGIN_MEMBER));
            session.setAttribute("role", session.getAttribute(SessionConstants.ROLE));
        }

        return "home";
    }
}
