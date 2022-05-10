package com.house.start.controller.Consumer;

import com.house.start.controller.session.SessionConstants;
import com.house.start.domain.Consumer;
import com.house.start.domain.Order;
import com.house.start.repository.ConsumerRepository;
import com.house.start.service.ConsumerService;
import com.house.start.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * consumer의 mypage에 해당하는 controller 부분
 */
@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("user")
public class ConsumerMypageController {
    private final ConsumerService consumerService;
    private final OrderService orderService;

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
            model.addAttribute("consumer", consumer);
            return "consumer/mypage/user";
        } else {
            // 판매자나 관리자인 경우
            return "err/denyPage";
        }
    }

    /**
     * 마이페이지에서 주문 보기
     */
    @GetMapping("/{consumer_id}/orders")
    public String getAllOrders(@PathVariable Long consumer_id, HttpServletRequest request, Model model) {
        log.info("--- consumer mypage controller - show user info -> order -----------------------------------------");
        HttpSession session = request.getSession();
        if (session.getAttribute(SessionConstants.LOGIN_MEMBER) == null) {
            return "err/notLogin";
        } else if (session.getAttribute(SessionConstants.ROLE) == "consumer") {
            // 소비자인 경우

            // mypage 기본 필수 데이터
            session.setAttribute("login_state", session.getAttribute(SessionConstants.LOGIN_MEMBER));
            session.setAttribute("role", session.getAttribute(SessionConstants.ROLE));
            Consumer consumer = consumerService.findConsumerById(consumer_id);
            model.addAttribute("consumer", consumer);

            // orders 데이터
            List<Order> orderList = orderService.findCartOrder(consumer);
            model.addAttribute("orderList", orderList);
            Long orderStaus = orderService.countOrderStaus();
            Long cStaus = orderService.countCompleteStaus();
            log.info("--- consumer mypage controller - show user info -> order -----------------------------------------" + orderStaus);
            return "consumer/mypage/orders";
        } else {
            // 판매자나 관리자인 경우
            return "err/denyPage";
        }
    }

    // 리뷰 보기
    @GetMapping("/{consumer_id}/review")
    public String getAllReviews(@PathVariable Long consumer_id, HttpServletRequest request, Model model) {
        log.info("--- consumer mypage controller - show user info -> review -----------------------------------------");
        HttpSession session = request.getSession();
        if (session.getAttribute(SessionConstants.LOGIN_MEMBER) == null) {
            return "err/notLogin";
        } else if (session.getAttribute(SessionConstants.ROLE) == "consumer") {
            // 소비자인 경우

            // mypage 기본 필수 데이터
            session.setAttribute("login_state", session.getAttribute(SessionConstants.LOGIN_MEMBER));
            session.setAttribute("role", session.getAttribute(SessionConstants.ROLE));
            Consumer consumer = consumerService.findConsumerById(consumer_id);
            model.addAttribute("consumer", consumer);

            // orders 데이터
            List<Order> orderList = orderService.findCartOrder(consumer);
            model.addAttribute("orderList", orderList);
            return "consumer/mypage/orders";
        } else {
            // 판매자나 관리자인 경우
            return "err/denyPage";
        }
    }

    // 좋아요 보기
    @GetMapping("/{consumer_id}/likes")
    public String getAllLikes() {
        return "info/likes";
    }

    @GetMapping("/{consumer_id}/deliveries")
    public String getAllDeliveries() {
        return "info/deliveries";
    }

}
