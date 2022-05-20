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
public class ConsumerController {
    private final ConsumerService consumerService;
    private final FileStore fileStore;
    private final ItemService itemService;
    private final OrderService orderService;

    private Logger logger = LoggerFactory.getLogger(ConsumerController.class);
    private final ServletContext servletContext;

    // 물건 리스트
    @GetMapping("/list")
    public String getAllItem(HttpServletRequest request, Model model){
        List<Item> items = consumerService.getAllItems();
        model.addAttribute("items", items);
        return "item_list";
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
        return "tmp_itemInfo";
    }

    // 장바구니 담기
    @PostMapping("/item/{id}/cart")
    public void addItemToCart(@PathVariable Long id,
                                @RequestBody HashMap<String, Object> map,
                                @SessionAttribute(name = SessionConstants.LOGIN_MEMBER, required = false) Consumer loginConsumer,
                                HttpServletRequest request) {

        int count = Integer.parseInt(map.get("cnt").toString());
        Item item = itemService.findItem(id);
        Cart cart = consumerService.findByCart(loginConsumer);

        Long cartItemId = consumerService.addItemToCart(item, cart, count);

        System.out.println("cartItemId after:  "+cartItemId);


    }


    // 장바구니
//    @GetMapping("/cart")
//    public String getCarts(Model model){
//        List<Item> itemList = consumerService.findByCart(ItemStatus.CART);
//        model.addAttribute("itemList", itemList);
//        return "cart";
//    }


    // 배송 완료
    @PutMapping("/user/deliveries/{id}/completed")
    public void getAllDelivery(@PathVariable Long id){
        consumerService.completeDelivery(id);
    }

    // 커뮤니티 목록
    @GetMapping("/community")
    public String getAllPost(Model model) {
        String realPath = servletContext.getRealPath("/resources");
        logger.info("realPath:  "+realPath);

        List<Post> posts = consumerService.getAllPost();
        model.addAttribute("postList", posts);
        return "post_list";
    }

    @GetMapping("/community/{id}")
    public String getOnePost(@PathVariable Long id, Model model) {
        Post post = consumerService.getOnePost(id);

        model.addAttribute("post", post);
        model.addAttribute("likes", post.countLikes());
        model.addAttribute("comments", post.getComments());
//        return "post_detail";
        return "consumer_postDetail";
    }


    // 글 작성 페이지
    @GetMapping("/community/new")
    public String getNewPost(Model model) {
        model.addAttribute("post", new PostForm());
        return "consumer_newPost";
    }

    // 글 작성
    @PostMapping("/community/write")
    public String postUser(@ModelAttribute PostForm post,
                           @SessionAttribute(name = SessionConstants.LOGIN_MEMBER, required = false) Consumer loginConsumer,
                           HttpServletRequest request) throws IOException {

        logger.info(post.getContents());
        logger.info(String.valueOf(post.getPhoto()));

        UploadFile uploadFile = fileStore.storeFile(post.getPhoto(), request);

        Post newPost = Post.builder()
                .contents(post.getContents())
                .uploadFile(uploadFile)
                .consumer(loginConsumer)
                .postDate(LocalDateTime.now())
                .build();

        consumerService.savePost(newPost);

        return "redirect:/community";
    }
    /**
     * 글 -> 좋아요 누르기
     * **/
    @PostMapping("/community/{id}/likes")
    public String putLikes(@PathVariable String id, @SessionAttribute(name = SessionConstants.LOGIN_MEMBER, required = false) Consumer loginConsumer) {

        consumerService.putLikes(Long.valueOf(id), loginConsumer);
        return "redirect:/community/"+id;
    }


    /**
     * 댓글
     * **/
    // 댓글 작성
    @PostMapping("/community/{id}/comments/write")
    public String postComment(@PathVariable String id, @RequestParam String contents, @SessionAttribute(name = SessionConstants.LOGIN_MEMBER, required = false) Consumer loginConsumer) {
        consumerService.saveComment(id, contents, loginConsumer);
        return "redirect:/community/"+id;
    }

    // 댓글 수정
    @PutMapping("/comments/{id}/put")
    public String putComment(@PathVariable String id, @RequestBody CommentForm commentForm, Model model) {
        consumerService.updateComment(commentForm.getId(), commentForm.getContent());
        model.addAttribute("ACCESS", "SUCCESS");
        return "redirect:/community/"+commentForm.getPostId();
    }

    // 댓글 삭제
    @DeleteMapping("/comments/{id}/delete")
    public String deleteComment(@PathVariable Long id, Model model) {
        consumerService.deleteComment(id);
        model.addAttribute("ACESS", "SUCCESS");
        return "redirect:/community/";
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
        model.addAttribute("consumer", consumer);

//        orderService.order(consumerId, id, 1);

        return "consumer_beforePurchase";
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
        Optional<Order> completedOrder = orderService.findOrder(orderId); // 주문 완료된 객체 반환
        List<OrderItem> orderItems = completedOrder.get().getOrderItems();

        model.addAttribute(orderItems);
        model.addAttribute(item);

        // 주문 완료 페이지
        return "consumer_afterPurchase";
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

        return "redirect:/cart";
    }

    /**
     *  장바구니 페이지
     */
    @GetMapping("/cart")
    public String cart(HttpServletRequest request,
                       @SessionAttribute(name = SessionConstants.LOGIN_MEMBER, required = false) Consumer loginConsumer,
                       Model model) {
        // 로그인 전제로
        Cart cart = consumerService.findByCart(loginConsumer);
        model.addAttribute("cart", cart);

        return "consumer_cart";
    }
    /**
     * 장바구니 -> 구매
     * 주문 객체 생성
     * **/

    @PostMapping("/order")
    public String createOrder(@SessionAttribute(name = SessionConstants.LOGIN_MEMBER) Consumer loginConsumer
                            , HttpServletRequest request) {
        Long orderId = orderService.orders(loginConsumer);
        return "sample_order";
    }
    // 소비자 정보 조회`
    //

//        orderService.findCartOrder(consumerId);


}






