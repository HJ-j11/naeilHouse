package com.house.start.service;

import com.house.start.domain.Item;
import com.house.start.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ItemService {

    private final ItemRepository itemRepository;

    /**
     *  상품 등록
     */
    @Transactional
    public void createItem(Item item) {
        itemRepository.create(item);
    }

    /**
     *  아이디로 1개 상품 조회
     */
    public Item findItem(Long id) {
        return itemRepository.findItem(id);
    }

    /**
     *  모든 상품 조회
     */
    public List<Item> findItems() {
        return itemRepository.findAll();
    }
}
