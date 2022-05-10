package com.house.start.controller.Consumer;

import com.house.start.controller.session.SessionConstants;
import com.house.start.domain.Consumer;
import com.house.start.service.ConsumerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * consumer의 mypage에 해당하는 controller 부분
 */
@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("user")
public class ConsumerMypageController {
    private final ConsumerService consumerService;

    /**
     * 마이페이지 처음 페이지
     */
    @GetMapping("")
    public String getUserInfo(HttpServletRequest request, Model model) {
        log.info("--- consumer mypage controller - show user info -----------------------------------------");
        HttpSession session = request.getSession();
        if (session.getAttribute(SessionConstants.LOGIN_MEMBER) == null) {
            return "err/notLogin";
        } else if (session.getAttribute(SessionConstants.ROLE) == "consumer") {
            // 소비자인 경우
            session.setAttribute("login_state", session.getAttribute(SessionConstants.LOGIN_MEMBER));
            session.setAttribute("role", session.getAttribute(SessionConstants.ROLE));
            Consumer consumer = (Consumer) session.getAttribute(SessionConstants.LOGIN_MEMBER);
            consumer = consumerService.findConsumerBycId(consumer.getCId());
            log.info("--- consumer mypage controller - show user info -----------------------------------------" + consumer);
            log.info("--- consumer mypage controller - show user info -----------------------------------------"+consumer.getLikes());
            model.addAttribute("consumer", consumer);
            return "consumer/mypage/user";
        } else {
            // 판매자나 관리자인 경우
            return "err/denyPage";
        }
    }

    // 주문 보기
    @GetMapping("/orders")
    public String getAllOrders(Model model) {
        // 자신이 주문한 걸 가져와야할텐데..?

        return "info/orders";
    }

    // 리뷰 보기
    @GetMapping("/review")
    public String getAllReviews() {
        return "info/reviews";
    }

    // 좋아요 보기
    @GetMapping("/likes")
    public String getAllLikes() {
        return "info/likes";
    }

    @GetMapping("/deliveries")
    public String getAllDeliveries() {
        return "info/deliveries";
    }

    public HttpSession setSession(HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.setAttribute("login_state", session.getAttribute(SessionConstants.LOGIN_MEMBER));
        session.setAttribute("role", session.getAttribute(SessionConstants.ROLE));
        return session;
    }
}
