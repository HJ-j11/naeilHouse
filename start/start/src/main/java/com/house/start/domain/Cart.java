package com.house.start.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class Cart {

    @Id @GeneratedValue
    private Long id;

    @OneToOne
    @JoinColumn(name = "consumer_id")
    private Consumer consumer;

}
