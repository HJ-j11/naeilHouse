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
     * 판매자 수락
     */
    public void updateSeller(Long seller_id) {
        Seller seller = sellerRepository.findOne(seller_id);
        seller.setIsApproved(Boolean.TRUE);
    }
}
