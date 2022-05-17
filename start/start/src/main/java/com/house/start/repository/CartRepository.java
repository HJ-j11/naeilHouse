package com.house.start.repository;

import com.house.start.domain.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {
    Cart findCartByConsumer_Id(@Param("consumerId") Long id);

}
