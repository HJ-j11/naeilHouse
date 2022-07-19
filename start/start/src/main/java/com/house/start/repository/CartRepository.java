package com.house.start.repository;

import com.house.start.domain.Cart;
import com.house.start.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {

    Cart findByMember(Member member);

}
