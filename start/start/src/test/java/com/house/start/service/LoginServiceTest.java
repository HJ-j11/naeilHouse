package com.house.start.service;

import static org.junit.Assert.*;

import com.house.start.domain.Admin;
import com.house.start.domain.Consumer;
import com.house.start.domain.Seller;
import com.house.start.repository.AdminRepository;
import com.house.start.repository.ConsumerRepository;
import com.house.start.repository.SellerRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class LoginServiceTest {
    @Autowired LoginService loginService;
    @Autowired ConsumerRepository consumerRepository;
    @Autowired SellerRepository sellerRepository;
    @Autowired AdminRepository adminRepository;


    @Test
    public void 소비자_로그인() throws Exception {
        //Given
        Consumer consumer = new Consumer();
        String name= "소비자";
        String cId = "consumer";
        String pwd = "password";
        consumer.setName(name);
        consumer.setCId(cId);
        consumer.setPwd(pwd);

        //When
        consumerRepository.save(consumer);
        Consumer test_consumer = loginService.loginConsumer(cId, pwd); // 로그인 ok
        Consumer test_noneID = loginService.loginConsumer("null", pwd); // 아이디가 없을 때
        Consumer test_nonePWD = loginService.loginConsumer(cId, "null"); // 비밀번호와 동일하지 않을 떄

        //Then
        assertEquals(consumer.getId(), test_consumer.getId());
        assertNull(test_noneID);
        assertNull(test_nonePWD);
    }

    @Test
    public void 판매자_로그인() throws Exception {
        //Given
        Seller seller = new Seller();
        String name= "판매자";
        String sId = "seller";
        String pwd = "password";
        seller.setName(name);
        seller.setSId(sId);
        seller.setPwd(pwd);

        //When
        sellerRepository.save(seller);
        Seller test_seller = loginService.loginSeller(sId, pwd);// 로그인 ok
        Seller test_noneID = loginService.loginSeller("null", pwd); // 아이디가 없을 때
        Seller test_nonePWD = loginService.loginSeller(sId, "null"); // 비밀번호와 동일하지 않을 떄

        //Then
        assertEquals(seller.getId(), test_seller.getId());
        assertNull(test_noneID);
        assertNull(test_nonePWD);
    }

    @Test
    public void 관리자_로그인() throws Exception {
        //Given
        Admin admin = new Admin();
        String name= "관리자";
        String aId = "admin";
        String pwd = "password";
        admin.setName(name);
        admin.setAId(aId);
        admin.setPwd(pwd);

        //When
        adminRepository.save(admin);
        Admin test_admin = loginService.loginAdmin(aId, pwd);// 로그인 ok
        Admin test_noneID = loginService.loginAdmin("null", pwd); // 아이디가 없을 때
        Admin test_nonePWD = loginService.loginAdmin(aId, "null"); // 비밀번호와 동일하지 않을 떄

        //Then
        assertEquals(admin.getId(), test_admin.getId());
        assertNull(test_noneID);
        assertNull(test_nonePWD);
    }


}