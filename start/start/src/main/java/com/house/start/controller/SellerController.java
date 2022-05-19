package com.house.start.controller;

import com.house.start.controller.form.ItemEditForm;
import com.house.start.controller.form.ItemForm;
import com.house.start.controller.session.SessionConstants;
import com.house.start.domain.*;
import com.house.start.file.FileStore;
import com.house.start.service.ConsumerService;
import com.house.start.service.ItemService;
import com.house.start.service.SellerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
public class SellerController {

    private final ItemService itemService;
    private final SellerService sellerService;
    private final FileStore fileStore;
    private final ConsumerService consumerService;

    /**
     *  상품 등록 폼 페이지
     */
    @GetMapping("/seller/item/add")
    public String addItem( @SessionAttribute(name = SessionConstants.LOGIN_MEMBER, required = false) Seller loginSeller,
                           Model model) {

        // 세션에 회원 데이터가 없으면 예외처리
        if (loginSeller == null) {
            return "redirect:/login";
        }

        log.info("seller controller - get");
        model.addAttribute("form", new ItemForm());
        return "seller/seller_createItemForm";
    }

    /**
     *  상품 등록 완료 후 -> 상품 목록 페이지로
     */
    @PostMapping("/seller/item/add")
    public String createItem(@ModelAttribute ItemForm form,
                             @SessionAttribute(name = SessionConstants.LOGIN_MEMBER, required = false) Seller loginSeller,
                             HttpServletRequest request) throws IOException {

        // 세션에 회원 데이터가 없으면 예외처리
        if (loginSeller == null) {
            return "redirect:/login";
        }

        // 판매자 id 조회
        Seller seller = sellerService.findSeller(loginSeller.getId());

        // 이미지 저장
        UploadFile uploadFile = fileStore.storeFile(form.getImage(), request);

        Item item = new Item();
        item.setSeller(seller);
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
    public String itemList(@SessionAttribute(name = SessionConstants.LOGIN_MEMBER, required = false) Seller loginSeller,
                           Model model) {

        // 세션에 회원 데이터가 없으면 예외처리
        if (loginSeller == null) {
            return "redirect:/login";
        }

        // 판매자 조회
        Seller seller = sellerService.findSeller(loginSeller.getId());

        // 판매자가 등록한 상품 조회
        List<Item> items = itemService.findItemsBySeller(seller);

        model.addAttribute("items", items);
        return "seller/seller_itemList";
    }

    /**
     *  상품 수정 페이지
     */
    @GetMapping("/seller/item/{id}/edit")
    public String editItemForm(@PathVariable("id") Long id, Model model) {
        Item item = itemService.findItem(id);

        ItemEditForm form = new ItemEditForm();
        form.setName(item.getName());
        form.setPrice(item.getPrice());
        form.setInfo(item.getInfo());

        model.addAttribute("item", item);
        model.addAttribute("form", form);
        return "seller/seller_itemEdit";
    }

    /**
     *  상품 수정 페이지 -> 수정하기 버튼
     */
    @PostMapping("/seller/item/{id}/edit")
    public String editItem(@PathVariable Long id, @ModelAttribute("form") ItemEditForm form) {
        itemService.updateItem(id, form.getName(), form.getPrice(), form.getInfo());

        // 상품 목록 페이지로 이동
        return "redirect:/seller/item/list";
    }


    // 테스트 페이지
    @GetMapping("/test")
    public String test(Model model) {
//        List<Item> items = itemService.findItems();
//        model.addAttribute("items", items);

        Item item = itemService.findItem(1L);
        model.addAttribute("item", item);
//        model.addAttribute("form", new MemberJoinForm());

        Consumer consumer = consumerService.findConsumerById(13L);
        model.addAttribute("consumer", consumer);

        return "test";
    }

}
