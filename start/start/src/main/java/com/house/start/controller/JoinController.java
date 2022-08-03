package com.house.start.controller;

import com.house.start.controller.form.MemberJoinForm;
import com.house.start.domain.*;
import com.house.start.file.FileStore;
import com.house.start.service.JoinService;
import com.house.start.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Slf4j
@Controller
@RequiredArgsConstructor
public class JoinController {

    private final JoinService joinService;
    private final FileStore fileStore;
    private final PasswordEncoder passwordEncoder;

    /**
     *  어느 역할(소비자 or 판매자 or 관리자)로 회원가입 할지 고르는 페이지
     */
    @GetMapping("/member/join")
    public String joinSelect() {
        return "join/member_select";
    }

    /**
     *  소비자 회원가입 등록 폼 페이지
     */
    @GetMapping("/consumer/join")
    public String joinConsumer(Model model) {
        model.addAttribute("form", new MemberJoinForm());
        return "join/consumer_add";
    }

    /**
     *  소비자 회원가입 등록
     */
    @PostMapping("/consumer/join")
    public String joinConsumer(@ModelAttribute MemberJoinForm form,
                               HttpServletRequest request) throws IOException {

        // 이미지 저장
        UploadFile uploadFile = fileStore.storeFile(form.getImage(), request);

        // 소비자 객체 생성
        Member member = new Member();
        member.setUsername(form.getId());
        member.setPassword(form.getPassword());
        member.setName(form.getName());
//        consumer.setUploadFile(uploadFile);

        // 포인트 초기화
        member.setPoint(500000);

        joinService.joinConsumer(member);

        return "redirect:/";
    }

    /**
     *  판매자 회원가입 등록 폼 페이지
     */
    @GetMapping("/seller/join")
    public String joinSeller(Model model) {
        model.addAttribute("form", new MemberJoinForm());
        return "join/seller_add";
    }

    /**
     *  판매자 회원가입 등록
     */
    @PostMapping("/seller/join")
    public String joinSeller(@ModelAttribute MemberJoinForm form,
                             HttpServletRequest request) throws IOException {

        // 이미지 저장
        UploadFile uploadFile = fileStore.storeFile(form.getImage(), request);

        // 판매자 객체 생성
        Member member = new Member();
        member.setUsername(form.getId());
        member.setPassword(passwordEncoder.encode(form.getPassword()));
        member.setName(form.getName());
        member.setStoreName(form.getStoreName());
//        seller.setUploadFile(uploadFile);

        // 회원 신청 승인여부 false
        member.setIsApproved(false);
        member.setRole(Role.SELLER);

        joinService.joinMember(member);

        return "redirect:/";
    }

    /**
     *  관리자 회원가입 등록 폼 페이지
     */
    @GetMapping("/admin/join")
    public String joinAdmin(Model model) {
        model.addAttribute("form", new MemberJoinForm());
        return "join/admin_add";
    }

    /**
     *  관리자 회원가입 등록
     */
    @PostMapping("/admin/join")
    public String joinAdmin(@ModelAttribute MemberJoinForm form,
                            HttpServletRequest request) throws IOException {

        // 이미지 저장
        UploadFile uploadFile = fileStore.storeFile(form.getImage(), request);

        // 관리자 객체 생성
        Member member = new Member();
        member.setUsername(form.getId());
        member.setPassword(form.getPassword());
        member.setName(form.getName());
        member.setUploadFile(uploadFile);

        joinService.joinMember(member);

        return "redirect:/";
    }

}