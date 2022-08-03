package com.house.start.domain;

import com.house.start.controller.form.MemberJoinForm;
import lombok.*;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
@NoArgsConstructor
public class Member implements Serializable {

    @Id
    @GeneratedValue
    @Column(name = "member_id")
    private Long id;

    private String name; // 이름
    private String username; // 아이디
    private String password; // 비밀번호
    private Role role; // 권한

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "uploadfile_id")
    private UploadFile uploadFile;

    @OneToMany(mappedBy = "member")
    private List<Order> orders = new ArrayList<>();

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Review> reviews = new ArrayList<>();

    @OneToMany(mappedBy = "member")
    private List<Post> posts = new ArrayList<>();

    @OneToMany(mappedBy = "member")
    private List<Like> likes = new ArrayList<>();

    private int point; // <소비자> 포인트 금액

    @OneToMany(mappedBy = "member")
    private List<Item> items = new ArrayList<>();

    private String storeName; // <판매자> 상호명
    private Boolean isApproved; // <판매자> 관리자의 승인 여부

    @Builder
    public Member(String name, String username, String password, String storeName, Role role, UploadFile uploadFile) {
        this.name = name;
        this.username = username;
        this.password = password;
        this.storeName = storeName;
        this.role = role;
        this.uploadFile = uploadFile;
    }

    public Member createMember(MemberJoinForm joinForm, Role role, UploadFile uploadFile) {
        Member newMember = Member.builder()
                .name(joinForm.getName())
                .username(joinForm.getId())
                .password(joinForm.getPassword())
                .storeName(joinForm.getStoreName())
                .role(role)
                .uploadFile(uploadFile)
                .build();

        return newMember;
    }

}
