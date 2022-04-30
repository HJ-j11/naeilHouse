package com.house.start.controller;

import com.house.start.controller.form.CommentForm;
import com.house.start.controller.form.PostForm;
import com.house.start.domain.*;
import com.house.start.file.FileStore;
import com.house.start.service.ConsumerService;
import com.house.start.service.ItemService;
import com.house.start.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequiredArgsConstructor
public class ConsumerController {
    private final ConsumerService consumerService;
    private final FileStore fileStore;
    private final ItemService itemService;
    private final OrderService orderService;

    private Logger logger = LoggerFactory.getLogger(ConsumerController.class);

    // 물건 리스트
    @GetMapping("/list")
    public String getAllItem(Model model){
        List<Item> items = consumerService.getAllItems();
        model.addAttribute("items", items);
        return "item_list";
    }

    // 물건 카테고리
    @GetMapping("list/{category_id}")
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
        return "item_info";
    }

    // 장바구니 담기
    @PutMapping("/list/item/{id}/getCart")
    public void goToCart(@PathVariable Long id) {
        consumerService.goToCart(id);
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

    //마이 페이지
    @GetMapping("/user")
    public String getUserInfo(@PathVariable Long id, Model model, HttpServletRequest request) {
        // 후에 session으로 교체할 예정.
        try {
            HttpSession session = request.getSession();
            String userId = session.getId();

        } catch(Exception e) {
            logger.error(e.getMessage());
        }

        Consumer user = consumerService.getConsumerInfo(id);
        model.addAttribute("user", user);
        return "info/user";
    }

    // 주문 보기
    @GetMapping("/user/orders")
    public String getAllOrders(Model model) {
        // 자신이 주문한 걸 가져와야할텐데..?

        return "info/orders";
    }

    // 리뷰 보기
    @GetMapping("/user/review")
    public String getAllReviews() {
        return "info/reviews";
    }

    // 좋아요 보기
    @GetMapping("/user/likes")
    public String getAllLikes() {
        return "info/likes";
    }

    @GetMapping("/user/deliveries")
    public String getAllDeliveries() {
        return "info/deliveries";
    }

    // 커뮤니티 목록
    @GetMapping("/community")
    public String getAllPost(Model model) {
        List<Post> posts = consumerService.getAllPost();
        model.addAttribute("postList", posts);
        return "post_list";
    }

    @GetMapping("/community/{id}")
    public String getOnePost(@PathVariable Long id, Model model) {
        Post post = consumerService.getOnePost(id);

        // 간편하게 넘기는 법 없을까?

        model.addAttribute("post", post);
        model.addAttribute("likes", post.countLikes());
        model.addAttribute("comments", post.getComments());
        return "post_detail";
    }


    // 글 작성 페이지
    @GetMapping("/community/new")
    public String getNewPost(Model model) {
        model.addAttribute("post", new PostForm());
        return "new_post";
    }

    // 글 작성
    @PostMapping("/community/write")
    public String postUser(@ModelAttribute PostForm post, HttpServletRequest request) throws IOException {
        logger.info(post.getContents());
        logger.info(String.valueOf(post.getPhoto()));

        UploadFile uploadFile = fileStore.storeFile(post.getPhoto(), request);

        try {
            HttpSession session = request.getSession();
            Consumer consumer = (Consumer) session.getAttribute("userInfo");
        } catch(Exception e) {
            logger.error("No session");
        }

        Post newPost = Post.builder()
                .contents(post.getContents())
                .uploadFile(uploadFile)
                .postDate(LocalDateTime.now())
                .build();

        consumerService.save(newPost);

        return "redirect:/community";
    }
    /**
     * 글 -> 좋아요 누르기
     * **/
    @PostMapping("/community/{id}/likes")
    public String putLikes(@PathVariable String id) {
        consumerService.putLikes(Long.valueOf(id));
        return "redirect:/community/"+id;
    }


    /**
     * 댓글
     * **/
    // 댓글 작성
    @PostMapping("/community/{id}/comments/write")
    public String postComment(@PathVariable String id, @RequestParam String contents) {
        consumerService.saveComment(id, contents);
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






