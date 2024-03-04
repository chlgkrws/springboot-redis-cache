package com.example.redis.code;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity(name = "item")
@Table(name = "item")
@Setter
@Getter
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false, length = 30)
    private String name;

    @Column(length = 200)
    private Double price;
}
