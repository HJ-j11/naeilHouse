package com.house.start.controller;

import com.house.start.controller.form.ItemForm;
import com.house.start.domain.Category;
import com.house.start.domain.Item;
import com.house.start.service.ItemService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
public class SellerController {

    private final ItemService itemService;

    @GetMapping("/seller/add/item")
    public String addItem(Model model) {
        log.info("seller controller - get");
        model.addAttribute("form", new ItemForm());
        return "seller_createItemForm";
    }

    @PostMapping("/seller/add/item")
    public String createItem(@ModelAttribute ItemForm form) {
        log.info("seller controller - post");

        // 판매자 저장

        Item item = new Item();
        item.setName(form.getName());
        item.setPrice(form.getPrice());
        item.setStockQuantity(form.getStockQuantity());
        item.setInfo(form.getInfo());

        Category category = new Category();
        category.setName(form.getCategory());
        item.setCategory(category);

        itemService.createItem(item);

        return "redirect:/seller/item/list";
    }

    @GetMapping("/seller/item/list")
    public String itemList(Model model) {
        List<Item> items = itemService.findItems();
        model.addAttribute("items", items);
        return "seller_itemList";
    }

}
