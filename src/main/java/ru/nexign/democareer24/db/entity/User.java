package ru.nexign.democareer24.db.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * @author : maoz (mao_z@mail.ru)
 **/
@Entity @Data @Table(name = "users")
@NoArgsConstructor
public class User {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;
    private int age;
    private String city;
    private Integer prize;

    @ManyToOne
    Lottery lottery;
}
