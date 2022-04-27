package com.house.start.controller;

import com.house.start.controller.session.SessionConstants;
import com.house.start.domain.*;
import com.house.start.service.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
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

    /**
     * 소비자 정보 조회
     */
    @GetMapping("/admin/consumers")
    public String showConsumer (HttpServletRequest request, Model model) {
        log.info("--- admin controller - show consumers info -----------------------------------------");
        HttpSession session = request.getSession();

        // 세션이 없으면 홈으로 이동
        if (session.getAttribute(SessionConstants.LOGIN_MEMBER) == null) {
            return "err/notLogin";
        }else if (session.getAttribute(SessionConstants.LOGIN_MEMBER) == "admin") {
            // 관리자인 경우
            List<Consumer> consumerList = consumerService.findConsumers();
            model.addAttribute("consumerList", consumerList);
            return "admin/show_consumers";
        }else {
            // 소비자나 판매자인 경우
            return "err/denyPage";
        }
    }

    /**
     * 판매자 정보 조회
      */
    @GetMapping("/admin/sellers")
    public String showSellers(HttpServletRequest request, Model model) {
        log.info("--- admin controller - show sellers info -----------------------------------------");
        HttpSession session = request.getSession();

        // 세션이 없으면 홈으로 이동
        if (session.getAttribute(SessionConstants.LOGIN_MEMBER) == null) {
            return "err/notLogin";
        }else if (session.getAttribute(SessionConstants.LOGIN_MEMBER) == "admin") {
            // 관리자인 경우
            List<Seller> sellerList = sellerService.findSellers();
            model.addAttribute("sellerList",sellerList);
            return "admin/show_sellers";
        }else {
            // 소비자나 판매자인 경우
            return "err/denyPage";
        }

    }

    /**
     * 판매자 승인 처리
     */
    @GetMapping("/admin_sellers/approved/{seller_id}")
    public String approvedSeller (@PathVariable Long seller_id) {
        log.info("--- admin controller - show sellers approved -----------------------------------------");
        sellerService.approveSeller(seller_id);
        return "redirect:/admin/sellers";
    }

    /**
     * 판매자 승인 철회
      */
    @GetMapping("/admin_sellers/notapproved/{seller_id}")
    public String notapprovedSeller (@PathVariable Long seller_id) {
        log.info("--- admin controller - show sellers not approved -----------------------------------------");
        sellerService.notapproveSeller(seller_id);
        return "redirect:/admin/sellers";
    }

    /**
     * 게시글 정보 조회
      */
    @GetMapping("/admin/posts")
    public String showPosts(HttpServletRequest request, Model model) {
        log.info("--- admin controller - show posts info -----------------------------------------");
        HttpSession session = request.getSession();

        // 세션이 없으면 홈으로 이동
        if (session.getAttribute(SessionConstants.LOGIN_MEMBER) == null) {
            return "err/notLogin";
        }else if (session.getAttribute(SessionConstants.LOGIN_MEMBER) == "admin") {
            // 관리자인 경우
            List<Post> postList = postService.findPosts();
            model.addAttribute("postList",postList);
            return "admin/show_posts";
        }else {
            // 소비자나 판매자인 경우
            return "err/denyPage";
        }
    }

    /**
     * 상품 목록 조회
      */
    @GetMapping("/admin/items")
    public String showItems(HttpServletRequest request, Model model) {
        log.info("--- admin controller - show items info -----------------------------------------");
        HttpSession session = request.getSession();
        
        // 세션이 없으면 홈으로 이동
        if (session.getAttribute(SessionConstants.LOGIN_MEMBER) == null) {
            return "err/notLogin";
        }else if (session.getAttribute(SessionConstants.LOGIN_MEMBER) == "admin") {
            // 관리자인 경우
            List<Item> itemList = itemService.findItems();
            model.addAttribute("itemList",itemList);
            return "admin/show_items";
        }else {
            // 소비자나 판매자인 경우
            return "err/denyPage";
        }
    }

    /**
     * 주문 목록 조회
      */
    @GetMapping("/admin/orders")
    public String showOrders(HttpServletRequest request, Model model) {
        log.info("--- admin controller - show orders info -----------------------------------------");
        List<Order> orderList = orderService.findOrders();
        model.addAttribute("orderList",orderList);
        return "admin/show_orders";
    }

}

