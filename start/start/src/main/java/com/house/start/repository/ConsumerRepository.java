package com.house.start.repository;

import com.house.start.domain.Consumer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public interface ConsumerRepository extends JpaRepository<Consumer, Long> {
    /*
    public List<String> findAllWith() {
        return em.createQuery("select c.id, c.cId, count(p.id) from Consumer c, Post p where c.id = p.consumer", String.class)
                .getResultList();
    }*/
}
