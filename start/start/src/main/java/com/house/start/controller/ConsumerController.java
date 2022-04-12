package com.house.start.controller;

import com.house.start.domain.Item;
import com.house.start.service.ItemService;
import com.house.start.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class ConsumerController {

    private final ItemService itemService;
    private final OrderService orderService;

    /**
     *  상품 목록 페이지
     */
    @GetMapping("/consumer/item/list")
    public String itemList(Model model) {

        List<Item> items = itemService.findItems();

        model.addAttribute("items", items);

        return "consumer_itemList";
    }

    /**
     *  상품 상세 페이지
     */
    @GetMapping("/consumer/item/{id}/info")
    public String itemInfo(@PathVariable Long id, Model model) {

        Item item = itemService.findItem(id);
        model.addAttribute("item", item);

        return "consumer_itemInfo";
    }

    /**
     *  상품 상세 -> 바로 구매시
     */
    @GetMapping("/consumer/item/{id}/purchase")
    public String purchase(@PathVariable Long id) {

        // 배송지 정보 (주소) 조회

        // 주문자 정보 조회

        // 아이템 정보 조회
        Item item = itemService.findItem(id);

        return "comsumer_beforePurchase";
    }

    /**
     *  상품 상세 -> 장바구니 담기
     */
    @PostMapping("/consumer/cart/{id}/add")
    public String addItemToCart(@PathVariable Long id) {

        // 소비자 정보 조회

        // 아이템 정보 조회
        Item item = itemService.findItem(id);

        // Order 생성
        // order.setOrderStatus(OrderStatus.CART);

        return "redirect:/consumer/cart/list";
    }


    /**
     *  장바구니 페이지
     */
//    @GetMapping("/consumer/cart/list")
//    public String cart() {

        // 소비자 정보 조회
        //

//        orderService.findCartOrder(consumerId);

}

