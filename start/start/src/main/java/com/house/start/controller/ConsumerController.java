package com.house.start.controller;


import com.house.start.service.ConsumerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

@Controller
public class ConsumerController {
    @Autowired
    ConsumerService consumerService;

    // 물건 리스트
    @GetMapping("/list")
    public String getAllItem(){
        return "";
    }
    // 물건 카테고리
    @GetMapping("list/{id}")
    public String getItemByCategory(){
        return "";
    }

    // 물건 상세
    @GetMapping("/list/{category_id}/{id}")
    public String getOneItem(){
        return "";
    }

    // 장바구니
    @GetMapping("/cart")
    public String getCarts(){
        return "";
    }


    // 배송 완료
    @GetMapping("/user/deliveries/{id}/completed")
    public String getAllDelivery(String id){
        return "";
    }

    //마이 페이지
    @GetMapping("/user")
    public String getUserInfo() {
        return "info/user";
    }

    // 주문 보기
    @GetMapping("/user/orders")
    public String getAllOrders() {
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
    public String getAllPost() {
        return "community/communities";
    }

    @GetMapping("/community/{id}")
    public String getOnePost() {
        return "community/post";
    }

    @PutMapping("/community/{id}/like")
    public void putLikes() {

    }

    // 글 작성 페이지
    @GetMapping("/community/new")
    public String getNewPost() {
        return "communitiy/likes";
    }

    // 글 작성
    @PostMapping("/community/write")
    public void postNew() {

    }

    // 댓글 등록
    @PostMapping("/comments/write")
    public void postComment() {

    }

    // 댓글 수정
    @PutMapping("/comments/{id}/put")
    public void putComment() {

    }

    @DeleteMapping("/comments/{id}/delete")
    public void deleteComment() {

    }

}
