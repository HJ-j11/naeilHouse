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

    // ?ordercontroller일지 consumercontroller가 맞을지 모르겠어서 ㅜㅜㅜ 회의 후에 여쭤보고 옮기겠습니다...!!
    @GetMapping("/orderlist")
    public String showOrder (Model model) {
        List<Order> orderList = orderService.findOrders();
        System.out.println(orderList);
        model.addAttribute("orderList", orderList);
        return "consumer/orderlist";
    }
}
