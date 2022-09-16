package com.house.start.controller;

import com.house.start.controller.form.ItemEditForm;
import com.house.start.controller.form.ItemForm;
import com.house.start.controller.form.MemberJoinForm;
import com.house.start.controller.session.SessionConstants;
import com.house.start.domain.*;
import com.house.start.domain.entity.Member;
import com.house.start.file.FileStore;
import com.house.start.repository.OrderItemRepository;
import com.house.start.repository.OrderRepository;
import com.house.start.service.ConsumerService;
import com.house.start.service.ItemService;
import com.house.start.service.impl.MemberServiceImpl;
import com.house.start.service.SellerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Slf4j
@Controller
@RequiredArgsConstructor
public class SellerController {

    private final ItemService itemService;
    private final SellerService sellerService;
    private final MemberServiceImpl memberService;
    private final FileStore fileStore;
    private final ConsumerService consumerService;
    private final OrderItemRepository orderItemRepository;
    private final OrderRepository orderRepository;

    /**
     * 상품 등록 폼 페이지
     */
    @GetMapping("/seller/item/add")
    public String addItem(Model model) {

        model.addAttribute("form", new ItemForm());
        return "seller/seller_createItemFormTmp";
    }

    /**
     * 상품 등록 완료 후 -> 상품 목록 페이지로
     */
    @Transactional
    @PostMapping("/seller/item/add")
    public String createItem(@ModelAttribute ItemForm form,
                             @AuthenticationPrincipal Member member,
                             HttpServletRequest request) throws IOException {

        // 이미지 저장
        UploadFile uploadFile = fileStore.storeFile(form.getImage(), request);

        Item item = Item.builder()
                .member(member)
                .name(form.getName())
                .price(form.getPrice())
                .stockQuantity(form.getStockQuantity())
                .info(form.getInfo())
                .uploadFile(uploadFile)
                .category(new Category(form.getCategory()))
                .build();

        itemService.createItem(item);

        return "redirect:/seller/item/list";
    }

    /**
     * 등록된 상품 목록 페이지
     */
    @GetMapping("/seller/item/list")
    public String itemList(@AuthenticationPrincipal Member member, Model model) {

        // 판매자가 등록한 상품 조회
        List<Item> items = itemService.findItemsByMember(member);

        model.addAttribute("items", items);
        return "/seller/seller_itemListTmp";
    }

    /**
     * 상품 수정 페이지
     */
    @GetMapping("/seller/item/{id}/edit")
    public String editItemForm(@PathVariable("id") Long id, Model model) {
        Item item = itemService.findItem(id);

        ItemEditForm form = new ItemEditForm();
        form.setName(item.getName());
        form.setPrice(item.getPrice());
        form.setInfo(item.getInfo());
        form.setStockQuantity(item.getStockQuantity());
        form.setCategory(item.getCategory());

        model.addAttribute("item", item);
        model.addAttribute("form", form);
        return "seller/seller_itemEditTmp";
    }

    /**
     * 상품 수정 페이지 -> 수정하기 버튼
     */
    @PostMapping("/seller/item/{id}/edit")
    public String editItem(@PathVariable Long id, @ModelAttribute("form") ItemEditForm form) {
        itemService.updateItem(id, form.getName(), form.getPrice(), form.getInfo(), form.getStockQuantity(), form.getCategory());

        // 상품 목록 페이지로 이동
        return "redirect:/seller/item/list";
    }

    @RequestMapping("/seller/manage")
    public String deliveryManage(@AuthenticationPrincipal Member member, Model model) {

        List<OrderItem> orderItemsBySeller = orderItemRepository.findOrderItemsBySeller(OrderItemStatus.ORDER, member);

        for (OrderItem orderItem : orderItemsBySeller) {
            log.info("orderItem.getOrder().getId() = " + orderItem.getOrder().getId());
        }

        model.addAttribute("orderItems", orderItemsBySeller);
        model.addAttribute("deliveryStatus", DeliveryStatus.values());

        return "seller/seller_manage";
    }

    @PostMapping("/seller/manage/{id}")
    public String changeDelivery(@PathVariable("id") Long orderItemId,
                                 @RequestParam("deliverySelected") DeliveryStatus deliveryStatus,
                                 @AuthenticationPrincipal Member member) {

        Optional<OrderItem> orderItem = orderItemRepository.findById(orderItemId);

        log.info("deliverySelected = " + deliveryStatus);

        if (deliveryStatus == DeliveryStatus.COMPLETE) {
            orderItem.get().setOrderItemStatus(OrderItemStatus.COMPLETED);
        }

        orderItem.get().getDelivery().setDeliveryStatus(deliveryStatus);

        return "redirect:/seller/manage";
    }


    // 테스트 페이지
    @GetMapping("/test/{id}")
    public String test(@PathVariable Long id, @AuthenticationPrincipal Member member, Model model) {


        Item item = itemService.findItem(id);
        model.addAttribute("item", item);
        model.addAttribute("consumer", member);

        return "consumer_beforePurchaseTmp";
    }

}
