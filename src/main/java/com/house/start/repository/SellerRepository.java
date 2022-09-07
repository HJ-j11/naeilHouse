package com.house.start.repository;

import com.house.start.domain.Admin;
import com.house.start.domain.Consumer;
import com.house.start.domain.Order;
import com.house.start.domain.Seller;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

@Repository
public interface SellerRepository extends JpaRepository<Seller, Long> {

    // 판매자 아이디로 조회
    Optional<Seller> findBysId (String sId);


}
