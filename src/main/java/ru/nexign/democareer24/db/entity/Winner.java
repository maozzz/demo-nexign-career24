package ru.nexign.democareer24.db.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * @author : maoz (mao_z@mail.ru)
 **/
@Entity @Table(name = "winners")
@Data @NoArgsConstructor
public class Winner {

    public Winner(User user, Lottery lottery) {
        this.lottery = lottery;
        this.user = user;
    }

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @OneToOne User user;
    @OneToOne Lottery lottery;
}
