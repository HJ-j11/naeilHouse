package com.house.start.controller;

import com.house.start.domain.*;
import com.house.start.service.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
public class AdminController {
    private final ConsumerService consumerService;
    private final SellerService sellerService;
    private final PostService postService;
    private final ItemService itemService;
    private final OrderService orderService;

    // 소비자 정보 조회
    @GetMapping("/admin/consumers")
    public String showConsumer (Model model) {
        log.info("--- admin controller - show consumers info -----------------------------------------");
        List<Consumer> consumerList = consumerService.findConsumers();
        model.addAttribute("consumerList", consumerList);
        return "admin/show_consumers";
    }

    // 판매자 정보 조회
    @GetMapping("/admin/sellers")
    public String showSellers(Model model) {
        log.info("--- admin controller - show sellers info -----------------------------------------");
        List<Seller> sellerList = sellerService.findSellers();
        model.addAttribute("sellerList",sellerList);
        return "admin/show_sellers";
    }

    // 판매자 승인 처리
    @GetMapping("/admin_sellers/approved/{seller_id}")
    public String approvedSeller (@PathVariable Long seller_id) {
        log.info("--- admin controller - show sellers approved -----------------------------------------");
        sellerService.approveSeller(seller_id);
        return "redirect:/admin/sellers";
    }

    // 판매자 승인 철회
    @GetMapping("/admin_sellers/notapproved/{seller_id}")
    public String notapprovedSeller (@PathVariable Long seller_id) {
        log.info("--- admin controller - show sellers not approved -----------------------------------------");
        sellerService.notapproveSeller(seller_id);
        return "redirect:/admin/sellers";
    }

    // 게시글 정보 조회
    @GetMapping("/admin/posts")
    public String showPosts(Model model) {
        log.info("--- admin controller - show posts info -----------------------------------------");
        List<Post> postList = postService.findPosts();
        model.addAttribute("postList",postList);
        return "admin/show_posts";
    }

    // 상품 목록 조회
    @GetMapping("/admin/items")
    public String showItems(Model model) {
        log.info("--- admin controller - show items info -----------------------------------------");
        List<Item> itemList = itemService.findItems();
        model.addAttribute("itemList",itemList);
        return "admin/show_items";
    }

    // 주문 목록 조회
    @GetMapping("/admin/orders")
    public String showOrders(Model model) {
        log.info("--- admin controller - show orders info -----------------------------------------");
        List<Order> orderList = orderService.findOrders();
        model.addAttribute("orderList",orderList);
        return "admin/show_orders";
    }

}

