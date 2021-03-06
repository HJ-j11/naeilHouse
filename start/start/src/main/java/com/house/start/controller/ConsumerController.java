package com.house.start.controller;

import com.house.start.controller.form.CommentForm;
import com.house.start.controller.form.PostForm;
import com.house.start.controller.session.SessionConstants;
import com.house.start.domain.*;
import com.house.start.file.FileStore;
import com.house.start.service.ConsumerService;
import com.house.start.service.ItemService;
import com.house.start.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.*;


@Slf4j
@Controller
@RequiredArgsConstructor
public class  ConsumerController {
    private final ConsumerService consumerService;
    private final FileStore fileStore;
    private final ItemService itemService;
    private final OrderService orderService;



    // 물건 리스트
    @GetMapping("/list")
    public String getAllItem(HttpServletRequest request, Model model){
        List<Item> items = consumerService.getAllItems();
        model.addAttribute("items", items);
        return "consumer/item_list";
    }

    // 물건 카테고리
    @GetMapping("/list/{category_id}")
    public String getItemByCategory(@PathVariable Long category_id, Model model){
        List<Item> items = consumerService.getAllByCategory(category_id);
        model.addAttribute("items", items);
        return "";
    }

    // 물건 상세
    @GetMapping("/list/item/{id}")
    public String getOneItem(@PathVariable Long id, Model model){
        Item item = consumerService.getOneItem(id);
        model.addAttribute("item", item);
        return "consumer/consumer_itemInfo";
    }

    // 장바구니 담기
    @PostMapping("/item/{id}/cart")
    public String addItemToCart(@PathVariable Long id,
                                @RequestBody HashMap<String, Object> map,
                                @SessionAttribute(name = SessionConstants.LOGIN_MEMBER, required = false) Consumer loginConsumer,
                                HttpServletRequest request) {

        int count = Integer.parseInt(map.get("cnt").toString());
        Item item = itemService.findItem(id);
        Cart cart = consumerService.findByCart(loginConsumer);

        Long cartId = consumerService.addItemToCart(item, cart, count);
        System.out.println("cartId :"+cartId);
        return "redirect:/list";
    }

    // 배송 완료
    @PutMapping("/seller/deliveries/{id}/completed")
    public void getAllDelivery(@PathVariable Long id){
        consumerService.completeDelivery(id);
    }


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
     *  상품 상세 -> 바로 구매시 (결제 준비 페이지)
     */
    @GetMapping("/consumer/item/{id}/purchase")
    public String beforePurchase(@PathVariable Long id,
                           @SessionAttribute(name = SessionConstants.LOGIN_MEMBER, required = false) Consumer loginConsumer,
                           Model model) {

        // 세션에 회원 데이터가 없으면 예외처리
        if (loginConsumer == null) {
            return "redirect:/list";
        }

        // 소비자 id 조회
        Long consumerId = loginConsumer.getId();
        Consumer consumer = consumerService.findConsumerById(consumerId);

        // 상품 조회
        Item item = itemService.findItem(id);

        model.addAttribute("item", item);
//        model.addAttribute("totalPrice", item.getPrice());
        model.addAttribute("consumer", consumer);

        return "consumer_beforePurchase";
//        return "test_cartToBeforePurchase";
    }

    /**
     *  결제 준비 페이지 -> 결제하기 (상품 1개) : 결제 완료 페이지로 이동
     */
    @PostMapping("/consumer/item/{id}/purchase")
    public String afterPurchase(@PathVariable Long id,
                           @SessionAttribute(name = SessionConstants.LOGIN_MEMBER) Consumer loginConsumer,
                           Model model) {

        // 소비자 id 조회
        Long consumerId = loginConsumer.getId();

        // 상품 id 조회
        Long itemId = itemService.findItem(id).getId();
        Item item = itemService.findItem(id);

        // 주문 생성
        Long orderId = orderService.order(consumerId, itemId, 1); // 주문
        /*Optional<Order> completedOrder = orderService.findOrder(orderId); // 주문 완료된 객체 반환
        List<OrderItem> orderItems = completedOrder.get().getOrderItems();*/

//        model.addAttribute("orderItems", orderItems);
        model.addAttribute("totalPrice", item.getPrice());
        model.addAttribute("orderItems", item);

        // 주문 완료 페이지
        return "consumer_afterPurchase";
//        return "test_afterPurchase";
    }

    /**
     *  상품 상세 -> 장바구니 담기
     */
    @PostMapping("/consumer/cart/{id}/add")
    public String addItemToCart(@PathVariable Long id,
                                @SessionAttribute(name = SessionConstants.LOGIN_MEMBER) Consumer loginConsumer) {

        // 소비자 정보 조회
        Long consumerId = loginConsumer.getId();

        // 아이템 정보 조회
        Item item = itemService.findItem(id);

        // Order 생성
        // order.setOrderStatus(OrderStatus.CART);

        return "redirect:/cart";
    }

    /**
     *  장바구니 페이지
     */
    @GetMapping("/cart")
    public String cart(HttpServletRequest request,
                       @SessionAttribute(name = SessionConstants.LOGIN_MEMBER, required = false) Consumer loginConsumer,
                       Model model) {
        if(loginConsumer == null) {
            return "redirect:/login";
        }
        // 로그인 전제로
        Cart cart = consumerService.findByCart(loginConsumer);
        int totalPrice = cart.getTotalPrice();
        model.addAttribute("cartItems", cart.getCartItems());
        model.addAttribute("totalPrice", totalPrice);
        return "consumer/consumer_cart";
    }

    /**
     * 장바구니 -> 구매
     * 주문 객체 생성
     * **/
    @GetMapping("/order")
    public String beforeOrderItems(@SessionAttribute(name = SessionConstants.LOGIN_MEMBER) Consumer loginConsumer,
                             Model model) {
        if (loginConsumer == null) {
            return "redirect:/login";
        }
        // 상품 조회
        Cart cart = consumerService.findByCart(loginConsumer);

        // cart에 있는 List<cartItem> cartItems
        model.addAttribute("items", cart.getCartItems());
        model.addAttribute("totalPrice", cart.getTotalPrice());
        model.addAttribute("consumer", loginConsumer);
        return "consumer/consumer_order";
//        return "consumer_beforePurchase";

    }

    // 잠시 주석 처리해놓습니다.
    /*@PostMapping("/order")
    public String afterOrderItems( @SessionAttribute(name = SessionConstants.LOGIN_MEMBER) Consumer loginConsumer) {
        Long orderId = orderService.orders(loginConsumer);
        System.out.println("new OrderId : "+orderId+" created!");
        // 주문을 생성한 후 장바구니에 있는 상품 비우기.
        Cart cart = consumerService.findByCart(loginConsumer);
        cart.removeCartItem();
        return "consumer/item_list";
//        return "consumer_afterPurchase";
    }*/

    // 소비자 정보 조회`
    //

//        orderService.findCartOrder(consumerId);


}






