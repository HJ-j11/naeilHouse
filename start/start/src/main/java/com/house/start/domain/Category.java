package com.house.start.domain;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Category {

    @Id
    @Column(name="category_id")
    private long id;
    @Column(name="category_name")
    private long name;
}
