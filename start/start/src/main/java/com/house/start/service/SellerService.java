package com.house.start.service;

import com.house.start.domain.Seller;
import com.house.start.repository.SellerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class SellerService {
    @Autowired
    SellerRepository sellerRepository;

    private final EntityManager em;

    public List<Seller> findSellers() {
        return sellerRepository.findAll();
    }

    /**
     * 판매자 승인
     */
    @Transactional
    public void approveSeller(Long seller_id) {
        Seller seller = sellerRepository.findById(seller_id).get();
        seller.setIsApproved(true);

    }

    /**
     * 판매자 승인 취소
     */
    @Transactional
    public void notapproveSeller(Long seller_id) {
        Seller seller = sellerRepository.findById(seller_id).get();
        seller.setIsApproved(false);
    }
}
