package com.house.start.repository;

import com.house.start.domain.Consumer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository
@RequiredArgsConstructor
public class ConsumerRepository {

    private final EntityManager em;

    /**
     *  소비자 정보 저장
     */
    public void save(Consumer consumer) {
        em.persist(consumer);
    }

    public Consumer findConsumer(Long consumerId) {
        return em.find(Consumer.class, consumerId);
    }

}
