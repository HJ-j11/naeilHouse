package com.house.start.repository;

import com.house.start.domain.Item;
import com.house.start.domain.ItemStatus;
import com.house.start.domain.Seller;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.JpaQueryCreator;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {

    public List<Item> findByCategory(Long id);

    @Query("select i from Item i where i.seller.id = id")
    List<Item> findItemsBySeller(@Param(value = "id") Long id);


    /*@Query("select u from User u where u.username = :name")
    List<User> methodName(@Param("name") String username);*/

//    public List<Item> findByCart(ItemStatus itemStatus);

}
