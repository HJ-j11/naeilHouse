package com.house.start.repository;

import com.house.start.domain.Item;
import com.house.start.domain.ItemStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {
    public List<Item> findByCategory(Long id);

    public List<Item> findByCart(ItemStatus itemStatus);

}
