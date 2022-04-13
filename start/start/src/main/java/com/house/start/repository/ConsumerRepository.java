package com.house.start.repository;

import com.house.start.domain.Consumer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository
public interface ConsumerRepository extends JpaRepository<Consumer, Long> {

//    private final EntityManager em;

    /**
     *  소비자 정보 저장
     */
//    public void save(Consumer consumer) {
//        em.persist(consumer);
//    }
    public Consumer findConsumer(Long consumerId);
//    public Consumer findConsumer(Long consumerId) {
//        return em.find(Consumer.class, consumerId);
//    }

    /*
    public List<String> findAllWith() {
        return em.createQuery("select c.id, c.cId, count(p.id) from Consumer c, Post p where c.id = p.consumer", String.class)
                .getResultList();
    }*/
}
