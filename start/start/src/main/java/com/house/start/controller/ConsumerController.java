package com.house.start.controller;

import com.house.start.controller.session.SessionConstants;
import com.house.start.domain.*;
import com.house.start.domain.dto.Cart.CartDto;
import com.house.start.domain.dto.Cart.CartItemDto;
import com.house.start.domain.dto.Item.ItemDto;
import com.house.start.domain.entity.Member;
import com.house.start.file.FileStore;
import com.house.start.service.ConsumerService;
import com.house.start.service.ItemService;
import com.house.start.service.impl.MemberServiceImpl;
import com.house.start.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.*;


@Slf4j
@Controller
@RequiredArgsConstructor
public class ConsumerController {
    private final ConsumerService consumerService;
    private final MemberServiceImpl memberService;
    private final FileStore fileStore;
    private final ItemService itemService;
    private final OrderService orderService;



    // 상품 리스트
    @GetMapping("/list")
    public String getAllItem(HttpServletRequest request, Model model){
        List<ItemDto> items = consumerService.getAllItems();
        model.addAttribute("items", items);
        return "consumer/item_list";
    }

   // 상품 상세
    @GetMapping("/list/item/{id}")
    public String getOneItem(@PathVariable Long id, Model model, @AuthenticationPrincipal Member member){
        ItemDto item = consumerService.getOneItem(id);
        model.addAttribute("item", item);
        return "consumer/consumer_itemInfo";
    }

    // 장바구니 담기
    @PostMapping("/item/{id}/cart")
    public String addItemToCart(@PathVariable Long id,
                                @RequestBody HashMap<String, Object> map,
                                @AuthenticationPrincipal Member loginMember) {

        int count = Integer.parseInt(map.get("cnt").toString());
        Item item = itemService.findItem(id);
        Cart cart = consumerService.findByCart(loginMember);

        Long cartId = consumerService.addItemToCart(item, cart, count);
        System.out.println("cartId :" + cartId);
        return "redirect:/list";
    }

    // 배송 완료
    @PutMapping("/seller/deliveries/{id}/completed")
    public void getAllDelivery(@PathVariable Long id) {
        consumerService.completeDelivery(id);
    }


    /**
     * 상품 목록 페이지
     */
    @GetMapping("/consumer/item/list")
    public String itemList(Model model) {
        List<Item> items = itemService.findItems();
        model.addAttribute("items", items);
        return "consumer_itemList";
    }

    /**
     * 상품 상세 페이지
     */
    @GetMapping("/consumer/item/{id}/info")
    public String itemInfo(@PathVariable Long id, Model model) {

        Item item = itemService.findItem(id);
        model.addAttribute("item", item);

        return "consumer_itemInfo";
    }

    /**
     * 상품 상세 -> 바로 구매시 (결제 준비 페이지)
     */
    @GetMapping("/consumer/item/{id}/purchase")
    public String beforePurchase(@PathVariable Long id,
                                 @AuthenticationPrincipal Member member,
                                 Model model) {

        // 상품 조회
        Item item = itemService.findItem(id);

        model.addAttribute("item", item);
//        model.addAttribute("totalPrice", item.getPrice());
        model.addAttribute("consumer", member);

        return "consumer_beforePurchaseTmp";
//        return "test_cartToBeforePurchase";
    }

    /**
     * 결제 준비 페이지 -> 결제하기 (상품 1개) : 결제 완료 페이지로 이동
     */
    @PostMapping("/consumer/item/{id}/purchase")
    public String afterPurchase(@PathVariable Long id,
                                @AuthenticationPrincipal Member member,
                                Model model) {

        // 상품 id 조회
        Long itemId = itemService.findItem(id).getId();
        Item item = itemService.findItem(id);

        // 주문 생성
        Long orderId = orderService.order(member.getId(), id, 1); // 주문
        /*Optional<Order> completedOrder = orderService.findOrder(orderId); // 주문 완료된 객체 반환
        List<OrderItem> orderItems = completedOrder.get().getOrderItems();*/

//        model.addAttribute("orderItems", orderItems);
        model.addAttribute("totalPrice", item.getPrice());
        model.addAttribute("orderItems", item);

        // 주문 완료 페이지
        return "consumer_afterPurchaseTmp";
    }

    /**
     * 장바구니 페이지
     */
    @GetMapping("/cart")
    public String cart(@AuthenticationPrincipal Member loginMember,
                       Model model) {

        System.out.println("------ Member Id: "+ loginMember + "-------");

        if(loginMember == null) {
            return "redirect:/login";
        }

        // 로그인 전제로
        Cart cart = consumerService.findByCart(loginMember);
        List<CartItemDto> cartItem = consumerService.getCartItemDto(cart);
        int totalPrice = cart.getTotalPrice();
        model.addAttribute("cartItems", cartItem);
        model.addAttribute("totalPrice", totalPrice);
        return "consumer/consumer_cart";
    }

    /**
     * 장바구니 -> 구매
     * 주문 객체 생성
     **/
    @GetMapping("/order")
    public String beforeOrderItems(@AuthenticationPrincipal Member loginMember,
                             Model model) {
        if (loginMember == null) {
            return "redirect:/login";
        }
        // 상품 조회
        Cart cart = consumerService.findByCart(loginMember);

        // cart에 있는 List<cartItem> cartItems
        model.addAttribute("items", cart.getCartItems());
        model.addAttribute("totalPrice", cart.getTotalPrice());
        model.addAttribute("consumer", loginMember);
        return "consumer/consumer_order";

    }

}






