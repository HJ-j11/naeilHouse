package com.house.start.controller.form;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class LoginForm {
    @NotBlank
    private Integer Role; // 0: consumer, 1: seller, 2: admin

    @NotBlank
    private String loginId; //아이디

    @NotBlank
    private String pwd; //비밀번호
}
