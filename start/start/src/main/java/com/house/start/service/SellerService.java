package com.house.start.service;

import com.house.start.domain.Item;
import com.house.start.domain.Seller;
import com.house.start.repository.SellerRespository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SellerService {

    private final SellerRespository sellerRespository;

    /**
     *  아이디로 한 명의 판매자 조회
     */
    public Seller findSeller(Long id) {
        return sellerRespository.findSeller(id);
    }
}
