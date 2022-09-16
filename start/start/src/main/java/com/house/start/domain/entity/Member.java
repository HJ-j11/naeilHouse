package com.house.start.domain.entity;

import com.house.start.domain.*;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Member extends BaseTimeEntity implements Serializable {

    @Id
    @GeneratedValue
    @Column(name = "member_id")
    private Long id;

    private String name; // 이름
    private String username; // 아이디
    private String password; // 비밀번호

    @Column(name = "provider_type")
    private ProviderType providerType;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "uploadfile_id")
    private UploadFile uploadFile;

    @OneToMany(mappedBy = "member")
    @Builder.Default
    private List<Order> orders = new ArrayList<>();

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private List<Review> reviews = new ArrayList<>();

    @OneToMany(mappedBy = "member")
    @Builder.Default
    private List<Post> posts = new ArrayList<>();

    @OneToMany(mappedBy = "member")
    @Builder.Default
    private List<Like> likes = new ArrayList<>();

    private int point; // <소비자> 포인트 금액

    @OneToMany(mappedBy = "member")
    @Builder.Default
    private List<Item> items = new ArrayList<>();

    private String storeName; // <판매자> 상호명
    private Boolean isApproved = false; // <판매자> 관리자의 승인 여부

    @ManyToMany(fetch = FetchType.LAZY, cascade={CascadeType.ALL})
    @JoinTable(name = "member_roles", joinColumns = { @JoinColumn(name = "member_id") }, inverseJoinColumns = {
            @JoinColumn(name = "role_id") })
    @Builder.Default
    private Set<Role> userRoles = new HashSet<>();

}
