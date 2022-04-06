package com.house.start.controller;

import com.house.start.domain.Consumer;
import com.house.start.domain.Item;
import com.house.start.service.ConsumerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class AdminController {
    private final ConsumerService consumerService;

    @GetMapping("/admin/consumer")
    public String manageConsumer (Model model) { // ??더 자세히 보기일 경우에만 게시글, 댓글 리뷰를 보게하는 지
        // 회원 정보
        List<Consumer> consumers = consumerService.findConsumers();

        // 게시글 조회

        // 댓글 조회

        // 리뷰 조회

        return "admin_consumers";
    }
}
