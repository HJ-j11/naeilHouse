package com.house.start.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
public class Admin {
    @Id
    @Getter @Setter
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long admin_id;

    private String name;

    private String id;

    private String pwd;

}
