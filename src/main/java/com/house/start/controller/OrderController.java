package com.house.start.controller;

import com.house.start.domain.Order;
import com.house.start.domain.entity.Member;
import com.house.start.service.ConsumerService;
import com.house.start.service.MemberService;
import com.house.start.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;
    private final ConsumerService consumerService;

    /**
     * 주문 취소
     */
    @GetMapping("/orderlist/{orderitem_id}/cancel")
    public void showOrder (Model model, @PathVariable Long orderitem_id) {
        orderService.cancelOrder(orderitem_id);
    }
}
