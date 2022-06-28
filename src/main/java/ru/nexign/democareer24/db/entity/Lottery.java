package ru.nexign.democareer24.db.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author : maoz (mao_z@mail.ru)
 **/
@Entity @Data @NoArgsConstructor
@Table(name = "lotteries")
public class Lottery {

    public static final int LOTTERY_CLOSED = 1;

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @CreationTimestamp
    private Date createdAt;
    private int status = 0;

    @JsonIgnore
    @OneToMany(mappedBy = "lottery")
    List<User> users = new ArrayList<>();

    public Lottery(long id) {
        this.id = id;
    }

    public void close() {
        status |= LOTTERY_CLOSED;
    }
}
