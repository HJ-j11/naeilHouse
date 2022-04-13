package com.house.start.controller;

import com.house.start.domain.Consumer;
import com.house.start.domain.Item;
import com.house.start.domain.Seller;
import com.house.start.service.ConsumerService;
import com.house.start.service.SellerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class AdminController {
    private final ConsumerService consumerService;
    private final SellerService sellerService;

    @GetMapping("/admin_consumers")
    public String manageConsumer (Model model) {
        //List<String> consumerList = consumerService.findConsumers();
        //System.out.println(consumerList);
        //model.addAttribute("consumerList", consumerList);
        return "admin/show_consumer";
    }

    @GetMapping("/admin_sellers")
    public String showSellers(String id, Model model) {
        List<Seller> sellerList = sellerService.findSellers();
        System.out.println(sellerList);
        model.addAttribute("sellerList",sellerList);
        return "admin/show_sellers";
    }

    @GetMapping("/admin_sellers/approved/{seller_id}")
    public String approvedSeller (@PathVariable Long seller_id) {
        sellerService.approveSeller(seller_id);
        return "redirect:/admin_sellers";
    }

    @GetMapping("/admin_sellers/notapproved/{seller_id}")
    public String notapprovedSeller (@PathVariable Long seller_id) {
        sellerService.notapproveSeller(seller_id);
        return "redirect:/admin_sellers";
    }

}

