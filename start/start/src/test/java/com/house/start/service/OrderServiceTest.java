package com.house.start.service;

import com.house.start.domain.Consumer;
import com.house.start.repository.OrderRepository;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class OrderServiceTest {
    @Autowired OrderService orderService;
    @Autowired OrderRepository orderRepository;


    @Test
    public void 주문취소() throws Exception {
        //Given

        //When

        //Then
    }


}