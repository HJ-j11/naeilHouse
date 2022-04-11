package com.house.start.service;

import com.house.start.domain.Seller;
import com.house.start.repository.SellerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class SellerService {
    private final SellerRepository sellerRepository;

    public List<Seller> findSellers() {
        return sellerRepository.findAll();
    }

    /**
     * 판매자 승인
     */
    @Transactional
    public void approveSeller(Long seller_id) {
        Seller seller = sellerRepository.findOne(seller_id);
        seller.setIsApproved(true);

    }

    /**
     * 판매자 승인 취소
     */
    @Transactional
    public void notapproveSeller(Long seller_id) {
        Seller seller = sellerRepository.findOne(seller_id);
        seller.setIsApproved(false);
    }
}
