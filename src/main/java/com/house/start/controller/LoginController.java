package com.house.start.controller;

import com.house.start.controller.form.LoginForm;
import com.house.start.controller.session.SessionConstants;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Slf4j
@Controller
@RequiredArgsConstructor
public class LoginController {

    /**
     * 로그인 화면으로 이동
      */
    @GetMapping("/login")
    public String login(@ModelAttribute LoginForm loginForm, Model model) {
//        log.info("login controller - do login");
        return "login/loginForm";
    }

    @GetMapping("/oauth/login")
    public String oAuthLogin() {
        log.info("---- oauth login entered ----");
        return "login/OAUthLoginForm";
    }

    /**
     * 로그아웃
     */
    @GetMapping("/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null) {
            new SecurityContextLogoutHandler().logout(request, response, authentication);
        }

        return "redirect:/";
    }
}
