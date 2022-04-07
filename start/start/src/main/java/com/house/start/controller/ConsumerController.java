package com.house.start.controller;

<<<<<<< HEAD

import org.springframework.stereotype.Controller;

@Controller
public class ConsumerController {
=======
import com.house.start.domain.Item;
import com.house.start.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class ConsumerController {

    private final ItemService itemService;

    @GetMapping("consumer/item/list")
    public String itemList(Model model) {

        List<Item> items = itemService.findItems();

        List<String> data = new ArrayList<String>();

        int i = 1;
        for (Item item : items) {
            data.add(String.valueOf(i++));
        }

        model.addAttribute("datas", data);
        model.addAttribute("items", items);

        return "consumer_itemList";
    }
>>>>>>> 62bccde (개발중)
}
