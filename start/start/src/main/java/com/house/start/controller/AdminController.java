package com.house.start.controller;

import com.house.start.domain.Consumer;
import com.house.start.domain.Item;
import com.house.start.domain.Seller;
import com.house.start.service.ConsumerService;
import com.house.start.service.SellerService;
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

    // 소비자 정보 조회
    @GetMapping("/admin_consumers")
    public String showConsumer (Model model) {
        log.info("--- admin controller - show consumers info -----------------------------------------");
        //List<String> consumerList = consumerService.findConsumers();
        //System.out.println(consumerList);
        //model.addAttribute("consumerList", consumerList);
        return "admin/show_consumer";
    }

    // 판매자 정보 조회
    @GetMapping("/admin_sellers")
    public String showSellers(Model model) {
        log.info("--- admin controller - show sellers info -----------------------------------------");
        List<Seller> sellerList = sellerService.findSellers();
        System.out.println(sellerList);
        model.addAttribute("sellerList",sellerList);
        return "admin/show_sellers";
    }

    // 판매자 승인 처리
    @GetMapping("/admin_sellers/approved/{seller_id}")
    public String approvedSeller (@PathVariable Long seller_id) {
        log.info("--- admin controller - show sellers approved -----------------------------------------");
        sellerService.approveSeller(seller_id);
        return "redirect:/admin_sellers";
    }

    // 판매자 승인 철회
    @GetMapping("/admin_sellers/notapproved/{seller_id}")
    public String notapprovedSeller (@PathVariable Long seller_id) {
        log.info("--- admin controller - show sellers not approved -----------------------------------------");
        sellerService.notapproveSeller(seller_id);
        return "redirect:/admin_sellers";
    }

}

