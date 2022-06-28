package ru.nexign.democareer24.db.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.nexign.democareer24.db.entity.User;

import java.util.List;

/**
 * @author : maoz (mao_z@mail.ru)
 **/
public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findAllByLotteryId(long lotteryId);

    List<User> findAllByPrizeNotNull();
}
