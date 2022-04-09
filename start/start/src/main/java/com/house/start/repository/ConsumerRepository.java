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

    public void save(Consumer consumer) {
        em.persist(consumer);
    }

    public List<String> findAllWith() {
        /*return em.createQuery("select c.id, c.name, c.point, count(p.id) as post_num, count(l.id) as likes_num, count(r.review_id) as review_num"
                        + "from Consumer c, Post p, Likes l, Review r"
                        + "where c.consumer_id = p.consumer_id and p.consumer_id = l.consumer_id and l.consumer_id = r.consumer_id" +
                        "group by c.id", String.class)
                .getResultList();*/
        return em.createQuery("select c.id, c.cId, count(p.id) from Consumer c, Post p where c.id = p.consumer", String.class)
                .getResultList();
    }
}
