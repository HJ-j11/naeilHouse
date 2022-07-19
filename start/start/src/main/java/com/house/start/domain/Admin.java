//package com.house.start.domain;
//
//import lombok.Getter;
//import lombok.Setter;
//
//import javax.persistence.*;
//import java.util.ArrayList;
//import java.util.List;
//import java.io.Serializable;
//@Entity
//@Getter @Setter
//public class Admin implements Serializable{
//    @Id @GeneratedValue
//    @Column(name = "admin_id")
//    private Long id;
//
//    private String name; // 관리자 이름
//    private String aId; // 아이디
//    private String pwd; // 비밀번호
//
//    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
//    @JoinColumn(name = "uploadfile_id")
//    private UploadFile uploadFile;
//}
