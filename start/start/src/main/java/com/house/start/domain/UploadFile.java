package com.house.start.domain;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter @Setter
public class UploadFile {

    @Id @GeneratedValue
    @Column(name = "uploadfile_id")
    private Long id;

    @OneToOne(mappedBy = "uploadFile")
    private Item item;

    @OneToOne(mappedBy = "uploadFile")
    private Post post;

    @OneToOne(mappedBy = "uploadFile")
    private Consumer consumer;

    @OneToOne(mappedBy = "uploadFile")
    private Seller seller;

    @OneToOne(mappedBy = "uploadFile")
    private Admin admin;

    private String uploadFileName;
    private String storeFileName;

    /**
     * 생성 메소드
     * **/

    private void setFileNames(String uploadFileName, String storeFileName) {
        this.setUploadFileName(uploadFileName);
        this.setStoreFileName(storeFileName);
    }

}
