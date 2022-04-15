package com.house.start.controller;

import com.house.start.controller.form.LoginForm;
import com.house.start.domain.Consumer;
import com.house.start.service.LoginService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Slf4j
@Controller
@RequiredArgsConstructor
public class LoginuserController {
    private final LoginService loginService;

    // 로그인 화면으로 이동
    @GetMapping("/login")
    public String loginForm(@ModelAttribute LoginForm loginForm) {
        return "login/loginForm";
    }

    @PostMapping("/login")
    public String loginUser(@ModelAttribute @Validated LoginForm loginForm,
                        BindingResult bindingResult,
                        @RequestParam(defaultValue = "/") String redirectURL) {
        if (bindingResult.hasErrors()) {
            return "login/loginForm";
        }
        Integer role_num = loginForm.getRole();
        if (role_num == 0) {
            // 소비자 로그인
            Consumer loginConsumer = loginService.loginConsumer(loginForm.getLoginId(), loginForm.getPwd());
            if (loginConsumer == null) {
                bindingResult.reject("loginFall", "아이디 또는 비밀번호가 맞지 않습니다. 회원이 맞는지 확인해보세요.");
            }

        } else if (role_num == 1) {
            // 판매자 로그인

        } else {
            // 관리자 로그인

        }


        return "reditect: " +redirectURL;

    }
}
