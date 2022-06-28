package ru.nexign.democareer24.db.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.nexign.democareer24.db.entity.Lottery;

/**
 * @author : maoz (mao_z@mail.ru)
 **/
public interface LotteryRepository extends JpaRepository<Lottery, Long> {

    @Query("from Lottery l WHERE l.status = 0")
    Lottery current();
}
