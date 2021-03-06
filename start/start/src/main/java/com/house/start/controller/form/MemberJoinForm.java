package com.house.start.controller.form;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;

@Data
@Getter @Setter
public class MemberJoinForm {

    @NotBlank
    String id;
    @NotBlank
    String password;
    @NotBlank
    String name;
    @NotBlank
    String storeName;
    @NotBlank
    MultipartFile image;
}
