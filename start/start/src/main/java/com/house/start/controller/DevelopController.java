package com.house.start.controller;

import com.house.start.controller.session.SessionConstants;
import com.house.start.domain.Consumer;
import com.house.start.domain.Item;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@Transactional
@RequiredArgsConstructor
public class DevelopController {

    @GetMapping("/sample")
    public String sample(HttpServletRequest request, Model model) {
        HttpSession session = request.getSession();
        if (session.getAttribute(SessionConstants.LOGIN_MEMBER) == null) {
            session.setAttribute("login_user", false);
            session.setAttribute("role", false);
        } else {
            session.setAttribute("login_user", session.getAttribute(SessionConstants.LOGIN_MEMBER));
            session.setAttribute("role", session.getAttribute(SessionConstants.ROLE));
        }
        return "main_content";
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

    @GetMapping("/consumer/top/bar")
    public String consumerTopBar() { return "consumer_top_bar"; }





}
