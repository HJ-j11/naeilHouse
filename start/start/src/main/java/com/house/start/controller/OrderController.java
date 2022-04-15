package com.house.start.controller;

import com.house.start.domain.Order;
import com.house.start.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;

    @GetMapping("/orderlist")
    public String showOrder (Model model) {
        List<Order> orderList = orderService.findOrders();
        System.out.println(orderList);
        model.addAttribute("orderList", orderList);
        return "consumer/orderlist";
    }
}
