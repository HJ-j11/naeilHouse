package com.house.start.repository;

import com.house.start.domain.Consumer;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class ConsumerRepository {

    private final EntityManager em;

    public ConsumerRepository(EntityManager em) {
        this.em = em;
    }

    public List<Consumer> findAll() {
        return em.createQuery("select c from Consumer", Consumer.class)
                .getResultList();
    }
}
