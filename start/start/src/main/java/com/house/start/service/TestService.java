package com.house.start.service;


import com.house.start.entity.Item;
import com.house.start.repository.TestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.List;

@Service
public class TestService {

    @Autowired
    TestRepository testRepository;

    public List<Item> findAll() {
        List<Item> items = new ArrayList<>();
        items = testRepository.findAll();
//        items.forEach(e-> System.out.println(e.getName()));
        return items;
    }

    public Item findById(String id) {
        Item itm = new Item();
        itm = testRepository.findById(id);
        return itm;
    }


}
