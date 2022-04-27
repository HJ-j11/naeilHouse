package com.house.start.service;

import com.house.start.domain.Admin;
import com.house.start.domain.Consumer;
import com.house.start.domain.Item;
import com.house.start.domain.Seller;
import com.house.start.repository.AdminRepository;
import com.house.start.repository.ConsumerRepository;
import com.house.start.repository.SellerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class JoinService {

    private final ConsumerRepository consumerRepository;
    private final SellerRepository sellerRepository;
    private final AdminRepository adminRepository;

    /**
     *  소비자 회원가입
     */
    @Transactional
    public void joinConsumer(Consumer consumer) {
        consumerRepository.save(consumer);
    }

    /**
     *  판매자 회원가입
     */
    @Transactional
    public void joinSeller(Seller seller) {
        sellerRepository.save(seller);
    }

    /**
     *  판매자 회원가입
     */
    @Transactional
    public void joinAdmin(Admin admin) {
        adminRepository.save(admin);
    }
}
