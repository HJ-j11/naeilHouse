package com.house.start.controller;

import com.house.start.domain.*;
import com.house.start.domain.dto.Item.ItemAdminDTO;
import com.house.start.domain.dto.Item.ItemDTO;
import com.house.start.domain.dto.Order.OrderAdminDTO;
import com.house.start.domain.dto.Order.OrderItem.OrderOrderItemAdminDTO;
import com.house.start.domain.dto.Post.PostAdminDTO;
import com.house.start.service.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("admin")
public class AdminController {
    private final ConsumerService consumerService;
    private final SellerService sellerService;
    private final PostService postService;
    private final ItemService itemService;
    private final OrderService orderService;

    /**
     * 소비자 정보 조회
     */
    @GetMapping("/consumers")
    public String showConsumer(Model model) {
        log.info("--- admin controller - show consumers info -----------------------------------------");

//            List<Consumer> consumerList = consumerService.findConsumers();
//            model.addAttribute("consumerList", consumerList);
        return "admin/showConsumers";
    }

    /**
     * 판매자 정보 조회
     */
    @GetMapping("/sellers")
    public String showSellers( Model model) {
        log.info("--- admin controller - show sellers info -----------------------------------------");
//            List<Seller> sellerList = sellerService.findSellers();
//            model.addAttribute("sellerList", sellerList);
        return "admin/showSellers";

    }

    /**
     * 판매자 승인 처리
     */
    @GetMapping("/sellers/{seller_id}/approved")
    public String approvedSeller(@PathVariable Long seller_id) {
        log.info("--- admin controller - show sellers approved -----------------------------------------");
        sellerService.approveSeller(seller_id);
        return "redirect:/admin/sellers";
    }

    /**
     * 판매자 승인 철회
     */
    @GetMapping("/sellers/{seller_id}/notapproved")
    public String notapprovedSeller(@PathVariable Long seller_id) {
        log.info("--- admin controller - show sellers not approved -----------------------------------------");
        sellerService.notapproveSeller(seller_id);
        return "redirect:/admin/sellers";
    }

    /**
     * 게시글 정보 조회
     */
    @GetMapping("/posts")
    public String showPosts(Model model) {
        log.info("--- admin controller - show posts info -----------------------------------------");
        // 관리자인 경우
        List<PostAdminDTO> postAdminDTOS = new ArrayList<>();
        List<Post> postList = postService.findPosts();
        for (Post post: postList) {
            PostAdminDTO postAdminDTO = new PostAdminDTO(post);
            postAdminDTOS.add(postAdminDTO);
        }
        model.addAttribute("postList", postAdminDTOS);
        return "admin/showPosts";
    }

    @GetMapping("/posts_data")
    public ResponseEntity<?> findPostPage(@PageableDefault(size = 7) Pageable pageable) {
        log.info("--- admin controller - find postAdminDTO info -----------------------------------------");
        List<PostAdminDTO> addd = postService.findPostDTO(pageable).getContent();
        log.info(addd.toString());
        return ResponseEntity.ok(addd);
    }

    /**
     * 상품 목록 조회
     */
    @GetMapping("/items")
    public String showItems(Model model) {
        log.info("--- admin controller - show items info -----------------------------------------");

        List<ItemAdminDTO> itemAdminDTOList = itemService.findItems()
                .stream()
                .filter(item -> item != null)
                .map(ItemAdminDTO::new)
                .collect(Collectors.toList());
        model.addAttribute("itemList", itemAdminDTOList);
        return "admin/showItems";
    }

    /**
     * 주문 목록 조회
     */
    @GetMapping("/orders")
    public String showOrders(Model model) {
        log.info("--- admin controller - show orders info -----------------------------------------");
        // 관리자인 경우
        List<Order> orderList = orderService.findOrders();
        List<OrderAdminDTO> orderDTOList = new ArrayList<>();
        for (Order order: orderList) {
            List<OrderItem> orderItemList = order.getOrderItems();
            List<OrderOrderItemAdminDTO> orderOrderItemDTOList = new ArrayList<>();
            for (OrderItem orderItem: orderItemList) {
                OrderOrderItemAdminDTO orderOrderItemDTO = OrderOrderItemAdminDTO.builder()
                        .orderItem(orderItem)
                        .build();
                orderOrderItemDTOList.add(orderOrderItemDTO);
            }
            OrderAdminDTO orderDTO = OrderAdminDTO.builder()
                    .order(order)
                    .orderItems(orderOrderItemDTOList)
                    .build();
            orderDTOList.add(orderDTO);
        }
        model.addAttribute("orderList", orderDTOList);
        return "admin/showOrders";
    }

}

