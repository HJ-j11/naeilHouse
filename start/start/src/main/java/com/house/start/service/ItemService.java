package com.house.start.service;

import com.house.start.domain.Item;
import com.house.start.domain.Seller;
import com.house.start.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

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
        itemRepository.save(item);
    }

    /**
     *  아이디로 1개 상품 조회
     */
    public Item findItem(Long id) {
        return itemRepository.findById(id).get();
    }

    /**
     *  모든 상품 조회
     */
    public List<Item> findItems() {
        return itemRepository.findAll();
    }

    /**
     *  특정 판매자의 등록 상품 조회
     */
    public List<Item> findItemsBySeller(Seller seller) {
        return itemRepository.findItemBySeller(seller);
    }

    /**
     *  상품 수정
     */
    @Transactional
    public void updateItem(Long id, String name, int price, String info) {
        Optional<Item> item = itemRepository.findById(id);
        item.get().setName(name);
        item.get().setPrice(price);
        item.get().setInfo(info);
    }
}
