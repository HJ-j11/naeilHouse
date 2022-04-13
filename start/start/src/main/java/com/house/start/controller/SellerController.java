package com.house.start.controller;

import com.house.start.controller.form.ItemForm;
import com.house.start.domain.Category;
import com.house.start.domain.Item;
import com.house.start.domain.UploadFile;
import com.house.start.file.FileStore;
import com.house.start.service.ItemService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
public class SellerController {

    private final ItemService itemService;
    private final FileStore fileStore;

    /**
     *  상품 등록 폼 페이지
     */
    @GetMapping("/seller/item/add")
    public String addItem(Model model) {
        log.info("seller controller - get");
        model.addAttribute("form", new ItemForm());
        return "seller_createItemForm";
    }

    /**
     *  상품 등록 완료 후 -> 상품 목록 페이지로
     */
    @PostMapping("/seller/item/add")
    public String createItem(@ModelAttribute ItemForm form, HttpServletRequest request) throws IOException {
        log.info("seller controller - post");
        log.info("multipartFile={}", form.getImage());

        // 판매자 정보 조회
        // Seller seller = sellerService.findSeller(id);

        // 이미지 저장

        UploadFile uploadFile = fileStore.storeFile(form.getImage(), request);
//        fileStore.storeFile(form.getImage(), request);

        Item item = new Item();
        item.setName(form.getName());
        item.setPrice(form.getPrice());
        item.setStockQuantity(form.getStockQuantity());
        item.setInfo(form.getInfo());
        item.setUploadFile(uploadFile);

        Category category = new Category();
        category.setName(form.getCategory());
        item.setCategory(category);

        itemService.createItem(item);

        return "redirect:/seller/item/list";
    }

    /**
     *  등록된 상품 목록 페이지
     */
    @GetMapping("/seller/item/list")
    public String itemList(Model model) {
        List<Item> items = itemService.findItems();
        model.addAttribute("items", items);
        return "seller_itemList";
    }

}
