package com.house.start.service;

import com.house.start.domain.Item;
import com.house.start.domain.ItemStatus;
import com.house.start.domain.Seller;
import com.house.start.repository.ItemRepository;
import com.house.start.repository.SellerRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class ConsumerServiceTest {
    @Autowired
    SellerRepository sellerRepository;
    @Autowired
    ItemRepository itemRepository;

//    @Test
//    public void addItem() {
//        Seller seller = Seller.builder()
//                .name("seller1")
//                .sId("aaaa")
//                .pwd("1234")
//                .storeName("store1")
//                .build();
//
//        sellerRepository.save(seller);
//
//        Item item = Item.builder()
//                .name("cup")
//                .info("this is cup")
//                .price(3000)
//                .seller(seller)
//                .itemStatus(ItemStatus.CART)
//                .stockQuantity(10)
//                .build();
//
//        itemRepository.save(item);
//
//    }

//    @Test
//    public List<Item> getItemByCart() {
//        List<Item> items = itemRepository.findByCart(ItemStatus.LIST);
//        Stream<Item> stream = items.stream();
//        stream.forEach(System.out::println);
//
//        return items;
//    }
}