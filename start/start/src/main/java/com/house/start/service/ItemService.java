package com.house.start.service;

import com.house.start.domain.Item;
import com.house.start.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;

    @Transactional
    public void createItem(Item item) {
        itemRepository.create(item);
    }

    public List<Item> findItems() {
        return itemRepository.findAll();
    }
}
