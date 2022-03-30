package com.house.start.entity;


import javax.persistence.*;

@Entity
public class Consumer {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long consumer_id;
    @Column(nullable = false)
    private String name;
    private String id;
    private String pwd;

    public long getConsumer_id() {
        return consumer_id;
    }

    public void setConsumer_id(long consumer_id) {
        this.consumer_id = consumer_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }
}
