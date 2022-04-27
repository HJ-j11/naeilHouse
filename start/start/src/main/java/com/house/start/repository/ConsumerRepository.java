package com.house.start.repository;

import com.house.start.domain.Admin;
import com.house.start.domain.Consumer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

@Repository
public interface ConsumerRepository extends JpaRepository<Consumer, Long> {

    // 소비자 아이디로 조회
    Optional<Consumer> findBycId (String cId);


}
