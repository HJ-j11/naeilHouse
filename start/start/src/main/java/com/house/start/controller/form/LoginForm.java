package com.house.start.controller.form;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.bind.annotation.GetMapping;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@Getter @Setter
public class LoginForm {
    @NotNull
    private Integer role; // 0: consumer, 1: seller, 2: admin

    @NotBlank
    private String loginId; //아이디

    @NotBlank
    private String pwd; //비밀번호
}
