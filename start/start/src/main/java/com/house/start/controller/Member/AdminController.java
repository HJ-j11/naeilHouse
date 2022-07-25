package com.house.start.controller.Member;

import com.house.start.controller.session.SessionConstants;
import com.house.start.domain.*;
import com.house.start.domain.dto.Order.OrderAdminDTO;
import com.house.start.domain.dto.Order.OrderItem.OrderOrderItemAdminDTO;
import com.house.start.domain.dto.Post.PostAdminDTO;
import com.house.start.service.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

import static com.house.start.controller.session.SessionConstants.ROLE;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("admin")
public class AdminController {
    private final MemberService memberService;
    private final SellerService sellerService;
    private final PostService postService;
    private final ItemService itemService;
    private final OrderService orderService;

    /**
     * 소비자 정보 조회
     */
    @GetMapping("/consumers")
    public String showConsumer(HttpServletRequest request, Model model) {
        log.info("--- admin controller - show consumers info -----------------------------------------");
        HttpSession session = request.getSession();

        //소비자 정보 조회하기
        List<Member> consumerList = memberService.findConsumers();
        model.addAttribute("consumerList", consumerList);

        return "admin/showConsumers";
    }

    /**
     * 판매자 정보 조회
     */
    @GetMapping("/sellers")
    public String showSellers(HttpServletRequest request, Model model) {
        log.info("--- admin controller - show sellers info -----------------------------------------");
        HttpSession session = request.getSession();

        List<Member> sellerList = memberService.findSellers();
        model.addAttribute("sellerList", sellerList);

        return "admin/showSellers";

    }

    /**
     * 판매자 승인 처리
     */
    @GetMapping("/sellers/{seller_id}/approved")
    public String approvedSeller(@PathVariable Long seller_id) throws Exception {
        log.info("--- admin controller - show sellers approved -----------------------------------------");
        sellerService.approveSeller(seller_id);
        return "redirect:/admin/sellers";
    }

    /**
     * 판매자 승인 철회
     */
    @GetMapping("/sellers/{seller_id}/notapproved")
    public String notapprovedSeller(@PathVariable Long seller_id) throws Exception {
        log.info("--- admin controller - show sellers not approved -----------------------------------------");
        sellerService.notapproveSeller(seller_id);
        return "redirect:/admin/sellers";
    }

    /**
     * 게시글 정보 조회
     */
    @GetMapping("/posts")
    public String showPosts(HttpServletRequest request, Model model) {
        log.info("--- admin controller - show posts info -----------------------------------------");
        HttpSession session = request.getSession();

        if (session.getAttribute(ROLE) == null) {
            // 세션이 없으면 홈으로 이동
            return "err/notLogin";
        } else if (session.getAttribute(ROLE).toString().equals("admin")) {
            // 관리자인 경우
            List<PostAdminDTO> postAdminDTOS = new ArrayList<>();
            List<Post> postList = postService.findPosts();
            for (Post post: postList) {
                PostAdminDTO postAdminDTO = new PostAdminDTO(post);
                postAdminDTOS.add(postAdminDTO);
            }
            model.addAttribute("postList", postAdminDTOS);
            return "admin/showPosts";
        } else {
            // 소비자나 판매자인 경우
            log.info("in2");
            return "err/denyPage";
        }
    }

    /**
     * 상품 목록 조회
     */
    @GetMapping("/items")
    public String showItems(HttpServletRequest request, Model model) {
        log.info("--- admin controller - show items info -----------------------------------------");
        HttpSession session = request.getSession();

        // 세션이 없으면 홈으로 이동
        if (session.getAttribute(SessionConstants.LOGIN_MEMBER) == null) {
            return "err/notLogin";
        } else if (session.getAttribute(ROLE).toString().equals("admin")) {
            // 관리자인 경우
            List<Item> itemList = itemService.findItems();
            model.addAttribute("itemList", itemList);
            return "admin/showItems";
        } else {
            // 소비자나 판매자인 경우
            return "err/denyPage";
        }
    }

    /**
     * 주문 목록 조회
     */
    @GetMapping("/orders")
    public String showOrders(HttpServletRequest request, Model model) {
        log.info("--- admin controller - show orders info -----------------------------------------");
        HttpSession session = request.getSession();
        // 세션이 없으면 홈으로 이동
        if (session.getAttribute(SessionConstants.LOGIN_MEMBER) == null) {
            return "err/notLogin";
        } else if (session.getAttribute(ROLE).toString().equals("admin"))  {
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
        } else {
            // 소비자나 판매자인 경우
            return "err/denyPage";
        }
    }

}

