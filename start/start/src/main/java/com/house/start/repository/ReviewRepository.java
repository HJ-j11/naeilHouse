package com.house.start.repository;


import com.house.start.domain.entity.Member;
import com.house.start.domain.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {

    public List<Review> findAll();

    List<Review> findByMember(Member member);




}
