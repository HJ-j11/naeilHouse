package com.house.start.repository;

import com.house.start.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public interface TestRepository extends JpaRepository<Item, Long> {

    public List<Item> findByName(String name);

}
