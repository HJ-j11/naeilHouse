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

    private String uploadFileName;
    private String storeFileName;

}
