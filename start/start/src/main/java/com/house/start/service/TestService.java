package com.house.start.service;


import com.house.start.entity.Item;
import com.house.start.repository.TestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TestService {
    @Autowired
    TestRepository testRepository;

    public List<Item> findAll() {
        List<Item> items = new ArrayList<>();
        testRepository.findAll().forEach( e -> items.add(e));
        return items;
    }

}
