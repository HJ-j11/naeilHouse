package com.house.start.auth;


import com.house.start.domain.Member;
import com.house.start.domain.UploadFile;
import lombok.*;
import org.springframework.security.core.userdetails.User;

import java.util.Map;

@Getter @Setter

public class OAuthAttributes {
    private Map<String, Object> attributes;
    private String nameAttributeKey;
    private String username;
    private String email;
    private String role;
    private UploadFile uploadFile;

    @Builder
    public OAuthAttributes(Map<String, Object> attributes,
                           String nameAttributeKey, String username,
                           String email, UploadFile uploadFile) {
        this.attributes = attributes;
        this.nameAttributeKey= nameAttributeKey;
        this.username = username;
        this.email = email;
        this.uploadFile = uploadFile;
    }

    public static OAuthAttributes of(String userNameAttributeName,
                                     Map<String, Object> attributes) {
        return ofGoogle(userNameAttributeName, attributes);
    }

    private static OAuthAttributes ofGoogle(String userNameAttributeName,
                                            Map<String, Object> attributes) {
        return OAuthAttributes.builder()
                .username((String) attributes.get("name"))
                .email((String) attributes.get("email"))
                .uploadFile((UploadFile) attributes.get("uploadFile"))
                .attributes(attributes)
                .nameAttributeKey(userNameAttributeName)
                .build();
    }


    public Member toEntity() {
        return Member.builder()
                .username(username)
                .email(email)
                .uploadFile(uploadFile)
                .role(role)
                .build();
    }
}
