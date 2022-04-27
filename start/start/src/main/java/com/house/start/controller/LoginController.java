package com.house.start.controller;

import com.house.start.controller.form.LoginForm;
import com.house.start.controller.session.SessionConstants;
import com.house.start.domain.Admin;
import com.house.start.domain.Consumer;
import com.house.start.domain.Seller;
import com.house.start.domain.UserType;
import com.house.start.service.LoginService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Slf4j
@Controller
@RequiredArgsConstructor
public class LoginController {
    private final LoginService loginService;

    // 로그인 화면으로 이동
    @GetMapping("/login")
    public String login(@ModelAttribute LoginForm loginForm, Model model) {
        log.info("login controller - do login");
        return "login/loginForm";
    }

    @PostMapping("/login")
    public String loginUser(@ModelAttribute @Validated LoginForm loginForm,
                            BindingResult bindingResult,
                            HttpServletRequest request) {
        log.info("login controller - post login data");
        if (bindingResult.hasErrors()) { // 입력한 loginForm 형식이 안맞는 경우
            return "login/loginForm";
        }

        Integer role_num = loginForm.getRole();
        if (role_num == 0) {
            // 소비자 로그인
            Consumer loginConsumer = loginService.loginConsumer(loginForm.getLoginId(), loginForm.getPwd());
            if (loginConsumer == null) { //로그인 실패
                log.info("login controller - fail login" + loginForm.getLoginId());
                bindingResult.reject("loginFall", "아이디 또는 비밀번호가 맞지 않습니다. 회원이 맞는지 확인해보세요.");
                return "login/loginForm";
            }

            //로그인 성공 처리
            HttpSession session = request.getSession();
            session.setAttribute(SessionConstants.LOGIN_MEMBER, loginConsumer);
            session.setAttribute(SessionConstants.ROLE, "consumer");
            log.info("login controller - success login id: " + session.getAttribute(SessionConstants.ROLE));
        } else if (role_num == 1) {
            // 판매자 로그인
            Seller loginSeller = loginService.loginSeller(loginForm.getLoginId(), loginForm.getPwd());
            if (loginSeller == null) { // 로그인 실패
                log.info("login controller - fail login" + loginForm.getLoginId());
                bindingResult.reject("loginFall", "아이디 또는 비밀번호가 맞지 않습니다. 판매자가 맞는지 확인해보세요.");
                return "login/loginForm";
            }

            //로그인 성공 처리
            HttpSession session = request.getSession();
            session.setAttribute(SessionConstants.LOGIN_MEMBER, loginSeller);
            session.setAttribute(SessionConstants.ROLE, "seller");
            log.info("login controller - success login id: " + session.getAttribute(SessionConstants.ROLE));
        } else {
            // 관리자 로그인
            Admin loginAdmin = loginService.loginAdmin(loginForm.getLoginId(), loginForm.getPwd());
            if (loginAdmin == null) { // 로그인 실패
                log.info("login controller - fail login" + loginForm.getLoginId());
                bindingResult.reject("loginFall", "아이디 또는 비밀번호가 맞지 않습니다. 관리자가 맞는지 확인해보세요.");
                return "login/loginForm";
            }

            //로그인 성공 처리
            HttpSession session = request.getSession();
            session.setAttribute(SessionConstants.LOGIN_MEMBER, loginAdmin);
            session.setAttribute(SessionConstants.ROLE, "admin");
            log.info("login controller - success login id: " + session.getAttribute(SessionConstants.ROLE));
        }
        return "redirect:/";
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();   // 세션 날림
        }
        return "redirect:/";
    }
}
