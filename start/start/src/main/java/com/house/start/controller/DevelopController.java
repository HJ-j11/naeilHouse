package com.house.start.controller;

import com.house.start.domain.Consumer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;

@Controller
@Transactional
@RequiredArgsConstructor
public class DevelopController {

//    private final EntityManager em;
//
//    /**
//     *  소비자 테스트 데이터 초기화
//     */
//    @PostConstruct
//    public void init() {
//        Consumer consumerA = new Consumer();
//        consumerA.setName("회원A");
//        consumerA.setPoint(100000);
//        consumerA.setCId("memberA");
//        consumerA.setPwd("1234");
//
//        Consumer consumerB = new Consumer();
//        consumerB.setName("회원B");
//        consumerB.setPoint(200000);
//        consumerB.setCId("memberB");
//        consumerB.setPwd("5678");
//
//        em.persist(consumerA);
//        em.persist(consumerB);
//    }


    @GetMapping("/sample")
    public String sample() {
        return "sample";
    }

    @GetMapping("/")
    public String main() {
        return "main";
    }

    @GetMapping("/admin/consumerSetting")
    public String consumerManagement() {
        return "admin_회원관리";
    }

    @GetMapping("/admin")
    public String adminHome() {
        return "admin_default";
    }

    @GetMapping("/items")
    public String itemList() {
        return "itemList";
    }

    @GetMapping("/consumer/top/bar")
    public String consumerTopBar() { return "consumer_top_bar"; }





}
