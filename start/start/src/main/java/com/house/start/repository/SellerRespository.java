package com.house.start.repository;

import com.house.start.domain.Item;
import com.house.start.domain.Seller;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository
@RequiredArgsConstructor
public class SellerRespository {

    private final EntityManager em;

    /**
     *  아이디로 한 명의 판매자 조회
     */
    public Seller findSeller(Long id) {
        return em.find(Seller.class, id);
    }


}
