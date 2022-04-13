package com.house.start.repository;

import com.house.start.domain.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {
    public List<Item> findByCategory(Long id);
//    private final EntityManager em;

    /**
     *  상품 등록
     */
    public void create(Item item);
//    public void create(Item item) {
//        em.persist(item);
//    }

    /**
     *  아이디로 1개 상품 조회
     */
    public Item findItem(Long id);
//    public Item findItem(Long id) {
//        return em.find(Item.class, id);
//    }

    /**
     *  모든 상품 조회
     */

//    public List<Item> findAll() {
//        return em.createQuery("select i from Item i", Item.class)
//                .getResultList();
//    }

//>>>>>>> develop
}
