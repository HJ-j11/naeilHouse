package com.house.start.service;

import com.house.start.domain.Consumer;
import com.house.start.repository.AdminRepository;
import com.house.start.repository.ConsumerRepository;
import com.house.start.repository.SellerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class LoginService {
    private final EntityManager em;
    @Autowired
    ConsumerRepository consumerRepository;
    @Autowired
    SellerRepository sellerRepository;
    @Autowired
    AdminRepository adminRepository;

    public Consumer loginConsumer(String loginId, String pwd) {
        return consumerRepository.findBycId(loginId)
                .filter(c -> c.getPwd().equals(pwd))
                .orElse(null);
    }

}
