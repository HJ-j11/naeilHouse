package com.house.start.repository;

import com.house.start.domain.Delivery;
import com.house.start.domain.Order;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository
public class DeliveryRepository {
    private final EntityManager em;

    public DeliveryRepository(EntityManager em) {
        this.em = em;
    }

    public Delivery findOne(Long delivery_id) {
        return em.find(Delivery.class, delivery_id);
    }

    public void deleteDelivery(Delivery delivery) {
        em.remove(delivery);
    }

}
