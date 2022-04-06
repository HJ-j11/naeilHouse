package com.house.start.repository;

import com.house.start.domain.Consumer;
import com.house.start.domain.Order;
import com.house.start.domain.Seller;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class SellerRepository {

    private final EntityManager em;

    public SellerRepository(EntityManager em) {
        this.em = em;
    }

    public List<Seller> findAll() {
        return em.createQuery("select c from Seller", Seller.class)
                .getResultList();
    }

    public Seller findOne(Long seller_id) {
        return em.find(Seller.class, seller_id);
    }
}
