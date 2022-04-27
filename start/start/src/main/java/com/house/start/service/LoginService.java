package com.house.start.service;

import com.house.start.domain.Admin;
import com.house.start.domain.Consumer;
import com.house.start.domain.Seller;
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
    private final ConsumerRepository consumerRepository;
    private final SellerRepository sellerRepository;
    private final AdminRepository adminRepository;

    /**
     * 소비자 로그인 서비스
     */
    public Consumer loginConsumer(String loginId, String pwd) {
        return consumerRepository.findBycId(loginId)
                .filter(c -> c.getPwd().equals(pwd))
                .orElse(null);
    }

    /**
     * 판매자 로그인 서비스
     */
    public Seller loginSeller(String loginId, String pwd) {
        return sellerRepository.findBysId(loginId)
                .filter(s -> s.getPwd().equals(pwd))
                .orElse(null);
    }

    /**
     * 관리자 로그인 서비스
     */
    public Admin loginAdmin(String loginId, String pwd) {
        return adminRepository.findByaId(loginId)
                .filter(c -> c.getPwd().equals(pwd))
                .orElse(null);
    }

}
