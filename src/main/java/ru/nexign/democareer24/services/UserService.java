package ru.nexign.democareer24.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.nexign.democareer24.db.entity.Lottery;
import ru.nexign.democareer24.db.entity.User;
import ru.nexign.democareer24.db.repo.LotteryRepository;
import ru.nexign.democareer24.db.repo.UserRepository;

import java.util.List;

/**
 * @author : maoz (mao_z@mail.ru)
 **/
@Service @Transactional
public class UserService {
    @Autowired UserRepository userRepository;
    @Autowired @Lazy LotteryService lotteryService;

    @Autowired LotteryRepository lotteryRepository;

    public User create(User user) {
        Lottery lottery = lotteryRepository.findById(lotteryService.current()).get();
        user.setLottery(lottery);
        return userRepository.save(user);
    }

    public List<User> participants() {
        return userRepository.findAllByLotteryId(lotteryService.current());
    }

    public User win(User winner, int prize) {
        winner.setPrize(prize);
        return userRepository.save(winner);
    }

    public List<User> winners() {
        return userRepository.findAllByPrizeNotNull();
    }
}
