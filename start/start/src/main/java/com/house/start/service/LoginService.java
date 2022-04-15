package com.house.start.service;

import com.house.start.domain.Consumer;
import com.house.start.repository.AdminRepository;
import com.house.start.repository.ConsumerRepository;
import com.house.start.repository.SellerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class LoginService {
    private final EntityManager em;
    ConsumerRepository consumerRepository;
    SellerRepository sellerRepository;
    AdminRepository adminRepository;

    public Consumer loginConsumer(String loginId, String pwd) {
        Consumer consumer = consumerRepository.findByCId(loginId);
        if (consumer.getPwd().equals(pwd)) {
            return consumer;
        } else{
            return null;
        }
    }

}
