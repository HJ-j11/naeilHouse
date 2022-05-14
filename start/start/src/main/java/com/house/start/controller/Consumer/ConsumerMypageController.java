package com.house.start.controller.Consumer;

import com.house.start.controller.form.LoginForm;
import com.house.start.controller.form.ReviewForm;
import com.house.start.controller.session.SessionConstants;
import com.house.start.domain.*;
import com.house.start.service.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

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
    private final ReviewService reviewService;
    private final LikeService likeService;
    private final OrderItemService orderItemService;

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
    public String getAllOrders(@ModelAttribute ReviewForm reviewForm, @PathVariable Long consumer_id, HttpServletRequest request, Model model) {
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
            List<Order> orderList = orderService.findOrderByConsumer(consumer);
            model.addAttribute("orderList", orderList);
            Long orderStatus = orderService.countOrderStaus();
            Long completeStatus = orderService.countCompleteStaus();
            model.addAttribute("orderStatus", orderStatus);
            model.addAttribute("completeStatus", completeStatus);
            model.addAttribute("paidStatus", orderStatus + completeStatus);
            return "consumer/mypage/orders";
        } else {
            // 판매자나 관리자인 경우
            return "err/denyPage";
        }
    }

    /**
     * 리뷰 데이터 입력 시, 로직 처리
     */
    @PostMapping("/{consumer_id}/orders/{orderitem_id}")
    public String createReview(@ModelAttribute @Validated ReviewForm reviewForm,
                               BindingResult bindingResult,
                               HttpServletRequest request,
                               @PathVariable Long consumer_id, @PathVariable String orderitem_id) {
        log.info("--- consumer mypage controller - post review data");
        if (bindingResult.hasErrors()) { // 입력한 reviewForm 형식이 안맞는 경우
            return "consumer/mypage/order";
        }
        String content = reviewForm.getContent();

        Consumer consumer = consumerService.findConsumerById(consumer_id);
        OrderItem orderItem = orderItemService.findOrderItemById(orderitem_id);
        Item item = orderItem.getItem();
        Review review = reviewService.saveReview(consumer, item, content);
        orderItem.setReviewYn(true);
        return "redirect:consumer/mypage/orders";
    }

    // 리뷰 보기
    @GetMapping("/{consumer_id}/reviews")
    public String getAllReviews(@PathVariable Long consumer_id, HttpServletRequest request, Model model) {
        log.info("--- consumer mypage controller - show user info -> reviews -----------------------------------------");
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

            // review 데이터
            List<Review> reviewList = reviewService.findReviewsByConsumer(consumer);
            model.addAttribute("reviewList", reviewList);
            return "consumer/mypage/reviews";
        } else {
            // 판매자나 관리자인 경우
            return "err/denyPage";
        }
    }

    // 좋아요 보기
    @GetMapping("/{consumer_id}/likes")
    public String getAllLikes(@PathVariable Long consumer_id, HttpServletRequest request, Model model) {
        log.info("--- consumer mypage controller - show user info -> likes -----------------------------------------");
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

            // likes 데이터
            List<Like> likesList = likeService.findLikesByConsumer(consumer);
            log.info("--- consumer mypage controller - show user info -> likes -----------------------------------------"+ likesList.size());
            model.addAttribute("likesList", likesList);
            return "consumer/mypage/likes";
        } else {
            // 판매자나 관리자인 경우
            return "err/denyPage";
        }
    }


}
