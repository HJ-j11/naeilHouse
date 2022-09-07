package com.house.start.controller;

import com.house.start.domain.Order;
import com.house.start.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;

    /**
     * 주문 취소
     */
    @GetMapping("/orderlist")
    public String showOrder (Model model) {
        List<Order> orderList = orderService.findOrders();
        model.addAttribute("orderList", orderList);
        return "consumer/orderlist";
    }
}
