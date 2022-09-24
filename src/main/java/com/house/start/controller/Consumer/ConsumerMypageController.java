package com.house.start.controller.Consumer;

import com.house.start.controller.form.ReviewForm;
import com.house.start.controller.session.SessionConstants;
import com.house.start.domain.*;
import com.house.start.domain.dto.Member.Consumer.ConsumerMypageDTO;
import com.house.start.domain.dto.Member.Consumer.Order.ConsumerMypageOrderDTO;
import com.house.start.domain.entity.Member;
import com.house.start.service.*;
import com.house.start.service.impl.MemberServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
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
    private final MemberServiceImpl memberService;
    private final OrderService orderService;
    private final ReviewService reviewService;
    private final LikeService likeService;
    private final OrderItemService orderItemService;

    /**
     * 마이페이지 처음 페이지
     */
    @GetMapping("")
    public String getUserInfo(HttpServletRequest request, Model model,
                              @AuthenticationPrincipal Member member) {
        log.info("--- consumer mypage controller - show user info -----------------------------------------");

        if (member == null ) {
            return "err/notLogin";
        }

        // 소비자인 경우
        member = memberService.findMemberById(member.getId());
        model.addAttribute("member", new ConsumerMypageDTO(member));
        return "consumer/mypage/user";
    }

    /**
     * 마이페이지에서 주문 보기
     */
    @GetMapping("/orders")
    public String getAllOrders(@ModelAttribute ReviewForm reviewForm, HttpServletRequest request, Model model,
                               @AuthenticationPrincipal Member member) {
        log.info("--- consumer mypage controller - show user info -> order -----------------------------------------");
        // mypage 기본 필수 데이터
        member = memberService.findMemberById(member.getId());
        model.addAttribute("member", new ConsumerMypageDTO(member));

        // orders 데이터
        List<Order> orderList = orderService.findOrderByConsumer(member);
        List<ConsumerMypageOrderDTO> consumerMypageOrderDTOList = new ArrayList<>();
        for (Order order: orderList) {
            consumerMypageOrderDTOList.add(new ConsumerMypageOrderDTO(order));
        }
        model.addAttribute("orderList", consumerMypageOrderDTOList
        );
        List<Long> statusList = orderService.countStatus(orderList);
        Long prepareStatus = statusList.get(0);
        Long orderStatus = statusList.get(2);
        Long completeStatus = statusList.get(1);

        model.addAttribute("prepareStatus", prepareStatus);
        model.addAttribute("orderStatus", orderStatus);
        model.addAttribute("completeStatus", completeStatus);
        model.addAttribute("paidStatus", orderStatus + completeStatus);
        return "consumer/mypage/orders";
    }

    /**
     * 리뷰 데이터 입력 시, 로직 처리
     */
    @PostMapping("/{consumer_id}/orders/{orderitem_id}")
    public String createReview(@ModelAttribute @Validated ReviewForm reviewForm,
                               BindingResult bindingResult,
                               HttpServletRequest request,
                               @PathVariable Long consumer_id, @PathVariable Long orderitem_id,
                               @AuthenticationPrincipal Member member) {
        log.info("--- consumer mypage controller - post review data");
        if (bindingResult.hasErrors()) { // 입력한 reviewForm 형식이 안맞는 경우
            return "consumer/mypage/order";
        }
        String content = reviewForm.getContent();

        OrderItem orderItem = orderItemService.findOrderItemById(orderitem_id);
        Item item = orderItem.getItem();
        Review review = reviewService.saveReview(member, item, content);
        return "redirect:/user/orders";
    }

    // 리뷰 보기
    @GetMapping("/reviews")
    public String getAllReviews(HttpServletRequest request, Model model, @AuthenticationPrincipal Member member) {
        log.info("--- consumer mypage controller - show user info -> reviews -----------------------------------------");
        // mypage 기본 필수 데이터
        member = memberService.findMemberById(member.getId());
        model.addAttribute("member", new ConsumerMypageDTO(member));

        // review 데이터
        List<Review> reviewList = reviewService.findReviewsByConsumer(member);
        log.info("check-----------------"+reviewList.toString());
        model.addAttribute("reviewList", reviewList);
        return "consumer/mypage/reviews";
    }

    // 좋아요 보기
    @GetMapping("/likes")
    public String getAllLikes(HttpServletRequest request, Model model, @AuthenticationPrincipal Member member) {
        log.info("--- consumer mypage controller - show user info -> likes -----------------------------------------");
        // mypage 기본 필수 데이터
        member = memberService.findMemberById(member.getId());
        model.addAttribute("member", new ConsumerMypageDTO(member));

        // likes 데이터
        List<Like> likesList = likeService.findLikesByConsumer(member);
        log.info("--- consumer mypage controller - show user info -> likes -----------------------------------------"+ likesList.size());
        model.addAttribute("likesList", likesList);
        return "consumer/mypage/likes";
    }


}
